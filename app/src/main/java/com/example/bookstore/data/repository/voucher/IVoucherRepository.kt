package com.example.bookstore.data.repository.voucher

import com.example.bookstore.base.DataResult
import com.example.bookstore.models.Voucher

interface IVoucherRepository {
    suspend fun addNewVoucher(voucher: Voucher): DataResult<Int>
    suspend fun getVoucher(): DataResult<List<Voucher>>
}