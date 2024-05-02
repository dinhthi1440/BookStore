package com.example.bookstore.util

import java.text.SimpleDateFormat
import java.util.Date
import java.util.concurrent.TimeUnit

class Utils {
    companion object {
        fun getTime(): String {
            val format = SimpleDateFormat("HH:mm:ss")
            val date = Date(System.currentTimeMillis())
            return format.format(date)
        }
        fun getDate(): String{
            val format = SimpleDateFormat("yyyy-MM-dd")
            val date = Date(System.currentTimeMillis())
            return format.format(date)
        }
        fun calculateMinutesFrom(dateTime: String, date: String = getDate(), time: String = getTime()): Long {
            val dateTimeFormat = SimpleDateFormat("yyyy-MM-dd HH:mm:ss")
            val currentDateTime = "$date $time"
            val startDate = dateTimeFormat.parse(dateTime)
            val endDate = dateTimeFormat.parse(currentDateTime)
            val diffInMilliSeconds = endDate.time - startDate.time
            return TimeUnit.MILLISECONDS.toMinutes(diffInMilliSeconds)
        }
        fun getNameAllProvince(): List<String>{
            return listOf(
                "An Giang", "Bà Rịa - Vũng Tàu", "Bắc Giang", "Bắc Kạn", "Bạc Liêu",
                "Bắc Ninh", "Bến Tre", "Bình Định", "Bình Dương", "Bình Phước",
                "Bình Thuận", "Cà Mau", "Cần Thơ", "Cao Bằng", "Đà Nẵng", "Đắk Lắk",
                "Đắk Nông", "Điện Biên", "Đồng Nai", "Đồng Tháp", "Gia Lai",
                "Hà Giang", "Hà Nam", "Hà Nội", "Hà Tĩnh", "Hải Dương", "Hải Phòng",
                "Hậu Giang", "Hòa Bình", "Hưng Yên", "Khánh Hòa", "Kiên Giang",
                "Kon Tum", "Lai Châu", "Lâm Đồng", "Lạng Sơn", "Lào Cai",
                "Long An", "Nam Định", "Nghệ An", "Ninh Bình", "Ninh Thuận",
                "Phú Thọ", "Phú Yên", "Quảng Bình", "Quảng Nam", "Quảng Ngãi",
                "Quảng Ninh", "Quảng Trị", "Sóc Trăng", "Sơn La", "Tây Ninh",
                "Thái Bình", "Thái Nguyên", "Thanh Hóa", "Thừa Thiên Huế",
                "Tiền Giang", "Trà Vinh", "Tuyên Quang", "Vĩnh Long", "Vĩnh Phúc",
                "Yên Bái"
            )
        }
    }

}