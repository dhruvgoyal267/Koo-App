package com.assignment.kooapp.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.RecoverySystem
import android.view.View
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.RecyclerView
import com.assignment.kooapp.adapter.PostsAdapter
import com.assignment.kooapp.databinding.ActivityMainBinding
import com.assignment.kooapp.fillPosts
import com.assignment.kooapp.viewmodel.PostViewModel

class MainActivity : AppCompatActivity(), PostsAdapter.OnLoadMoreListener {

    private val vm by lazy {
        ViewModelProvider(this)[PostViewModel::class.java]
    }

    private var pageNumber: Int = 1
    lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)


        getPosts()
    }

    private fun getPosts() {
        vm.getPosts(pageNumber)
        vm.posts.observe(this, {
            binding.posts.fillPosts(it)
            if (binding.posts.adapter != null)
                (binding.posts.adapter as PostsAdapter).setLoadMoreListener(this)
        })
    }

    override fun onLoadMore() {
        pageNumber++
        vm.getPosts(pageNumber)
    }
}
