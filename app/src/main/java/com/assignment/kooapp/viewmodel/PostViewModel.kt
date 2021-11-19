package com.assignment.kooapp.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.assignment.kooapp.model.PostResponse
import com.assignment.kooapp.repository.Repository
import kotlinx.coroutines.launch

class PostViewModel : ViewModel() {
    private val repo = Repository()

    val posts: MutableLiveData<PostResponse> = MutableLiveData()

    fun getPosts(pageNumber: Int) {
        viewModelScope.launch {
            posts.value = repo.getPosts(pageNumber)
        }
    }
}