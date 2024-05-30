package com.devgalan.tucofradia2.ui.news

import android.view.View
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.core.view.marginBottom
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.devgalan.tucofradia2.R
import com.devgalan.tucofradia2.data.model.news.News

class NewsViewHolder(private val view: View) : ViewHolder(view) {

    val cvNewsImage = view.findViewById<CardView>(R.id.cvNewsImage)
    val ivNewsImage = view.findViewById<ImageView>(R.id.ivNewsImage)
    val tvNewsTitle = view.findViewById<TextView>(R.id.tvNewsTitle)
    val tvNewsBody = view.findViewById<TextView>(R.id.tvNewsBody)
    val tvNewsDate = view.findViewById<TextView>(R.id.tvNewsDate)

    fun render(news: News, position: Int) {
        showImage(news.imagePath)
        tvNewsTitle.text = news.title
        tvNewsBody.text = news.body
        tvNewsDate.text = news.date
        if (position != 0) {
            val layoutParams = LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT,
                LinearLayout.LayoutParams.WRAP_CONTENT
            )
            layoutParams.setMargins(0, 32, 0, 0)
            view.layoutParams = layoutParams
        }
    }

    private fun showImage(imagePath: String?) {
        if (imagePath.isNullOrEmpty()) {
            cvNewsImage.visibility = View.GONE
        } else {
            cvNewsImage.visibility = View.VISIBLE
            Glide.with(view)
                .load(imagePath)
                .apply(
                    RequestOptions()
                        .placeholder(R.drawable.image_default_profile)
                        .error(R.drawable.image_default_profile)
                )
                .into(ivNewsImage)
        }
    }
}