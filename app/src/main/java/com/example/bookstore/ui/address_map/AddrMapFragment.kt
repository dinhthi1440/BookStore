package com.example.bookstore.ui.address_map

import androidx.lifecycle.ViewModelProvider
import com.example.bookstore.R
import com.example.bookstore.base.BaseFragment
import com.example.bookstore.base.BaseViewModel
import com.example.bookstore.databinding.FragmentAddrMapBinding
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.MapView
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment


class AddrMapFragment :BaseFragment<FragmentAddrMapBinding>(FragmentAddrMapBinding::inflate), OnMapReadyCallback {
    override val viewModel: BaseViewModel
        get() = ViewModelProvider(this)[AddrMapViewModel::class.java]
    private var mGoogleMap: GoogleMap? = null

    override fun initData() {

    }

    override fun handleEvent() {

    }

    override fun bindData() {
    }

    override fun destroy() {

    }

    override fun onMapReady(googleMap: GoogleMap) {
        mGoogleMap = googleMap
    }
}