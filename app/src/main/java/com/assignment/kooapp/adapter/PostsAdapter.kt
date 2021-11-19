package com.assignment.kooapp.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.assignment.kooapp.databinding.CustomPostBinding
import com.assignment.kooapp.model.Data

class PostsAdapter : RecyclerView.Adapter<PostsAdapter.PostViewHolder>() {

    private val posts: ArrayList<Data> by lazy {
        ArrayList()
    }

    private var onLoadMoreListener: OnLoadMoreListener? = null
    private var isLoading: Boolean = false
    private var visibilityThreshHold: Int = 1
    private var totalItemCount: Int = 1

    fun setTotalItems(count: Int) {
        totalItemCount = count
    }

    fun setLoadMoreListener(loadMoreListener: OnLoadMoreListener) {
        this.onLoadMoreListener = loadMoreListener
    }

    fun setVisibilityThreshHold(threshHolder: Int) {
        visibilityThreshHold = threshHolder
    }

    fun attachScrollListener(recyclerView: RecyclerView) {
        recyclerView.addOnScrollListener(object : RecyclerView.OnScrollListener() {
            override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                super.onScrolled(recyclerView, dx, dy)
                val linearLayoutManager = recyclerView.layoutManager as LinearLayoutManager
                val lastVisibleItem = linearLayoutManager.findLastVisibleItemPosition()
                if (!isLoading && ((lastVisibleItem+1) % visibilityThreshHold == 0) && (lastVisibleItem < totalItemCount)) {
                    if (onLoadMoreListener != null) {
                        onLoadMoreListener!!.onLoadMore()
                    }
                    isLoading = true
                }
            }
        })
    }


    interface OnLoadMoreListener {
        fun onLoadMore()
    }

    fun addPosts(morePosts: ArrayList<Data>) {
        val size = posts.size
        posts.addAll(morePosts)
        notifyItemRangeInserted(size - 1, morePosts.size)
    }

    class PostViewHolder(postBinding: CustomPostBinding) :
        RecyclerView.ViewHolder(postBinding.root) {
        val post = postBinding
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PostViewHolder {
        val postBinding = CustomPostBinding.inflate(LayoutInflater.from(parent.context))
        return PostViewHolder(postBinding)
    }

    override fun onBindViewHolder(holder: PostViewHolder, position: Int) {
        holder.post.post = posts[position]
    }

    override fun getItemCount(): Int = posts.size
}