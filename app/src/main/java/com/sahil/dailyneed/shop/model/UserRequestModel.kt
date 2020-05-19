package com.sahil.dailyneed.shop.model

data class UserRequestModel(
    val data: List<DataUserRequestModel>,
    val msg: String,
    val result: Int
)

data class DataUserRequestModel(
    val time: String,
    val token_id: String,
    val user_name: String
)