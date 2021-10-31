package com.example.mediaplayer

import android.media.MediaPlayer
import android.net.Uri
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.mediaplayer.databinding.ItemRecyclerBinding
import java.text.SimpleDateFormat

class MusicRecyclerAdapter : RecyclerView.Adapter<MusicRecyclerAdapter.Holder>() {
    var musicList = mutableListOf<Music>()
    lateinit var mediaPlayer: MediaPlayer

    init {

    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Holder {
        val binding = ItemRecyclerBinding.inflate(
            LayoutInflater.from(parent.context), parent, false)
        return Holder(binding)
    }

    override fun getItemCount(): Int {
        return musicList.size
    }

    override fun onBindViewHolder(holder: Holder, position: Int) {
        val music = musicList.get(position)
        holder.setMusic(music)
    }

    inner class Holder(val binding: ItemRecyclerBinding) : RecyclerView.ViewHolder(binding.root) {
        var musicUri: Uri? = null

        init {
            binding.root.setOnClickListener {
                if(mediaPlayer?.isPlaying == true){
                    mediaPlayer?.stop()
                }else {
                    mediaPlayer = MediaPlayer.create(binding.root.context, musicUri)
                    mediaPlayer?.start()
                }
            }
        }

        fun setMusic(music:Music) {
            binding.run {


                imageAlbum.setImageURI(music.getAlbumUri())
                textTitle.text = music.title


                val duration = SimpleDateFormat("mm:ss").format(music.duration)
                tvTime.text = duration
            }
            this.musicUri = music.getMusicUri()
        }
    }
}