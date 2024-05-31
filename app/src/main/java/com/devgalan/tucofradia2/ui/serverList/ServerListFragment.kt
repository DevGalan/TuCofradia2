package com.devgalan.tucofradia2.ui.serverList

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.widget.addTextChangedListener
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.devgalan.tucofradia2.R
import com.devgalan.tucofradia2.data.model.server.Server
import com.devgalan.tucofradia2.databinding.FragmentServerListBinding
import com.devgalan.tucofradia2.ui.news.NewsAdapter
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ServerListFragment : Fragment() {

    private val serverListViewModel by viewModels<ServerListViewModel>()
    private lateinit var serverListAdapter: ServerListAdapter

    private lateinit var binding: FragmentServerListBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentServerListBinding.inflate(inflater, container, false)

        initListeners()

        initUI()

        return binding.root
    }

    private fun initListeners() {
        binding.etSearch.addTextChangedListener { query ->
            val filteredServers = serverListViewModel.filterServerList(query.toString())
            serverListAdapter.updateServers(filteredServers)
        }
    }

    private fun initUI() {
        serverListAdapter = ServerListAdapter(serverListViewModel.getServerList())
        initRecyclerView()
    }

    private fun initRecyclerView() {
        binding.rvServerList.layoutManager = LinearLayoutManager(binding.rvServerList.context)
        binding.rvServerList.adapter = serverListAdapter
    }
}