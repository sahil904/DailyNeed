package com.sahil.dailyneed.user.model

data class ShopListModel(
    val data: Any,
    val msg: String,
    val result: Int
)

data class DataShopListModel(
    val city_name: String,
    val image_url: String,
    val shop_id: String,
    val shop_name: String,
    val shop_type: String
)