package com.sahil.dailyneed.user.model

data class RequestModel(
    val data: UserRequestModel,
    val msg: String,
    val result: Int
)

data class UserRequestModel(
    val time: String,
    val token_id: String,
    val user_name: String
)