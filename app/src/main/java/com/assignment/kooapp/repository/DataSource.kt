package com.assignment.kooapp.repository

import com.assignment.kooapp.model.PostResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface DataSource {

    @GET("public/v1/posts")
    suspend fun getPosts(@Query("page")pageNumber : Int):PostResponse
}