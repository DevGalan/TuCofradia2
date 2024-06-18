package com.devgalan.tucofradia2.ui.main.myServers

import android.app.Dialog
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.devgalan.tucofradia2.R
import com.devgalan.tucofradia2.data.ResultActions
import com.devgalan.tucofradia2.data.model.server.Server
import com.devgalan.tucofradia2.databinding.FragmentMyServersBinding
import com.google.android.material.floatingactionbutton.FloatingActionButton
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MyServersFragment : Fragment() {

    private val myServersViewModel by viewModels<MyServersViewModel>()

    private lateinit var myServersAdapter: MyServersAdapter

    private lateinit var binding: FragmentMyServersBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentMyServersBinding.inflate(inflater, container, false)

        myServersViewModel.onCreate()

        initObservers()

        initUI()

        return binding.root
    }

    private fun initObservers() {
        myServersViewModel.getMyServers().observe(viewLifecycleOwner) {
            myServersAdapter.updateServers(it)
        }
    }

    private fun initUI() {
        initListeners()
        initRecyclerView()
    }

    private fun initListeners() {
        binding.btnCreateServer.setOnClickListener {
            navigateToCreateServerScreen()
        }
    }

    private fun navigateToCreateServerScreen() {
        findNavController().navigate(R.id.action_myServersFragment_to_createServerFragment)
    }

    private fun initRecyclerView() {
        binding.rvServerList.layoutManager = LinearLayoutManager(binding.rvServerList.context)
        myServersAdapter =
            MyServersAdapter(myServersViewModel.getMyServers().value ?: emptyList(), {
                showLeaveDialog(it.id)
            }, {
                showEditDialog(it)
            }, {
                navigateToGameScreen(it)
            })
        binding.rvServerList.adapter = myServersAdapter
    }

    private fun showEditDialog(server: Server) {
        val editServerDialog = Dialog(binding.rvServerList.context)
        editServerDialog.setContentView(R.layout.dialog_edit_server)

        val btnSave = editServerDialog.findViewById<Button>(R.id.btnSave)
        val fabCancel = editServerDialog.findViewById<FloatingActionButton>(R.id.fabCancel)
        val etDescription = editServerDialog.findViewById<EditText>(R.id.etDescription)
        val etPassword = editServerDialog.findViewById<EditText>(R.id.etPassword)

        if (server.description.isNotEmpty()) {
            etDescription.setText(server.description)
        }

        btnSave.setOnClickListener {
            myServersViewModel.editServer(server.id, etPassword.text.toString(), etDescription.text.toString(), ResultActions({
                myServersViewModel.onCreate()
                editServerDialog.dismiss()
            }, {
                println("Error: $it")
            }))
        }

        fabCancel.setOnClickListener {
            editServerDialog.dismiss()
        }

        editServerDialog.show()
    }

    private fun showLeaveDialog(serverId: Long) {
        val leaveServerDialog = Dialog(binding.rvServerList.context)
        leaveServerDialog.setContentView(R.layout.dialog_leave_server)

        val btnLeave = leaveServerDialog.findViewById<Button>(R.id.btnLeave)
        val fabCancel = leaveServerDialog.findViewById<FloatingActionButton>(R.id.fabCancel)

        btnLeave.setOnClickListener {
            myServersViewModel.leaveServer(serverId, ResultActions({
                myServersViewModel.onCreate()
                leaveServerDialog.dismiss()
            }, {
                println("Error: $it")
            }))
        }

        fabCancel.setOnClickListener {
            leaveServerDialog.dismiss()
        }

        leaveServerDialog.show()
    }

    private fun navigateToGameScreen(server: Server) {
        myServersViewModel.setJoinedServer(server)
        findNavController().navigate(R.id.action_myServersFragment_to_gameActivity)
    }
}