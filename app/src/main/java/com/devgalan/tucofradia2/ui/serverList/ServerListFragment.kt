package com.devgalan.tucofradia2.ui.serverList

import android.app.Dialog
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.CheckBox
import android.widget.EditText
import androidx.core.widget.addTextChangedListener
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.devgalan.tucofradia2.R
import com.devgalan.tucofradia2.data.model.server.Server
import com.devgalan.tucofradia2.databinding.FragmentServerListBinding
import com.google.android.material.floatingactionbutton.FloatingActionButton
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ServerListFragment : Fragment() {

    private val serverListViewModel by viewModels<ServerListViewModel>()

    private lateinit var serverListAdapter: ServerListAdapter
    private lateinit var filterDialog: Dialog

    private lateinit var binding: FragmentServerListBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentServerListBinding.inflate(inflater, container, false)

        serverListViewModel.onCreate()

        initDialog()

        initListeners()

        initUI()

        return binding.root
    }

    private fun initDialog() {
        filterDialog = Dialog(binding.rvServerList.context)
        filterDialog.setContentView(R.layout.dialog_filter_server)

        val btnClear = filterDialog.findViewById<Button>(R.id.btnClear)
        val etName = filterDialog.findViewById<EditText>(R.id.etName)
        val etCode = filterDialog.findViewById<EditText>(R.id.etCode)
        val cbPublic = filterDialog.findViewById<CheckBox>(R.id.cbPublic)
        val cbFull = filterDialog.findViewById<CheckBox>(R.id.cbFull)
        val fabCancel = filterDialog.findViewById<FloatingActionButton>(R.id.fabCancel)

        btnClear.setOnClickListener {
            etName.text.clear()
            etCode.text.clear()
            cbPublic.isChecked = false
        }

        fabCancel.setOnClickListener {
            filterDialog.dismiss()
        }

        etName.hint = etName.context.getString(R.string.server_name)
        etCode.hint = etName.context.getString(R.string.server_code_hint)

        etName.addTextChangedListener { query ->
            val filteredServers = serverListViewModel.filterServerList(
                query.toString(),
                etCode.text.toString(),
                cbPublic.isChecked,
                cbFull.isChecked
            )
            serverListAdapter.updateServers(filteredServers)
        }

        etCode.addTextChangedListener { query ->
            val filteredServers = serverListViewModel.filterServerList(
                etName.text.toString(),
                query.toString(),
                cbPublic.isChecked,
                cbFull.isChecked
            )
            serverListAdapter.updateServers(filteredServers)
        }

        cbPublic.setOnCheckedChangeListener { _, isChecked ->
            val filteredServers = serverListViewModel.filterServerList(
                etName.text.toString(),
                etCode.text.toString(),
                isChecked,
                cbFull.isChecked
            )
            serverListAdapter.updateServers(filteredServers)
        }

        cbFull.setOnCheckedChangeListener { _, isChecked ->
            val filteredServers = serverListViewModel.filterServerList(
                etName.text.toString(),
                etCode.text.toString(),
                cbPublic.isChecked,
                isChecked
            )
            serverListAdapter.updateServers(filteredServers)
        }
    }

    private fun initListeners() {
        binding.btnFilters.setOnClickListener {
            showDialog()
        }
    }

    private fun showDialog() {
        filterDialog.show()
    }

    private fun initUI() {
        val filteredServers = serverListViewModel.filterServerList("", "", true, false)
        serverListAdapter = ServerListAdapter(filteredServers) { server ->
            serverListViewModel.joinServer(server)
        }
        initRecyclerView()
    }

    private fun initRecyclerView() {
        binding.rvServerList.layoutManager = LinearLayoutManager(binding.rvServerList.context)
        binding.rvServerList.adapter = serverListAdapter
    }
}