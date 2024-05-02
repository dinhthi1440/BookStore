package com.example.bookstore.ui.on_boarding

import android.app.AlertDialog
import android.app.DatePickerDialog
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import androidx.navigation.fragment.findNavController
import com.example.bookstore.R
import com.example.bookstore.base.BaseFragment
import com.example.bookstore.databinding.FragmentOnBoardingBinding
import com.example.bookstore.extensions.generateRandomCustomerID
import com.example.bookstore.extensions.getUserID
import com.example.bookstore.extensions.saveFirstLogIn
import com.example.bookstore.models.User
import com.example.bookstore.ui.setting.SettingViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel
import java.util.Calendar
import kotlin.random.Random

class OnBoardingFragment:BaseFragment<FragmentOnBoardingBinding>(FragmentOnBoardingBinding::inflate) {
    override val viewModel by viewModel<SettingViewModel>()
    private val userID by lazy { arguments?.getSerializable("userID") as String}
    private lateinit var datePickerDialog: DatePickerDialog
    private var uriAvt = ""
    private val contract = registerForActivityResult(ActivityResultContracts.GetContent()) { uri ->
        viewModel.upLoadImage("avt/${userID}", uri!!)
        viewModel.postImageResult.observe(viewLifecycleOwner){
            uriAvt = it
            binding.imgAvtUser.setImageURI(uri)
        }
    }
    override fun initData() {

    }

    override fun handleEvent() {

        binding.apply {
            btnContinue.setOnClickListener {
                val userName = edittextUserName.text.toString()
                val gender = acptGender.selectedItem.toString()
                val birthDate = edittextBirthDay.text.toString()
                val phoneNumber = edittextPhoneNumber.text.toString()
                if(userName !="" && gender != "" && birthDate !="" && phoneNumber !=""){
                    val user = User(
                        sharedPreferences.getUserID()!!, Random.generateRandomCustomerID(),
                        userName, gender, birthDate, "", phoneNumber, uriAvt
                    )
                    viewModel.updateUser(user)
                    viewModel.getUpdateUserResult.observe(viewLifecycleOwner){
                        sharedPreferences.saveFirstLogIn("NoFirstTime")
                        findNavController().navigate(R.id.action_onBoardingFragment_to_homeFragment)
                    }
                }else{
                    Toast.makeText(context, "Bạn chưa nhập đủ thông tin", Toast.LENGTH_SHORT).show()
                }
            }
            imgAvtUser.setOnClickListener {
                contract.launch("image/*")
            }
            acptGender.onItemSelectedListener = object : AdapterView.OnItemSelectedListener{
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

    override fun bindData() {
        val gender = arrayOf("Nam", "Nữ")
        initDatePicker()
        binding.apply {
            val adapter = ArrayAdapter(requireContext(),
                R.layout.item_dropdown, gender)
            acptGender.adapter = adapter
        }
    }

    override fun destroy() {

    }
}