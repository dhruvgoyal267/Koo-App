package com.assignment.kooapp.repository

import com.assignment.kooapp.model.PostResponse


class Repository {
    private val repo = RetrofitClient.getInstance()
    suspend fun getPosts(pageNumber: Int): PostResponse {
        return repo.getPosts(pageNumber)
    }
}