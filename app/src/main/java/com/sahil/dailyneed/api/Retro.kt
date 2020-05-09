package com.designoweb.marketplace.subcontractor.activity.api

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import com.google.gson.GsonBuilder
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor


class Retro {
    companion object {

        var gson = GsonBuilder()
            .setLenient()
            .create()


        fun ApiService (): ApiServices {
           // try {

            val client = OkHttpClient.Builder()
            val loggingInterceptor = HttpLoggingInterceptor()
            loggingInterceptor.level = HttpLoggingInterceptor.Level.BODY
            client.addInterceptor(loggingInterceptor)

                return Retrofit.Builder().baseUrl("http://13.235.243.70/teksmart/api/")
                    .addConverterFactory(
                        GsonConverterFactory.create(gson))
                    .client(client.build())
                    .build()
                    .create(ApiServices::class.java)
          //  }
           /* catch (ill: IllegalStateException){
                failed =true
            }*/


        }
    }
}