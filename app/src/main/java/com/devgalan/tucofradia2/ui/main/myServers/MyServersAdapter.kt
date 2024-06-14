package com.devgalan.tucofradia2.ui.main.myServers

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.devgalan.tucofradia2.R
import com.devgalan.tucofradia2.data.model.server.Server

class MyServersAdapter(private var myServersList: List<Server>, private val onLeaveButtonPressed: (Server) -> Unit, private val onEditButtonPressed: (Server) -> Unit, private val onJoinButtonPressed: (Server) -> Unit) : RecyclerView.Adapter<MyServersViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyServersViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        return MyServersViewHolder(layoutInflater.inflate(R.layout.item_server, parent, false))
    }

    override fun getItemCount() = myServersList.size

    override fun onBindViewHolder(holder: MyServersViewHolder, position: Int) {
        holder.render(myServersList[position], position, onLeaveButtonPressed, onEditButtonPressed, onJoinButtonPressed)
    }

    fun updateServers(myServersList: List<Server>) {
        this.myServersList = myServersList
        notifyDataSetChanged()
    }
}