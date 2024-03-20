package com.example.bookstore.ui.setting

import android.graphics.Bitmap
import android.graphics.Color
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.example.bookstore.R
import com.example.bookstore.base.BaseFragment
import com.example.bookstore.base.BaseViewModel
import com.example.bookstore.databinding.FragmentSettingBinding
import com.example.bookstore.extensions.confirmPassword
import com.google.zxing.BarcodeFormat
import com.google.zxing.WriterException
import com.google.zxing.qrcode.QRCodeWriter

class SettingFragment: BaseFragment<FragmentSettingBinding>(FragmentSettingBinding::inflate) {
    override val viewModel: BaseViewModel
        get() = ViewModelProvider(this)[SettingViewModel::class.java]

    override fun initData() {

    }

    override fun handleEvent() {
        binding.apply {
            inforAccount.setOnClickListener {
                dialog(requireContext()).confirmPassword {
                    Toast.makeText(context, "$it", Toast.LENGTH_SHORT).show()
                    findNavController().navigate(R.id.action_settingFragment_to_accountInformationFragment)
                }
            }
            changePasswrod.setOnClickListener {
                dialog(requireContext()).confirmPassword {
                    Toast.makeText(context, "$it", Toast.LENGTH_SHORT).show()
                    findNavController().navigate(R.id.action_settingFragment_to_accountInformationFragment)
                }
            }
        }
    }

    override fun bindData() {
        //QR code generate
        try {
            val bitMatrix = QRCodeWriter().encode(binding.txtvIdUser.text.toString(),
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