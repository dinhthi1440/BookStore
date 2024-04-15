package com.example.bookstore.data.repository.voucher

import com.example.bookstore.base.BaseReponsitory
import com.example.bookstore.base.DataResult
import com.example.bookstore.data.service.IFirebaseSource
import com.example.bookstore.models.Voucher

class VoucherRepository(private val cloud: IFirebaseSource.CloudStore): BaseReponsitory(),IVoucherRepository  {
    override suspend fun addNewVoucher(voucher: Voucher): DataResult<Int> {
        return getResult { cloud.addNewVoucher(voucher) }
    }

    override suspend fun getVoucher(): DataResult<List<Voucher>> {
        return getResult { cloud.getVoucher() }
    }
}