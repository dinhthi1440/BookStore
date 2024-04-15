package com.example.bookstore.ui.setting

import android.app.AlertDialog
import android.graphics.Bitmap
import android.graphics.Color
import android.util.Log
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import androidx.core.os.bundleOf
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.bumptech.glide.Glide
import com.example.bookstore.R
import com.example.bookstore.base.BaseFragment
import com.example.bookstore.base.BaseViewModel
import com.example.bookstore.databinding.FragmentSettingBinding
import com.example.bookstore.extensions.confirmPassword
import com.example.bookstore.extensions.destroyUserID
import com.example.bookstore.extensions.getUserID
import com.example.bookstore.models.User
import com.google.firebase.auth.FirebaseAuth
import com.google.zxing.BarcodeFormat
import com.google.zxing.WriterException
import com.google.zxing.qrcode.QRCodeWriter
import org.koin.androidx.viewmodel.ext.android.viewModel

class SettingFragment: BaseFragment<FragmentSettingBinding>(FragmentSettingBinding::inflate) {
    override val viewModel by viewModel<SettingViewModel>()

    lateinit var user : User
    private var auth = FirebaseAuth.getInstance()

    private val contract = registerForActivityResult(ActivityResultContracts.GetContent()) { uri ->
        
        viewModel.upLoadImage("avt/${user.userID}", uri!!)
        viewModel.postImageResult.observe(viewLifecycleOwner){
            viewModel.updateUser(User(
                user.userID, user.customerCode,
                user.userName, user.gender, user.dateOfBirth, user.email, user.phoneNumber, it.toString()))
            viewModel.getUserResult.observe(viewLifecycleOwner){
                binding.imgAvtUser.setImageURI(uri)
                Toast.makeText(context, "Thay đổi ảnh thành công", Toast.LENGTH_SHORT).show()
            }
        }
        
    }
    override fun initData() {

    }

    override fun handleEvent() {
        binding.apply {
            inforAccount.setOnClickListener {
                dialog(requireContext()).confirmPassword {
                    val bundle = bundleOf("user" to user)
                    findNavController().navigate(R.id.action_settingFragment_to_accountInformationFragment, bundle)
//                    viewModel.reAuthenticateUser(it)
//                    viewModel.getConfirmPassResult.observe(viewLifecycleOwner){resutl ->
//                        if(resutl == 1){
//                            val bundle = bundleOf("user" to user)
//                            findNavController().navigate(R.id.action_settingFragment_to_accountInformationFragment, bundle)
//                        }else{
//                            Toast.makeText(context, "Mật khẩu không đúng", Toast.LENGTH_SHORT).show()
//                            Log.e("TAG", "handleEvent: lỗi", )
//                        }
//                    }
                }
            }
            changePasswrod.setOnClickListener {
                dialog(requireContext()).confirmPassword {
                    Toast.makeText(context, "$it", Toast.LENGTH_SHORT).show()
                    findNavController().navigate(R.id.action_settingFragment_to_changePasswordFragment)
                }
            }
            layoutLogOut.setOnClickListener {
                AlertDialog.Builder(context)
                    .setMessage("Bạn có muốn đăng xuất ?")
                    .setPositiveButton("Đồng ý") { _, _ ->
                        auth.signOut()
                        sharedPreferences.destroyUserID()
                        if(auth.currentUser == null){

                            findNavController().popBackStack()
                        }
                    }
                    .setNegativeButton("Hủy bỏ", null)
                    .show()
            }
        }
    }

    override fun bindData() {
        generateQRCode()
        viewModel.getUser(sharedPreferences.getUserID()!!)
        binding.apply {
            viewModel.getUserResult.observe(viewLifecycleOwner){
                user = it
                txtvUsername.text = it.userName
                txtvCustomerCode.text = it.customerCode
                Glide.with(this@SettingFragment).load(it.imageUser).into(imgAvtUser)
            }
            imgAvtUser.setOnClickListener {
                contract.launch("image/*")
            }
        }
    }

    private fun generateQRCode(){
        try {
            val bitMatrix = QRCodeWriter().encode(binding.txtvCustomerCode.text.toString(),
                BarcodeFormat.QR_CODE, 512, 512)
            val width = bitMatrix.width
            val height = bitMatrix.height
            val bmp = Bitmap.createBitmap(width, height, Bitmap.Config.RGB_565)
            for(x in 0 until width){
                for(y in 0 until height){
                    bmp.setPixel(x, y, if(bitMatrix[x, y]) Color.BLACK else Color.WHITE)
                }
            }
            binding.imgQrCodeUser.setImageBitmap(bmp)
        }catch (e: WriterException){
            e.printStackTrace()
        }
    }

    override fun destroy() {

    }
}