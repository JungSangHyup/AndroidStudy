package com.example.viewpagerfragment

import android.net.Uri
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.viewpagerfragment.databinding.ItemViewpagerBinding

class CustomPagerAdapter : RecyclerView.Adapter<Holder>(){
    var textList = listOf<String>()
    var imageList = listOf<String>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Holder {
        val binding = ItemViewpagerBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return Holder(binding)
    }

    override fun getItemCount(): Int {
        return textList.size
    }

    override fun onBindViewHolder(holder: Holder, position: Int) {
        val text = textList[position]
        holder.setText(text)
        holder?.setImg()
    }
}

class Holder(val binding: ItemViewpagerBinding) : RecyclerView.ViewHolder(binding.root) {
    fun setText(text:String) {
        binding.
    }
    fun setImg(){
        binding.imageCustomView.setImageResource(R.drawable.img1);
    }
}