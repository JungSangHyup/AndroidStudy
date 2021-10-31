package com.example.activitydatatransfer

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import com.example.activitydatatransfer.databinding.SecondBinding

class SecondActivity : Activity() {
    val binding by lazy{ SecondBinding.inflate(layoutInflater)}
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        title = "Second Activity"

        var inIntent = intent
        var hapValue = inIntent.getIntExtra("Num1", 0) + inIntent.getIntExtra("Num2", 0)

        binding.btnReturn.setOnClickListener {
            var outIntent = Intent(applicationContext, MainActivity::class.java)
            outIntent.putExtra("Hap", hapValue)
            setResult(Activity.RESULT_OK, outIntent)
            finish()
        }
    }
}