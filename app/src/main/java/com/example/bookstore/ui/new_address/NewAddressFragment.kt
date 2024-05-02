package com.example.bookstore.ui.new_address

import android.annotation.SuppressLint
import android.text.InputType
import android.util.Log
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.navigation.fragment.findNavController
import com.example.bookstore.R
import com.example.bookstore.base.BaseFragment
import com.example.bookstore.databinding.FragmentNewAddressBinding
import com.example.bookstore.extensions.generateAddressID
import com.example.bookstore.extensions.getAddressID
import com.example.bookstore.extensions.getUserID
import com.example.bookstore.extensions.saveAddressID
import com.example.bookstore.models.Address
import com.example.bookstore.models.Commune
import com.example.bookstore.models.District
import com.example.bookstore.models.Notify
import com.example.bookstore.models.Province
import com.example.bookstore.models.RatingDetail
import com.example.bookstore.util.JsonFileProcess
import com.example.bookstore.util.Utils
import org.koin.androidx.viewmodel.ext.android.viewModel
import kotlin.random.Random


class NewAddressFragment :BaseFragment<FragmentNewAddressBinding>(FragmentNewAddressBinding::inflate) {
    override val viewModel by viewModel<NewAddressViewModel>()
    private val address by lazy { arguments?.getSerializable("address") as? Address }
    private val provincesJsonString by lazy {JsonFileProcess().readJsonFromAssets(requireContext(), "tinh_tp.json")}
    private val districtJsonString by lazy { JsonFileProcess().readJsonFromAssets(requireContext(), "quan_huyen.json")}
    private val communeJsonString by lazy { JsonFileProcess().readJsonFromAssets(requireContext(), "xa_phuong.json")}
    private var addressID = ""

    override fun initData() {

    }

    @SuppressLint("SuspiciousIndentation")
    override fun handleEvent() {
        val provinces: List<Province> = JsonFileProcess().parseJsonToModel(provincesJsonString)
        val districts: List<District> = JsonFileProcess().parseJsonToModel(districtJsonString)
        val communes: List<Commune> = JsonFileProcess().parseJsonToModel(communeJsonString)
        var provinceCode = ""
        var districtCode = ""
        var filteredDistricts = listOf<District>()
        var listDistrictPathWithType = listOf<String>()
        var filteredCommunes = listOf<Commune>()
        var listCommunePathWithType = listOf<String>()

        binding.apply {

            imgBack.setOnClickListener {
                findNavController().popBackStack()
            }
            btnAdd.setOnClickListener {
                val userName = editTextUsername.text.toString()
                val phoneNumber= editTextPhoneNumber.text.toString()
                val province= actvProvince.text.toString()
                val district= actvDistrict.text.toString()
                val commune= actvCommuneOrAward.text.toString()
                val addressDetail= txtipAddressDetail.text.toString()
                if(checkData(userName, phoneNumber, province, district,
                        commune, addressDetail)){
                    viewModel.addNewAddress(sharedPreferences.getUserID()!!,
                        Address(addressID, userName, phoneNumber, province, district,
                            commune, addressDetail, "", false)
                    )
                    viewModel.addNewAddressResult.observe(viewLifecycleOwner){
                        Toast.makeText(context, "Đã sửa thành công", Toast.LENGTH_SHORT).show()
                        findNavController().popBackStack()
                    }
                }
            }
            actvProvince.run {
                val listPathWithType = provinces.map { it.nameWithType }.toList()
                setOnClickListener {
                    setAdapter(ArrayAdapter(context, R.layout.item_dropdown, listPathWithType))
                }
                inputType  = InputType.TYPE_NULL
                performClick()
                setOnItemClickListener { adapterView, view, index, l ->
                    if(provinceCode!=provinces[index].code){
                        actvDistrict.setText("")
                        actvCommuneOrAward.setText("")
                    }
                    provinceCode = provinces[index].code
                    setText(listPathWithType[index])
                }
            }
            actvDistrict.run {
                setOnClickListener {
                    filteredDistricts = districts.filter { it.parentCode == provinceCode }
                    listDistrictPathWithType = filteredDistricts.map { it.nameWithType }
                    setAdapter(ArrayAdapter(context, R.layout.item_dropdown, listDistrictPathWithType))
                }
                inputType = InputType.TYPE_NULL
                performClick()
                setOnItemClickListener { adapterView, view, i, l ->
                    if(districtCode!=filteredDistricts[i].code){
                        actvCommuneOrAward.setText("")
                    }
                    districtCode = filteredDistricts[i].code
                    setText(listDistrictPathWithType[i])
                }
            }
            actvCommuneOrAward.run {
                setOnClickListener {
                    filteredCommunes = communes.filter { it.parentCode == districtCode }
                    listCommunePathWithType = filteredCommunes.map { it.nameWithType }.toList()
                    setAdapter(ArrayAdapter(context, R.layout.item_dropdown, listCommunePathWithType))
                }
                inputType = InputType.TYPE_NULL
                performClick()
                setOnItemClickListener { adapterView, view, i, l ->
                    districtCode = filteredCommunes[i].code
                    setText(listCommunePathWithType[i])
                }
            }
            val defaultAddrID = sharedPreferences.getAddressID()
            switchChooseDefault.setOnCheckedChangeListener { compoundButton, isChecked ->
                if(isChecked){
                    sharedPreferences.saveAddressID(addressID)
                }else{
                    sharedPreferences.saveAddressID(defaultAddrID.toString())
                }
            }

        }
    }
    private fun checkData(userName: String, phoneNumber: String, province: String, district: String,
                          commune: String, addressDetail: String): Boolean{
        return (userName!="" && phoneNumber !="" && province!=""
                && district!="" && commune!="" && addressDetail!="")
    }


    override fun bindData() {
        if(address!=null){
            binding.apply {
                editTextUsername.setText(address?.name)
                editTextPhoneNumber.setText(address?.phoneNumber)
                actvProvince.setText(address?.province)
                actvDistrict.setText(address?.district)
                actvCommuneOrAward.setText(address?.communeOrAward)
                txtipAddressDetail.setText(address?.detailDescription)
                if(address?.id==sharedPreferences.getAddressID()){
                    switchChooseDefault.isChecked = true
                }
            }
            addressID = address?.id.toString()
        }else{
            addressID = Random.generateAddressID()
        }
    }

    override fun destroy() {

    }
}