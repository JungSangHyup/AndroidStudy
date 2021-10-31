package com.example.toast

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Gravity
import android.view.WindowManager
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import com.example.toast.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private val binding by lazy { ActivityMainBinding.inflate(layoutInflater) }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        title = "토스트 연습"

        //토스트
        binding.button1.setOnClickListener {
            var tMsg = Toast.makeText(applicationContext, "토스트 연습", Toast.LENGTH_SHORT)

            var display = (getSystemService(Context.WINDOW_SERVICE) as WindowManager).defaultDisplay
            var xOffset = (Math.random() * display.width).toInt()
            var yOffset = (Math.random() * display.height).toInt()

            tMsg.setGravity(Gravity.TOP or Gravity.LEFT, xOffset, yOffset)
            tMsg.show()
        }

        //AlertDialog
        binding.button2.setOnClickListener {
            var map = HashMap<String, Boolean>();
            var versionArray = arrayOf("오레오", "파이", "큐")
            map.put("오레오", true)
            map.put("파이", false)
            map.put("zb", false)

            var dlg = AlertDialog.Builder(this@MainActivity)
            dlg.setTitle("좋아하는 버전은?")
            dlg.setIcon(R.mipmap.ic_launcher)
            dlg.setItems(versionArray){
                dialog, which -> binding.button2.text = versionArray[which]
                map.put("오레오", false)
            }

            dlg.setPositiveButton("확인", null)
            dlg.show()
        }
    }
}