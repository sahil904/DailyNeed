package com.sahil.dailyneed.shop.model

data class ShopUser (
    val result: Int,
    val msg: String,
    val data: List<shopdetails>
)

data class shopdetails (
    val shop_id: String,
    val shop_name: String,
    val shop_type: String,
    val city_name: String,
    val imageUrl: String?
)