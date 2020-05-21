package com.sahil.dailyneed.shop.model


data class ItemModel(
    val `data`: MutableList<Items>,
    val msg: String,
    val result: Int
)

data class Items(
    val item_id: String,
    val shop_id: String,
    val item_name: String
)