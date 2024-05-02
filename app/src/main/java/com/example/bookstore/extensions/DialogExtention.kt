package com.example.bookstore.extensions

import android.annotation.SuppressLint
import android.app.Dialog
import android.graphics.Color.TRANSPARENT
import android.graphics.drawable.ColorDrawable
import android.os.Handler
import android.os.Looper
import android.view.WindowManager
import android.view.Gravity
import android.graphics.Color
import android.graphics.Paint
import android.view.View
import com.bumptech.glide.Glide
import com.example.bookstore.R
import com.example.bookstore.databinding.DlAnimationLoadingBinding
import com.example.bookstore.databinding.DlAnimationSuccessBinding
import com.example.bookstore.databinding.DlConfirmCancelOrderBinding
import com.example.bookstore.databinding.DlConfirmPasswordBinding
import com.example.bookstore.databinding.DlConfirmingPurchaseQuaniyBinding
import com.example.bookstore.databinding.DlScannedQrBinding
import com.example.bookstore.models.Book
import com.example.bookstore.models.User

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
fun Dialog.openDlLoading(stopFlag: Boolean) {
    val binding = DlAnimationLoadingBinding.inflate(layoutInflater)
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
    show()
    setCancelable(stopFlag)
}

@SuppressLint("SetTextI18n")
fun Dialog.confirmNumberPurchase(book: Book, callback: (quantity: Int) -> Unit){
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
        if(book.rating != 0.0){
            txtvPromotionPercent.text = "-" + book.rating.toString() + "%"
            txtvPrice.text = book.price.toString() +"đ"
            txtvPrice.paintFlags =  Paint.STRIKE_THRU_TEXT_FLAG
            txtvSellingPrice.text = (book.price * (100.0-book.rating) /100.0).toInt().toString() +"đ"
        }else{
            txtvPromotionPercent.visibility = View.GONE
            txtvPrice.visibility = View.GONE
            txtvSellingPrice.text = book.price.toString() +"đ"
        }
        Glide.with(root.rootView)
            .load(book.images[0])
            .into(imgBook)
    }
    binding.btnConfirm.setOnClickListener {
        callback(binding.txtvQuantity.text.toString().toInt())
        this.dismiss()
    }
}

fun Dialog.confirmPassword(pressButton: (String) -> Unit) {
    val binding = DlConfirmPasswordBinding.inflate(layoutInflater)
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

    binding.btnConfirm.setOnClickListener {
        pressButton(binding.edittextPassword.text.toString())
        this.dismiss()
    }
    binding.btnCancel.setOnClickListener {
        this.dismiss()
    }
    show()
}

fun Dialog.confirmCancelOrder(title: String , pressButton: (String) -> Unit) {
    val binding = DlConfirmCancelOrderBinding.inflate(layoutInflater)
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

    binding.txtvWarning.text = title
    binding.layoutBtnYes.setOnClickListener {
        pressButton("yes")
        this.dismiss()
    }
    binding.layoutBtnNo.setOnClickListener {
        this.dismiss()
    }
    show()
}

fun Dialog.scanQRResult(user: User, pressButton: (User, String) -> Unit) {
    val binding = DlScannedQrBinding.inflate(layoutInflater)
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
    binding.apply {
        Glide.with(context).load(user.imageUser).into(imgAvtUser)
        txtvDateBirth.text = user.dateOfBirth
        txtvGender.text = user.gender
        txtvUsername.text = user.userName
    }
    binding.imgCancel.setOnClickListener { this.dismiss() }
    binding.btnChatting.setOnClickListener {
        pressButton(user, "chatting")
        this.dismiss()
    }
    binding.btnFollow.setOnClickListener {
        pressButton(user, "follow")
        this.dismiss()
    }
    show()
}
