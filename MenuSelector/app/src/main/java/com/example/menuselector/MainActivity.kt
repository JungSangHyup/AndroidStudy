package com.example.menuselector

import android.app.Activity
import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.LayoutInflater
import android.view.Menu
import android.view.MenuItem
import com.example.menuselector.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private val binding by lazy { ActivityMainBinding.inflate(layoutInflater) }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        title = "배경색 바꾸기"


    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        super.onCreateOptionsMenu(menu)
        var mInflater = menuInflater
        mInflater.inflate(R.menu.menu, menu)
        return true
    }



    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when(item.itemId){
            R.id.itemRed -> {
                binding.baseLayout.setBackgroundColor(Color.RED)
                return true
            }
            R.id.itemBlue -> {
                binding.baseLayout.setBackgroundColor(Color.BLUE)
                return true
            }
            R.id.itemGreen -> {
                binding.baseLayout.setBackgroundColor(Color.GREEN)
                return true
            }
            R.id.subRotate -> {
                binding.button1.rotation = 45f
                return true
            }
            R.id.subSize -> {
                binding.button1.scaleX = 2f
                return true
            }
        }
        return false
    }
}