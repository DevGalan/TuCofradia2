package com.devgalan.tucofradia2.ui.serverList

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.devgalan.tucofradia2.R
import com.devgalan.tucofradia2.data.model.server.Server

class ServerListAdapter(private var serverList: List<Server>) : RecyclerView.Adapter<ServerListViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ServerListViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        return ServerListViewHolder(layoutInflater.inflate(R.layout.item_server, parent, false))
    }

    override fun getItemCount() = serverList.size

    override fun onBindViewHolder(holder: ServerListViewHolder, position: Int) {
        holder.render(serverList[position], position)
    }

    fun updateServers(serverList: List<Server>) {
        this.serverList = serverList
        notifyDataSetChanged()
    }
}