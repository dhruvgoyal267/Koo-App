package com.assignment.kooapp.repository

import androidx.databinding.ktx.BuildConfig
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class RetrofitClient {

    companion object {
        private const val URL =
            "https://gorest.co.in/"

        private var INSTANCE: DataSource? = null
        fun getInstance(): DataSource {
            if (INSTANCE == null)
                INSTANCE = Retrofit.Builder()
                    .baseUrl(URL)
                    .client(OkHttpClient.Builder().also { client ->
                        if (BuildConfig.DEBUG) {
                            val logging = HttpLoggingInterceptor()
                            logging.setLevel(HttpLoggingInterceptor.Level.BASIC)
                            client.addInterceptor(logging)
                        }
                    }.build())
                    .addConverterFactory(GsonConverterFactory.create())
                    .build()
                    .create(DataSource::class.java)
            return INSTANCE!!
        }
    }
}