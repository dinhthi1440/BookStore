package com.example.bookstore.ui.book_detail

import android.annotation.SuppressLint
import android.content.Context
import android.graphics.Paint
import android.os.Bundle
import android.os.CountDownTimer
import android.util.Log
import android.view.View
import android.view.inputmethod.InputMethodManager
import android.widget.Toast
import androidx.core.os.bundleOf
import androidx.fragment.app.setFragmentResultListener
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.denzcoskun.imageslider.models.SlideModel
import com.example.bookstore.R
import com.example.bookstore.base.BaseFragment
import com.example.bookstore.databinding.FragmentBookDetailBinding
import com.example.bookstore.extensions.confirmNumberPurchase
import com.example.bookstore.extensions.generateRandomCartID
import com.example.bookstore.extensions.generateRandomCommentID
import com.example.bookstore.extensions.getCurrentDateTime
import com.example.bookstore.extensions.getUserID
import com.example.bookstore.models.Book
import com.example.bookstore.models.Cart
import com.example.bookstore.models.CommentDetail
import com.example.bookstore.models.Rating
import com.example.bookstore.models.RatingDetail
import com.example.bookstore.models.TotalRateBook
import com.example.bookstore.models.User
import org.koin.androidx.viewmodel.ext.android.viewModel
import java.util.Calendar
import kotlin.random.Random


class BookDetailFragment:BaseFragment<FragmentBookDetailBinding>(FragmentBookDetailBinding::inflate) {
    override val viewModel by viewModel<BookDetailViewModel>()

    private val book by lazy {  arguments?.getSerializable("bookDetail") as Book }
    private val getUserID by lazy { sharedPreferences.getUserID()!! }
    private var ratingCurrentRep = Rating()
    private var listAdapterEvaluate= ListAdapterEvaluate(::commentClick,
        ::showComment, ::followUser, ::clickLike, ::deleteRating, getUserID)
    override fun initData() {

    }

    override fun handleEvent() {
        var quantityPurchase = 0;
        var isFavourite = false
        binding.apply {
            imgFavourite.setOnClickListener {
                if(isFavourite){
                    isFavourite = false
                    imgFavourite.setImageResource(R.drawable.heart_grey)
                }else{
                    isFavourite = true
                    imgFavourite.setImageResource(R.drawable.heart_red)
                }
            }

            order.setOnClickListener {
                dialog(requireContext()).confirmNumberPurchase(book){quantity ->
                    val cart = Cart(Random.generateRandomCartID(), getUserID, book, quantity)
                    viewModel.addNewCart(cart)
                    viewModel.getResultAddCart.observe(viewLifecycleOwner){
                        if(it==1){
                            Toast.makeText(context, "Thêm thành công", Toast.LENGTH_SHORT).show()
                        }else{
                            Toast.makeText(context, "Thât bại", Toast.LENGTH_SHORT).show()
                        }
                    }
                }
            }
            purchase.setOnClickListener {

                findNavController().navigate(R.id.action_bookDetailFragment_to_cartFragment)
            }
            txtvRating.setOnClickListener {
                val bundle = bundleOf("book" to book)
                findNavController().navigate(R.id.action_bookDetailFragment_to_ratingFragment, bundle)
            }
            imgSend.setOnClickListener {
                viewModel.addComment(CommentDetail(
                    Random.generateRandomCommentID(),
                    ratingCurrentRep.id,
                    binding.edtComment.text.toString(),
                    sharedPreferences.getUserID()!!,
                    Calendar.getInstance().getCurrentDateTime(),
                    mutableListOf()
                ), ratingCurrentRep)
                hideKeyboard(requireView())
                viewModel.getResultAddComment.observe(viewLifecycleOwner){
                    Toast.makeText(context, "Thêm bình luận thành công", Toast.LENGTH_SHORT).show()
                    loadDataAgain()
                }
                binding.layoutShopping.visibility = View.VISIBLE
                binding.layoutComment.visibility = View.GONE
            }
        }
    }

