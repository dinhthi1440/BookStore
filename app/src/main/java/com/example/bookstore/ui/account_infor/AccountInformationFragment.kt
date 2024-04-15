package com.example.bookstore.ui.account_infor

import android.annotation.SuppressLint
import android.app.AlertDialog
import android.app.DatePickerDialog
import android.util.Log
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.navigation.fragment.findNavController
import com.example.bookstore.R
import com.example.bookstore.base.BaseFragment
import com.example.bookstore.databinding.FragmentAccountInformationBinding
import com.example.bookstore.models.User
import org.koin.androidx.viewmodel.ext.android.viewModel
import java.util.Calendar


class AccountInformationFragment
    : BaseFragment<FragmentAccountInformationBinding>(FragmentAccountInformationBinding::inflate) {
    override val viewModel by viewModel<AccountInforVIewModel>()

    private val user by lazy { arguments?.getSerializable("user") as User }
    private lateinit var datePickerDialog: DatePickerDialog
    override fun initData() {

    }

    override fun handleEvent() {
        binding.apply {
            imgBack.setOnClickListener { findNavController().popBackStack() }
            btnUpdate.setOnClickListener {
                val userUpdate = User(
                    user.userID, user.customerCode, edittextUserName.text.toString(),
                    acptGender.selectedItem.toString(), edittextBirthDay.text.toString(),
                    user.email, edittextPhoneNumber.text.toString(), user.imageUser
                )
                viewModel.updateInforUser(userUpdate)
                viewModel.getUpdateInforResult.observe(viewLifecycleOwner){
                    if(it ==1){
                        Toast.makeText(context, "Sửa thông tin thành công!", Toast.LENGTH_SHORT).show()
                        findNavController().popBackStack()
                    }else{
                        Toast.makeText(context, "Lỗi ", Toast.LENGTH_SHORT).show()
                    }
                    
                }
            }
            acptGender.onItemSelectedListener = object :AdapterView.OnItemSelectedListener{
                override fun onItemSelected(p0: AdapterView<*>?, p1: View?, p2: Int, p3: Long) {

                }

                override fun onNothingSelected(p0: AdapterView<*>?) {

                }

            }
            edittextBirthDay.setOnClickListener {
                datePickerDialog.show()
            }
        }


    }
    private fun initDatePicker() {
        val dateSetListener = DatePickerDialog.OnDateSetListener { datePicker, year, month, day ->
            val realMonth = month + 1
            val date = makeDateString(day, realMonth, year)
            binding.edittextBirthDay.setText(date)
        }
        val cal = Calendar.getInstance()
        val year = cal.get(Calendar.YEAR)
        val month = cal.get(Calendar.MONTH)
        val day = cal.get(Calendar.DAY_OF_MONTH)
        val style = AlertDialog.THEME_HOLO_LIGHT
        datePickerDialog = DatePickerDialog(requireContext(), style, dateSetListener, year, month, day)
    }
    private fun makeDateString(day: Int, month: Int, year: Int): String {
        return "$day/$month/$year"
    }
    @SuppressLint("ResourceType")
    override fun bindData() {
        val gender = arrayOf("Nam", "Nữ")
        initDatePicker()
        binding.apply {
            //edittextEmail.setText(user.email)
            edittextBirthDay.setText(user.dateOfBirth)
            edittextUserName.setText(user.userName)
            edittextPhoneNumber.setText(user.phoneNumber)
            val adapter = ArrayAdapter(requireContext(),
                R.layout.item_dropdown, gender)
            acptGender.adapter = adapter
            acptGender.setSelection(gender.indexOf(user.gender))
        }
    }

    override fun destroy() {

    }
}