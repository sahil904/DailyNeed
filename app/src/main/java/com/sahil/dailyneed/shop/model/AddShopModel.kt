package com.sahil.dailyneed.shop.model

data class AddShopModel(
    val `data`: List<DataAddShopModel>,
    val msg: String,
    val result: Int
)

data class DataAddShopModel(
    val city_name: String,
    val image_url: String,
    val shop_id: String,
    val shop_name: String,
    val shop_type: String
)