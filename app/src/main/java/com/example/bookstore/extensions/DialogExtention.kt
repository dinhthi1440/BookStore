package com.example.bookstore.extensions

import android.app.Dialog
import android.graphics.Color.TRANSPARENT
import android.graphics.drawable.ColorDrawable
import android.os.Handler
import android.os.Looper
import android.view.WindowManager
import android.view.Gravity
import android.graphics.Color
import com.example.bookstore.R
import com.example.bookstore.databinding.DlAnimationSuccessBinding
import com.example.bookstore.databinding.DlConfirmingPurchaseQuaniyBinding

fun Dialog.openDlSuccess(stopFlag: Boolean = false) {
    val binding = DlAnimationSuccessBinding.inflate(layoutInflater)
    setContentView(binding.root)
    window?.apply {
        setLayout(
            WindowManager.LayoutParams.MATCH_PARENT,
            WindowManager.LayoutParams.WRAP_CONTENT
        )
        setBackgroundDrawable(ColorDrawable(TRANSPARENT))
        attributes.apply {
            gravity = Gravity.CENTER
        }
    }
    Handler(Looper.getMainLooper()).postDelayed({
        this.dismiss()
    },1700 )
    binding.lottie.animate().setDuration(1700).startDelay = 0

    setCancelable(stopFlag)
    show()
}

fun Dialog.confirmNumberPurchase(){
    val binding = DlConfirmingPurchaseQuaniyBinding.inflate(layoutInflater)
    setContentView(binding.root)
    show()
    window?.apply {
        setLayout(
            WindowManager.LayoutParams.MATCH_PARENT,
            WindowManager.LayoutParams.WRAP_CONTENT
        )
        setBackgroundDrawable(ColorDrawable(TRANSPARENT))
        attributes.apply {
            windowAnimations = R.style.DialogAnimation
        }
        setGravity(Gravity.BOTTOM)
    }
    binding.apply {
        var quanity = txtvQuantity.text.toString().toInt()
        btnMinus.setOnClickListener {
            if(quanity > 0){
                quanity -= 1
                txtvQuantity.text = quanity.toString()
            }
        }
        btnPlus.setOnClickListener {
            quanity += 1
            txtvQuantity.text = quanity.toString()
        }
    }
    binding.btnConfirm.setOnClickListener {
        this.dismiss()
    }

}

