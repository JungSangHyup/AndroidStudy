package com.example.activityindent

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.activityindent.databinding.ActivityMainBinding
import com.example.activityindent.databinding.ViewItemBinding
import java.io.Serializable

class MainActivity : AppCompatActivity(){
    private val binding by lazy { ActivityMainBinding.inflate(layoutInflater) }
    var paintList = arrayListOf<Paint>(
        Paint("img1", "경악", 4.0F, 0),
        Paint("img2", "ㅁㄴㅇㄹ", 3.0F, 0),
        Paint("img3", "ㅈㄷㄹ", 2.0F, 0)
    )
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        paintList.sortBy { it.voteCount }
        val paintAdapter = RecyclerAdapter(this, paintList)
        binding.listView.adapter = paintAdapter

        val lm = LinearLayoutManager(this)
        binding.listView.layoutManager = lm
        binding.listView.setHasFixedSize(true)

        binding.btnNewActivity.setOnClickListener {
            var intent = Intent(applicationContext, SecondActivity::class.java)
            intent.putExtra("PaintList", paintList)
            startActivity(intent)
        }


    }

    inner class RecyclerAdapter(val context: Context, val paintList: MutableList<Paint>) : RecyclerView.Adapter<RecyclerAdapter.Holder>() {
        inner class Holder(itemView: View?) : RecyclerView.ViewHolder(itemView!!){
            val img = itemView?.findViewById<ImageView>(R.id.imageView)
            val title = itemView?.findViewById<TextView>(R.id.textView)
            val rate = itemView?.findViewById<RatingBar>(R.id.ratingBar)
            val btnvote = itemView?.findViewById<Button>(R.id.btnVote)

            fun bind(paint: Paint, context: Context) {
                if(paint.photo != ""){
                    val resourceId = context.resources.getIdentifier(paint.photo, "drawable", context.packageName)
                    img?.setImageResource(resourceId)
                }
                title?.text = paint.title
                rate?.rating = paint.rating
                btnvote?.setOnClickListener {
                    paint.voteCount++
                    paint.rating = rate?.rating!!
                    Toast.makeText(this@MainActivity, "${paint.title} : ${paint.voteCount} VOTE!" , Toast.LENGTH_SHORT).show()
                }
            }
        }

        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Holder {
            val view = LayoutInflater.from(context).inflate(R.layout.view_item, parent, false)
            return Holder(view)
        }

        override fun onBindViewHolder(holder: Holder, position: Int) {
            holder?.bind(paintList[position], context)
        }

        override fun getItemCount(): Int {
            return paintList.size
        }
    }
}