package com.assignment.kooapp

import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.assignment.kooapp.adapter.PostsAdapter
import com.assignment.kooapp.model.Data
import com.assignment.kooapp.model.PostResponse


fun RecyclerView.fillPosts(response: PostResponse) {
    layoutManager = LinearLayoutManager(context)
    val postsAdapter = PostsAdapter().apply {
        addPosts(ArrayList(response.data))
        attachScrollListener(this@fillPosts)
        setVisibilityThreshHold(response.meta.pagination.limit)
        setTotalItems(response.meta.pagination.total)
    }
    adapter = postsAdapter
}



