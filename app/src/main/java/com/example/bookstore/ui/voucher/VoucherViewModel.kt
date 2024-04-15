package com.example.bookstore.ui.voucher

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.bookstore.base.BaseViewModel
import com.example.bookstore.data.repository.voucher.IVoucherRepository
import com.example.bookstore.models.Voucher

class VoucherViewModel(private val iVoucherRepository: IVoucherRepository):BaseViewModel() {

    private val _getVoucherResult = MutableLiveData<List<Voucher>>()
    val getVoucherResult: LiveData<List<Voucher>> get() = _getVoucherResult
    fun addNewVoucher(voucher: Voucher){
        executeTask(
            request = {iVoucherRepository.addNewVoucher(voucher)},
            onSuccess = {

            },
            onError = {}
        )
    }
    fun getVoucher(){
        executeTask(
            request = {iVoucherRepository.getVoucher()},
            onSuccess = {
                _getVoucherResult.value=it
            },
            onError = {}
        )
    }
}