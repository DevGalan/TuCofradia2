package com.devgalan.tucofradia2.ui.serverList

import android.view.View
import android.widget.Button
import android.widget.LinearLayout
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.devgalan.tucofradia2.R
import com.devgalan.tucofradia2.core.help.UnitConverser
import com.devgalan.tucofradia2.data.model.server.Server

class ServerListViewHolder(private val view: View): ViewHolder(view) {

    private val tvServerName = view.findViewById<TextView>(R.id.tvServerName)
    private val tvServerDescription = view.findViewById<TextView>(R.id.tvServerDescription)
    private val tvServerCode = view.findViewById<TextView>(R.id.tvServerCode)
    private val tvServerAmountPlayers = view.findViewById<TextView>(R.id.tvServerAmountPlayers)
    private val btnJoinServer = view.findViewById<Button>(R.id.btnJoinServer)

    fun render(server: Server) { //onClick: (Server, Int) -> Unit
        tvServerName.text = server.name
        tvServerDescription.text = server.description
        tvServerCode.text = tvServerName.context.getString(R.string.server_code, server.code)
        tvServerAmountPlayers.text = tvServerName.context.getString(R.string.players, server.amountPlayers, server.maxPlayers)
        //btnJoinServer.setOnClickListener { onClick(server, position) }

        val layoutParams = LinearLayout.LayoutParams(
            LinearLayout.LayoutParams.MATCH_PARENT,
            LinearLayout.LayoutParams.WRAP_CONTENT
        )
        layoutParams.setMargins(0, 0, 0, UnitConverser.dpToPx(16, view))
        view.layoutParams = layoutParams
    }
}