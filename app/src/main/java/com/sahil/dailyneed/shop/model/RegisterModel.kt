package com.sahil.dailyneed.shop.model

data class RegisterModel(
    val `data`: Data,
    val msg: String,
    val result: Int
)

data class Data(
    val email: String,
    val mobile_no: String,
    val full_name: String,
    val user_id: String
)