package com.sahil.dailyneed.user.model

data class MyTokenModel(
    val data: List<DataMyTokenModel>,
    val msg: String,
    val result: Int
)

data class DataMyTokenModel(
    val shop_name: String,
    val time: String,
    val token_id: String,
    val user_name: String
)