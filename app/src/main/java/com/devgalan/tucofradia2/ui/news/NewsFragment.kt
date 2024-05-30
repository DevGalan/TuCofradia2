package com.devgalan.tucofradia2.ui.news

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.devgalan.tucofradia2.R
import com.devgalan.tucofradia2.data.model.news.NewsProvider
import com.devgalan.tucofradia2.databinding.FragmentNewsBinding
import com.devgalan.tucofradia2.databinding.FragmentProfileBinding
import com.devgalan.tucofradia2.ui.profile.ProfileViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class NewsFragment : Fragment() {

    private val newsViewModel by viewModels<NewsViewModel>()

    private lateinit var binding: FragmentNewsBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentNewsBinding.inflate(inflater, container, false)

        initUI()

        return binding.root
    }

    private fun initUI() {
        initRecyclerView()
    }

    private fun initRecyclerView() {
        binding.rvNews.layoutManager = LinearLayoutManager(binding.rvNews.context)
        binding.rvNews.adapter = NewsAdapter(NewsProvider().news)
    }
}