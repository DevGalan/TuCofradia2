package com.devgalan.tucofradia2.ui.game.guilds

import android.view.View
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.devgalan.tucofradia2.R
import com.devgalan.tucofradia2.data.model.guild.Guild

class GuildsListViewHolder(view: View) : RecyclerView.ViewHolder(view) {

    private val guildName: TextView = view.findViewById(R.id.tvName)
    private val guildFee: TextView = view.findViewById(R.id.tvFee)
    private val guildUser: TextView = view.findViewById(R.id.tvUser)

    fun render(guild: Guild) {
        guildName.text = guild.name
        guildFee.text = "Cuota: " + guild.fee.toString()
        guildUser.text = "Usuario: " + guild.user.username
    }
}