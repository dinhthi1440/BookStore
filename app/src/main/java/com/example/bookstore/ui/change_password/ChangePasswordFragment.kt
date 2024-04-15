package com.example.bookstore.ui.change_password

import android.annotation.SuppressLint
import android.os.Handler
import android.provider.CalendarContract.Colors
import android.util.Log
import android.widget.Toast
import androidx.compose.ui.graphics.Color
import androidx.core.content.ContextCompat
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.example.bookstore.R
import com.example.bookstore.base.BaseFragment
import com.example.bookstore.base.BaseViewModel
import com.example.bookstore.databinding.FragmentChangePasswordBinding
import com.example.bookstore.untils.Constant
import org.koin.androidx.viewmodel.ext.android.viewModel

class ChangePasswordFragment :BaseFragment<FragmentChangePasswordBinding>(FragmentChangePasswordBinding::inflate) {
    override val viewModel by viewModel<ChangePassViewModel>()

    override fun initData() {

    }


    override fun handleEvent() {
        binding.apply {
            btnUpdate.setOnClickListener {
                Log.e("TAG", "handleEvent: đã bấm ${edittextNewPassword.text} và ${edittextPasswordAgain.text}", )
                if(edittextNewPassword.text.toString().matches(Constant.Regex.passwordRegex.toRegex())){
                    txtvWarningInput.setTextColor(ContextCompat.getColor(requireContext(),R.color.text_color_green))
                    if(edittextNewPassword.text.toString() != edittextPasswordAgain.text.toString()){
                        txtvWarningInput.text = "Mật khẩu nhập lại không khớp"
                        txtvWarningInput.setTextColor(ContextCompat.getColor(requireContext(),R.color.text_red))
                    }else{
                        txtvWarningInput.text = "Đã khớp"
                        txtvWarningInput.setTextColor(ContextCompat.getColor(requireContext(),R.color.text_color_green))
                        viewModel.updatePassWord(edittextNewPassword.text.toString())
                        viewModel.getUpdatePassResult.observe(viewLifecycleOwner){
                            Toast.makeText(context, "Thay đổi mật khẩu thành công", Toast.LENGTH_SHORT).show()
                            Handler().postDelayed(
                                { findNavController().popBackStack()},2000
                            )
                        }
                    }
                }else{
                    txtvWarningInput.setTextColor(ContextCompat.getColor(requireContext(),R.color.text_red))
                }
            }
        }
    }

    override fun bindData() {

    }

    override fun destroy() {

    }
}