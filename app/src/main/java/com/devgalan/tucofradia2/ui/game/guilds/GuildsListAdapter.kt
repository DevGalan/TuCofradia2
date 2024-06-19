package com.devgalan.tucofradia2.ui.game.guilds

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.devgalan.tucofradia2.R
import com.devgalan.tucofradia2.data.model.guild.Guild

class GuildsListAdapter(private var guildsList: List<Guild>) : RecyclerView.Adapter<GuildsListViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): GuildsListViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        return GuildsListViewHolder(layoutInflater.inflate(R.layout.item_guild, parent, false))
    }

    override fun getItemCount() = guildsList.size

    override fun onBindViewHolder(holder: GuildsListViewHolder, position: Int) {
        holder.render(guildsList[position])
    }

    fun updateGuilds(guildsList: List<Guild>) {
        this.guildsList = guildsList
        notifyDataSetChanged()
    }
}