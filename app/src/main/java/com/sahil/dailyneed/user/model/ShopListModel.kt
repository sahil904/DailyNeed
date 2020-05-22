package com.sahil.dailyneed.user.model

data class ShopListModel(
    val result: Int,
    val msg: String,
    val data: List<DataShopListModel>

)

data class DataShopListModel(
    val city_name: String,
    val image_url: String,
    val shop_id: String,
    val shop_name: String,
    val shop_type: String,
    val lat: String,
    val long: String
)