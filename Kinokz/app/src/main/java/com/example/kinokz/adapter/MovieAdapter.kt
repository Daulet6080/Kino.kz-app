package com.example.kinokz.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.kinokz.R
import com.example.kinokz.model.GeneralSection
import com.example.kinokz.model.HeaderSection
import com.example.kinokz.model.NowPlayingSection
import com.example.kinokz.model.Section

class MovieAdapter(private val data: List<Section>) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {
    companion object {
        const val TYPE_NOW_PLAYING = 0
        const val TYPE_GENERAL = 1
        const val TYPE_HEADER = 2
    }

    override fun getItemViewType(position: Int): Int {
        return when (data[position]) {
            is NowPlayingSection -> TYPE_NOW_PLAYING
            is HeaderSection -> TYPE_HEADER
            else -> TYPE_GENERAL
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        return when (viewType) {
            TYPE_NOW_PLAYING -> NowPlayingViewHolder(inflater.inflate(R.layout.now_playing_item, parent, false))
            TYPE_HEADER -> HeaderViewHolder(inflater.inflate(R.layout.header_item, parent, false))
            else -> GeneralViewHolder(inflater.inflate(R.layout.general_item, parent, false))
        }
    }

    override fun getItemCount():  Int = data.size

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        when (holder) {
            is NowPlayingViewHolder -> holder.bind(data[position] as NowPlayingSection)
            is GeneralViewHolder -> holder.bind(data[position] as GeneralSection)
        }
    }

    class HeaderViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        fun bind(section: HeaderSection) {
            val title: TextView = itemView.findViewById(R.id.sectionTitle)
            title.text = section.title
        }
    }

    class NowPlayingViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        fun bind(section: NowPlayingSection) {
            // Инициализация горизонтального RecyclerView
            val recyclerView: RecyclerView = itemView.findViewById(R.id.recyclerViewHorizontal)
            recyclerView.layoutManager = LinearLayoutManager(itemView.context, LinearLayoutManager.HORIZONTAL, false)
            recyclerView.adapter = ImageAdapter(section.movies)
        }
    }

    class GeneralViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        fun bind(section: GeneralSection) {
            val title: TextView = itemView.findViewById(R.id.sectionTitle)
            title.text = section.title
        }
    }
}