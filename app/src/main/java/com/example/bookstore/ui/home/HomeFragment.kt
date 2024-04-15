package com.example.bookstore.ui.home

import android.content.ContentValues.TAG
import android.util.Log
import android.widget.Toast
import androidx.core.os.bundleOf
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.bumptech.glide.Glide
import com.example.bookstore.R
import com.example.bookstore.base.BaseFragment
import com.example.bookstore.databinding.FragmentHomeBinding
import com.example.bookstore.extensions.generateRandomCustomerID
import com.example.bookstore.extensions.getUserID
import com.example.bookstore.models.Book
import com.example.bookstore.models.BookGenres
import com.example.bookstore.models.User
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.firestore.toObject
import com.google.firebase.ktx.Firebase
import io.github.g00fy2.quickie.QRResult
import io.github.g00fy2.quickie.ScanQRCode
import kotlinx.coroutines.tasks.await
import org.koin.androidx.viewmodel.ext.android.viewModel
import kotlin.random.Random

class HomeFragment :BaseFragment<FragmentHomeBinding>(FragmentHomeBinding::inflate) {
    override val viewModel by viewModel<HomeViewModel>()
    private val email by lazy { arguments?.getSerializable("email") as String}

    private val nameTypeBookAdapter by lazy{
        ListAdapterBookTypeName()
    }
    private val listTypeBookAdapter by lazy{
        ListAdapterBookType(::onClickItem)
    }

    override fun initData() {
        viewModel.getBookByGenre()
    }

    override fun handleEvent() {
        binding.apply {
            cart.setOnClickListener {
                findNavController().navigate(R.id.action_homeFragment_to_cartFragment)
            }
            voucher.setOnClickListener {
                findNavController().navigate(R.id.action_homeFragment_to_voucherFragment)
            }
            orders.setOnClickListener {
                findNavController().navigate(R.id.action_homeFragment_to_orderFragment)
            }
            QRCodeScanner.setOnClickListener {
                scanQrCodeLauncher.launch(null)
            }
            shopLocation.setOnClickListener {

            }
        }
    }


    override fun bindData() {
        viewModel.getBookByGenre.observe(viewLifecycleOwner){
            binding.recyclerviewListTypeBook.layoutManager = LinearLayoutManager(context)
            listTypeBookAdapter.submitList(it)
            binding.recyclerviewListTypeBook.adapter = listTypeBookAdapter
        }

        binding.apply {
//            recycleviewNameTypeBook.layoutManager = LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
//            recycleviewNameTypeBook.adapter = nameTypeBookAdapter
//            nameTypeBookAdapter.submitList(listName)

//            val user = User(
//                sharedPreferences.getUserID()!!, Random.generateRandomCustomerID(),
//                "", "", "", email, "", "")
//            viewModel.addUser(user)
            val books = listOf(
                Book("3", "Cách Nghĩ Để Thành Công", listOf("cach_nghi_de_thanh_cong.jpg"),
                    70000.0, "NXB Trẻ", "Napoleon Hill",
                    listOf("Kỹ năng sống", "Tâm lý"), "Cách nghĩ để thành công là " +
                            "một trong những cuốn sách bán chạy nhất mọi thời đại. Đã hơn 60 triệu " +
                            "bản được phát hành với gần trăm ngôn ngữ trên toàn thế giới và được" +
                            " công nhận là cuốn sách tạo ra nhiều triệu phú hơn, một cuốn sách " +
                            "truyền cảm hứng thành công nhiều hơn bất cứ cuốn sách kinh doanh " +
                            "nào trong lịch sử", 10.0, 0.0
                ),

                Book("4", "Lục Vân Tiên cổ tích truyện tập 1", listOf("luc_van_tien_tap_1.png"),
                    150000.0, "Văn hóa – Văn nghệ", "Viện Viễn Đông Bác Cổ Pháp",
                    listOf("Văn học", "Nghiên cứu"), "Bộ truyện Lục Vân Tiên minh họa bằng" +
                            " tranh màu được ra đời từ ý tưởng của Eugène Gibert, " +
                            "Đại úy Pháo binh Hải quân, ông đã tổ chức bản thảo chép tay truyện thơ," +
                            " có tranh minh họa do Lê Đức Trạch thể hiện", 30.0, 0.0
                ),
                Book("5", "Harry Potter Và Chiếc Cốc Lửa (Tái bản)", listOf("harry_portter_coc_lua.png"),
                    263000.0, "NXB Trẻ", "J. K. Rowling",
                    listOf("Tiểu Thuyết", "Hư cấu kỳ ảo"), "Khi giải Quidditch Thế giới bị cắt " +
                            "ngang bởi những kẻ ủng hộ Chúa tể Voldemort và sự trở lại của Dấu hiệu" +
                            " hắc ám khủng khiếp, Harry ý thức được rõ ràng rằng, Chúa tể Voldemort " +
                            "đang ngày càng mạnh hơn", 5.0, 0.0
                ),
            )
            val booksData = books.map { book ->
                mapOf(
                    "id" to book.id,
                    "title" to book.title,
                    "images" to book.images,
                    "price" to book.price,
                    "publisher" to book.publisher,
                    "author" to book.author,
                    "genres" to book.genres,
                    "description" to book.description,
                    "rating" to book.rating,
                    "reviews" to book.promotionPrice
                )
            }

        }


    }

    private val scanQrCodeLauncher = registerForActivityResult(ScanQRCode()) { result ->
        handleQRResult(result)
    }

    private fun handleQRResult(result: QRResult) {
        when (result) {
            is QRResult.QRSuccess -> {
                val rawValue = result.content.rawValue
                Toast.makeText(context, "Giá trị là: $rawValue", Toast.LENGTH_SHORT).show()
                Log.d("TAG", "handleQRResult: $rawValue", )
            }
            is QRResult.QRUserCanceled -> {

            }
            is QRResult.QRMissingPermission -> {

            }
            is QRResult.QRError -> {
                val exception = result.exception
            }
        }
    }
    private fun onClickItem(isShowMoreSelected: Boolean, bookGenres: BookGenres, book: Book){
        if(isShowMoreSelected){
            val bundle = bundleOf("bookGenre" to bookGenres)
            findNavController().navigate(R.id.action_homeFragment_to_showAllBookFragment, bundle)
        }else{
            val bundle = bundleOf("bookDetail" to book)
            findNavController().navigate(R.id.action_homeFragment_to_bookDetailFragment, bundle)
        }

    }

    override fun destroy() {

    }
}