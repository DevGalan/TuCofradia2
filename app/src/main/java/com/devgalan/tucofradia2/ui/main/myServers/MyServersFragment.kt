package com.devgalan.tucofradia2.ui.main.myServers

import android.app.Dialog
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.CheckBox
import android.widget.EditText
import androidx.core.widget.addTextChangedListener
import androidx.fragment.app.viewModels
import androidx.lifecycle.viewModelScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.devgalan.tucofradia2.R
import com.devgalan.tucofradia2.data.ResultActions
import com.devgalan.tucofradia2.data.dto.server.JoinServerDto
import com.devgalan.tucofradia2.data.model.server.Server
import com.devgalan.tucofradia2.databinding.FragmentMyServersBinding
import com.devgalan.tucofradia2.ui.main.news.NewsAdapter
import com.devgalan.tucofradia2.ui.main.serverList.ServerListAdapter
import com.google.android.material.floatingactionbutton.FloatingActionButton
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

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

        initUI()

        return binding.root
    }

    private fun initUI() {
        initRecyclerView()
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
        val leaveServerDialog = Dialog(binding.rvServerList.context)
        leaveServerDialog.setContentView(R.layout.dialog_edit_server)

        val btnSave = leaveServerDialog.findViewById<Button>(R.id.btnSave)
        val fabCancel = leaveServerDialog.findViewById<FloatingActionButton>(R.id.fabCancel)
        val etDescription = leaveServerDialog.findViewById<EditText>(R.id.etDescription)
        val etPassword = leaveServerDialog.findViewById<EditText>(R.id.etPassword)

        btnSave.setOnClickListener {
            myServersViewModel.editServer(server.id, etPassword.text.toString(), etDescription.text.toString(), ResultActions({
                myServersViewModel.setMyServers(myServersViewModel.getMyServers().value?.map {
                    if (it.id == server.id) {
                        it.description = etDescription.text.toString()
                        it.public = etPassword.text.toString().isEmpty()
                    }
                    it
                } ?: emptyList())
                myServersAdapter.updateServers(myServersViewModel.getMyServers().value ?: emptyList())
            }, {
                println("Error: $it")
            }))
        }

        fabCancel.setOnClickListener {
            leaveServerDialog.dismiss()
        }
    }

    private fun showLeaveDialog(serverId: Long) {
        val leaveServerDialog = Dialog(binding.rvServerList.context)
        leaveServerDialog.setContentView(R.layout.dialog_leave_server)

        val btnLeave = leaveServerDialog.findViewById<Button>(R.id.btnLeave)
        val fabCancel = leaveServerDialog.findViewById<FloatingActionButton>(R.id.fabCancel)

        btnLeave.setOnClickListener {
            myServersViewModel.leaveServer(serverId, ResultActions({
                myServersViewModel.setMyServers(myServersViewModel.getMyServers().value?.filter {
                    it.id != serverId
                } ?: emptyList())
                myServersAdapter.updateServers(myServersViewModel.getMyServers().value ?: emptyList())
            }, {
                println("Error: $it")
            }))
        }

        fabCancel.setOnClickListener {
            leaveServerDialog.dismiss()
        }
    }

    private fun navigateToGameScreen(server: Server) {
        myServersViewModel.setJoinedServer(server)
    }
}