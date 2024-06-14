package com.devgalan.tucofradia2.ui.main.serverList

import android.app.Dialog
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.CheckBox
import android.widget.EditText
import android.widget.TextView
import androidx.core.widget.addTextChangedListener
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.devgalan.tucofradia2.R
import com.devgalan.tucofradia2.data.ResultActions
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
            if (server.isFull()) {
                showServerFullDialog("")
            }
            if (server.public) {
                serverListViewModel.joinServer(server.code, "", ResultActions({
                    navigateToGameScreen(server)
                }, {
                    showServerFullDialog("Error al unirte al servidor: $it")
                }))
            } else {
                showEnterPasswordDialog(server)
            }
        }
        initRecyclerView()
    }

    private fun showServerFullDialog(text: String) {
        activity?.runOnUiThread {
            val dialog = Dialog(binding.rvServerList.context)
            dialog.setContentView(R.layout.dialog_error_join_server)

            val btnBack = dialog.findViewById<Button>(R.id.btnBack)

            if (text.isNotEmpty()) {
                val tvTitle = dialog.findViewById<TextView>(R.id.tvTitle)
                tvTitle.text = text
            }

            btnBack.setOnClickListener {
                dialog.dismiss()
            }

            dialog.show()
        }
    }

    private fun showEnterPasswordDialog(server: Server) {
        val dialog = Dialog(binding.rvServerList.context)
        dialog.setContentView(R.layout.dialog_password_join_server)

        val etPassword = dialog.findViewById<EditText>(R.id.etPassword)
        val btnJoin = dialog.findViewById<Button>(R.id.btnJoin)

        btnJoin.setOnClickListener {
            dialog.dismiss()
            serverListViewModel.joinServer(server.code, etPassword.text.toString(), ResultActions({
                navigateToGameScreen(server)
            }, {
                showServerFullDialog("Error joining server: $it")
            }))
        }

        dialog.show()
    }

    private fun navigateToGameScreen(server: Server) {
        serverListViewModel.setJoinedServer(server)
    }

    private fun initRecyclerView() {
        binding.rvServerList.layoutManager = LinearLayoutManager(binding.rvServerList.context)
        binding.rvServerList.adapter = serverListAdapter
    }
}