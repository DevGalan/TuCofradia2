package com.devgalan.tucofradia2.ui.main.serverList

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
    private val btnJoin = view.findViewById<Button>(R.id.btnJoin)

    private val btnEdit = view.findViewById<Button>(R.id.btnEdit)
    private val btnLeave = view.findViewById<Button>(R.id.btnLeave)

    fun render(server: Server, position: Int, onJoinButtonPressed: (Server) -> Unit) { //onClick: (Server, Int) -> Unit
        tvServerName.text = server.name
        tvServerDescription.text = server.description
        tvServerCode.text = tvServerName.context.getString(R.string.server_code, server.code)
        tvServerAmountPlayers.text = tvServerName.context.getString(R.string.players, server.amountPlayers, server.maxPlayers)

        val layoutParams = LinearLayout.LayoutParams(
            LinearLayout.LayoutParams.MATCH_PARENT,
            LinearLayout.LayoutParams.WRAP_CONTENT
        )
        if (position == 0) layoutParams.setMargins(0, UnitConverser.dpToPx(16, view), 0, UnitConverser.dpToPx(16, view))
        else layoutParams.setMargins(0, 0, 0, UnitConverser.dpToPx(16, view))
        view.layoutParams = layoutParams

        btnJoin.setOnClickListener {
            onJoinButtonPressed(server)
        }

        btnEdit.visibility = View.GONE
        btnLeave.visibility = View.GONE
    }
}