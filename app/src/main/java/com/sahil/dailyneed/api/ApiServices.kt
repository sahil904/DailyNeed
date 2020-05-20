package com.sahil.dailyneed.activity.api

import com.sahil.dailyneed.shop.model.RegisterModel
import com.sahil.dailyneed.shop.model.UserRequestModel
import com.sahil.dailyneed.user.model.ShopListModel
import okhttp3.MultipartBody
import okhttp3.RequestBody
import retrofit2.http.*

interface ApiServices {

    @FormUrlEncoded
    @POST("register")
    fun registration(
        @Field("full_name") name: String,
        @Field("email") email: String,
        @Field("mobile_no") phone: String,
        @Field("password") password: String,
        @Field("confirm_password") confirmpassword: String,
        @Field("token_id") token_id: String,
        @Field("type") type: String,
        @Field("source") source: String,
        @Field("lat") lat: String,
        @Field("lang") lang: String

    ): retrofit2.Call<RegisterModel>


    @FormUrlEncoded
    @POST("login")
    fun login(
        @Field("email") email: String,
        @Field("password") password: String,
        @Field("token_id") token_id: String,
        @Field("type") type: String,
        @Field("lat") lat: String,
        @Field("lang") lang: String

    ): retrofit2.Call<RegisterModel>

    @FormUrlEncoded
    @POST("shopKeeperToken")
    fun shopKeeperToken(
        @Field("shop_id") shop_id: String
    ): retrofit2.Call<UserRequestModel>

    @GET("shopListing")
    fun shopListing(): retrofit2.Call<ShopListModel>

    @FormUrlEncoded
    @POST("shopBooking")
    fun shopBooking(
        @Field("shop_id") shop_id: String,
        @Field("user_id") user_id: String
    ): retrofit2.Call<UserRequestModel>


    @Multipart
    @POST("addShop")
    fun addShop(
        @Part("shop_name") shop_name: RequestBody, @Part("shop_type") shop_type: RequestBody,
        @Part("user_id") user_id: RequestBody,
        @Part("city_name") city_name: RequestBody,
        @Part image_url: MultipartBody.Part?
    ): retrofit2.Call<RegisterModel>


}


