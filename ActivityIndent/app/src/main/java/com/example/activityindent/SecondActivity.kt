package com.example.activityindent

import android.app.Activity
import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.activityindent.databinding.SecondBinding

class SecondActivity : Activity() {
    lateinit var paintList : ArrayList<Paint>

    private val binding by lazy {
        SecondBinding.inflate(layoutInflater)
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        var inintent = intent
        paintList = inintent.getSerializableExtra("PaintList") as ArrayList<Paint>

        paintList.sortBy { it.voteCount }
        val paintAdapter = RecyclerAdapter(this, paintList)
        binding.listView.adapter = paintAdapter

        val lm = LinearLayoutManager(this)
        binding.listView.layoutManager = lm
        binding.listView.setHasFixedSize(true)

        binding.btnReturn.setOnClickListener { finish() }
    }

    inner class MainListAdapter(val context: Context, val paintList: MutableList<Paint>) : BaseAdapter() {
        override fun getCount(): Int {
            return paintList.size
        }

        override fun getItem(position: Int): Any {
            return paintList[position]
        }

        override fun getItemId(position: Int): Long {
            return 0
        }

        override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {
            val view : View = LayoutInflater.from(context).inflate(R.layout.view_item, null)

            val img = view.findViewById<ImageView>(R.id.imageView)
            val title = view.findViewById<TextView>(R.id.textView)
            val rate = view.findViewById<RatingBar>(R.id.ratingBar)

            val paint = paintList[position]
            val resourceId = context.resources.getIdentifier(paint.photo, "drawable", context.packageName)
            img.setImageResource(resourceId)
            title.text = paint.title
            rate.rating = paint.rating.toFloat()

            return view
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
                    Toast.makeText(this@SecondActivity, "${paint.title} : ${paint.voteCount} VOTE!" , Toast.LENGTH_SHORT).show()
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