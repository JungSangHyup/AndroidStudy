package com.example.loginformselector

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Gravity
import android.view.View
import android.view.WindowManager
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import com.example.loginformselector.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private val binding by lazy { ActivityMainBinding.inflate(layoutInflater)}
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        title = "사용자 정보 입력"

        binding.button1.setOnClickListener {
            var dialogView = View.inflate(this@MainActivity, R.layout.activity_login, null)
            var dlg = AlertDialog.Builder(this@MainActivity)
            dlg.setTitle("사용자 정보 입력")
            dlg.setIcon(R.drawable.ic_menu_allfriends)
            dlg.setView(dialogView)
            dlg.setPositiveButton("확인"){
                dialog, which ->
                var dlgEdtName = dialogView.findViewById<EditText>(R.id.dlgEdt1)
                var dlgEdtEmail = dialogView.findViewById<EditText>(R.id.dlgEdt2)

                binding.tvName.text = dlgEdtName.text.toString()
                binding.tvEmail.text = dlgEdtEmail.text.toString()
            }
            dlg.setNegativeButton("취소"){
                dialog, which ->
                var toast = Toast(this@MainActivity)
                var toastView = View.inflate(this@MainActivity, R.layout.toast1, null)
                var toastText = toastView.findViewById<TextView>(R.id.toastText1)
                var display = (getSystemService(Context.WINDOW_SERVICE) as WindowManager).defaultDisplay
                var xOffset = (Math.random() * display.width).toInt()
                var yOffset = (Math.random() * display.height).toInt()
                toast.setGravity(Gravity.TOP or Gravity.LEFT, xOffset, yOffset)
                toastText.text = "취소했습니다"
                toast.view = toastView
                toast.show()
            }
            dlg.show()
        }
    }
}