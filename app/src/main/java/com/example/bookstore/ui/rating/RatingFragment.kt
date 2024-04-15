package com.example.bookstore.ui.rating

import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.navigation.fragment.findNavController
import com.example.bookstore.R
import com.example.bookstore.base.BaseFragment
import com.example.bookstore.databinding.FragmentRatingBinding
import com.example.bookstore.extensions.generateRandomRatingID
import com.example.bookstore.extensions.getCurrentDateTime
import com.example.bookstore.extensions.getUserID
import com.example.bookstore.models.Book
import com.example.bookstore.models.Rating
import org.koin.androidx.viewmodel.ext.android.viewModel
import java.util.Calendar
import kotlin.random.Random

class RatingFragment:BaseFragment<FragmentRatingBinding>(FragmentRatingBinding::inflate) {
    override val viewModel by viewModel<RatingViewModel>()

    private val book by lazy { arguments?.getSerializable("book") as Book }


    override fun initData() {

    }

    override fun handleEvent() {
        binding.apply {
            imgBack.setOnClickListener { findNavController().popBackStack() }
            ratingBar2.setOnRatingBarChangeListener { ratingBar, floatRating, b ->
                var textFeel = ""
                when (floatRating.toInt()){
                    1 ->{
                        textFeel = "Tệ"
                    }
                    2 ->{
                        textFeel = "Không thích"
                    }
                    3 ->{
                        textFeel = "Bình thường"
                    }
                    4 ->{
                        textFeel = "Thích"
                    }
                    5 ->{
                        textFeel = "Cực kỳ thích"
                    }
                    else -> {
                        txtvFeeling.visibility = View.GONE
                    }
                }
                txtvFeeling.text = textFeel
            }
            btnPost.setOnClickListener {
                if(ratingBar2.rating.toInt() !=0){
                    Toast.makeText(context, "Đã chọn sao ${ratingBar2.rating}", Toast.LENGTH_SHORT).show()
                    val rating = Rating(
                        Random.generateRandomRatingID(),
                        book.id,
                        sharedPreferences.getUserID()!!,
                        txtvContent.text.toString(),
                        ratingBar2.rating.toInt(),
                        Calendar.getInstance().getCurrentDateTime(),
                        mutableListOf(),
                        mutableListOf()
                    )
                    viewModel.addRating(rating)
                    viewModel.getAddResult.observe(viewLifecycleOwner){
                        findNavController().popBackStack()
                    }
                }else{
                    Toast.makeText(context, "Bạn chưa chọn sao ${ratingBar2.rating}", Toast.LENGTH_SHORT).show()
                }
            }

        }

    }

    override fun bindData() {

    }

    override fun destroy() {

    }
}