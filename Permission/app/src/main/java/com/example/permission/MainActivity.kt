package com.example.permission

import android.Manifest
import android.content.pm.PackageManager
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import com.example.permission.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    val binding by lazy {ActivityMainBinding.inflate(layoutInflater)}

    fun checkPermission() {
        val cameraPermission = ContextCompat.checkSelfPermission(this, Manifest.permission.CAMERA)
        if(cameraPermission == PackageManager.PERMISSION_GRANTED){
            startProcess()
        }else {
            requestPermission()
        }
    }

    fun startProcess() {
        Toast.makeText(this, "카메라를 실행합니다.", Toast.LENGTH_LONG).show()
    }

    fun requestPermission(){
        ActivityCompat.requestPermissions(this, arrayOf(Manifest.permission.CAMERA), 99)
    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        when(requestCode){
            99 -> {
                if(grantResults[0] == PackageManager.PERMISSION_GRANTED){
                    startProcess()
                }else {
                    finish()
                }
            }
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        binding.btnCamera.setOnClickListener {
            checkPermission()
        }
    }
}