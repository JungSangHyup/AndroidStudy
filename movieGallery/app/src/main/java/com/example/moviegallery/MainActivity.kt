package com.example.moviegallery

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.ImageView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.moviegallery.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private val binding by lazy { ActivityMainBinding.inflate(layoutInflater) }

    val helper = SqliteHelper(this, "movie", 1)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        title = "갤러리 영화 포스터"

        for(i in 1..9){
            helper.deleteMovie(i)
        }

        for(i in 1..9){
            helper.insertMovie(Movie(i, "mov0${i}", "mov0${i}"))
        }

        val movies = helper.selectMovie()

        val movieAdapter = GalleryAdapter(this, movies)
        binding.recyclerView.adapter = movieAdapter

        val lm = LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false)
        binding.recyclerView.layoutManager = lm
        binding.recyclerView.setHasFixedSize(true)
    }
    
    inner class GalleryAdapter(val context: Context, val movies: MutableList<Movie>) : RecyclerView.Adapter<GalleryAdapter.Holder>(){
        inner class Holder(itemView: View) : RecyclerView.ViewHolder(itemView){
            val img = itemView.findViewById<ImageView>(R.id.imageView)

            fun bind(movie: Movie, context: Context){
                if(movie.image != ""){
                    val resourceId = context.resources.getIdentifier(movie.image, "drawable", context.packageName)
                    img?.setImageResource(resourceId)
                    img.setOnTouchListener { v, event ->
                        binding.ivPoster.scaleType = ImageView.ScaleType.FIT_CENTER
                        binding.ivPoster.setImageResource(resourceId)
                        false
                    }
                }

            }
        }

        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Holder {
            val view = LayoutInflater.from(context).inflate(R.layout.gallery_item, parent, false)
            return Holder(view)
        }

        override fun onBindViewHolder(holder: Holder, position: Int) {
            holder.bind(movies[position], context)
        }

        override fun getItemCount(): Int {
            return movies.size
        }
    }
}