    @SuppressLint("SetTextI18n")
    override fun bindData() {
        viewModel.getRating(book.id)
        viewModel.getNumberRateBook(book.id)
        binding.apply {
            if(book !=null){
                txtvNameBook.text = book.title
                txtvAuthor.text = book.author
                txtvDescription.text = book.description
                txtvPublisher.text = book.publisher
                txtvGenre.text = book.genres.joinToString(" | ")
                if(book.rating != 0.0){
                    txtvPromotionPercent.text = "-" + book.rating.toString() + "%"
                    txtvPrice.text = book.price.toString() +"đ"
                    txtvPrice.paintFlags =  Paint.STRIKE_THRU_TEXT_FLAG
                    txtvSellingPrice.text = (book.price * (100.0-book.rating) /100.0).toInt().toString() +"đ"
                }else{
                    txtvPromotionPercent.visibility = View.GONE
                    txtvPrice.visibility = View.GONE
                    txtvSellingPrice.text = book.price.toString() +"đ"
                }
                val images = ArrayList<SlideModel>()
                for (img in book.images){
                    images.add(SlideModel(img))
                }
                imageView.setImageList(images)
            }

            viewModel.getRatingNumResult.observe(viewLifecycleOwner){
                bindToTalRatingNumber(it)
            }


            viewModel.getListRating.observe(viewLifecycleOwner){
                if(it.isEmpty()){
                }else{

                    recyclerviewListEvaluate.layoutManager = LinearLayoutManager(root.context)
                    listAdapterEvaluate.submitList(it)
                    recyclerviewListEvaluate.adapter = listAdapterEvaluate
                }
            }
        }
    }

    @SuppressLint("SetTextI18n")
    private fun commentClick(rating: Rating, userName: String){
        ratingCurrentRep = rating
        binding.layoutShopping.visibility = View.GONE
        binding.layoutComment.visibility = View.VISIBLE
        binding.edtComment.setText("@$userName ")
        showKeyboard(requireContext())
    }

    @SuppressLint("SetTextI18n")
    private fun bindToTalRatingNumber(totalRateBook: TotalRateBook){
        val listNumberRating = mutableListOf<Int>()
        listNumberRating.add(totalRateBook.rate1Star)
        listNumberRating.add(totalRateBook.rate2Star)
        listNumberRating.add(totalRateBook.rate3Star)
        listNumberRating.add(totalRateBook.rate4Star)
        listNumberRating.add(totalRateBook.rate5Star)
        val totalNumber = listNumberRating.take(5).sum()
        val totalStar = listNumberRating.zip(1..5) { rating, star -> rating * star }.sum()
        val averageRating = totalStar.toDouble() / totalNumber
        val roundedAverageRating = "%.2f".format(averageRating)
        val listRating = mutableListOf<Double>()
        for (rate in listNumberRating){
            listRating.add(rate*100.0/totalNumber)
        }
        binding.apply {
            txtvAverageStarRating.text = roundedAverageRating
            val listAdapterRatingMain = ListAdapterRatingMain()
            recyclerView.layoutManager = LinearLayoutManager(root.context)
            listAdapterRatingMain.submitList(listRating)
            recyclerView.adapter = listAdapterRatingMain
            txtvTotalRating.text = totalNumber.toString()
            appCompatRatingBar.rating = averageRating.toFloat()
            txtvAverageStar.text = "$roundedAverageRating / 5"
        }
    }

    private fun showComment(ratingDetail: RatingDetail){
        val bundle = bundleOf("rating" to ratingDetail)
        findNavController().navigate(R.id.action_bookDetailFragment_to_replyRatingFragment, bundle)
    }
    private fun clickLike(rating: Rating, isLike: Boolean){
        viewModel.updateRating(rating, sharedPreferences.getUserID()!!, isLike)
        viewModel.getUpdateRatingResult.observe(viewLifecycleOwner){
            Toast.makeText(context, "Đã like thành công", Toast.LENGTH_SHORT).show()
        }
    }

    private fun followUser(friendID: String){
        viewModel.addFriends(getUserID, friendID)
        viewModel.getAddFriendResult.observe(viewLifecycleOwner){
            Toast.makeText(context, "Theo dõi thành công", Toast.LENGTH_SHORT).show()
        }
    }
    private fun loadDataAgain(){
        viewModel.getRating(book.id)
        viewModel.getListRating.observe(viewLifecycleOwner){
            if(it.isEmpty()){
            }else{
                binding.recyclerviewListEvaluate.layoutManager = LinearLayoutManager(binding.root.context)
                listAdapterEvaluate.submitList(it)
                binding.recyclerviewListEvaluate.adapter = listAdapterEvaluate
            }
        }
    }

    private fun deleteRating(rating: Rating, ){
        viewModel.deleteRating(rating)
        viewModel.getDeleteRating.observe(viewLifecycleOwner){
            Toast.makeText(context, "Xóa đánh giá thành công", Toast.LENGTH_SHORT).show()
            loadDataAgain()
        }
    }
    override fun destroy() {

    }



}