package com.example.exoplayer.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.exoplayer.databinding.ItemVideoBinding
import com.example.exoplayer.model.VideoModel


class VideoAdapter(val callback: (String, String) -> Unit) :
    RecyclerView.Adapter<VideoAdapter.ViewHolder>() {

    private var videoList: List<VideoModel> = listOf()


    inner class ViewHolder(private val binding: ItemVideoBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(item: VideoModel) = with(binding) {

            titleTextView.text = item.title
            subTitleTextView.text = item.subtitle

            Glide.with(thumbnailImageView.context)
                .load(item.thumb)
                .into(thumbnailImageView)

            root.setOnClickListener {
                callback(item.sources, item.title)
            }

        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {

        val view = ItemVideoBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {

        holder.bind(videoList[position])
    }

    override fun getItemCount(): Int = videoList.size

    fun setList(videoList: List<VideoModel>) {
        this.videoList = videoList
        notifyDataSetChanged()
    }


}