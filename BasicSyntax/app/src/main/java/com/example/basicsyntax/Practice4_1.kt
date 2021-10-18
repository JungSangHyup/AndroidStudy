package com.example.basicsyntax

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.practice4_1.*

class Practice4_1 : AppCompatActivity() {
    fun checknum(num1 : Double?, num2: Double?): Boolean {
        if(editNumber1 == null || editNumber2 == null){
            return false
        }
        return true
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.practice4_1)

        btnPlus.setOnClickListener {
            val num1 : Double? = editNumber1.text.toString().toDouble()
            val num2 : Double? = editNumber2.text.toString().toDouble()
            if(checknum(num1, num2)){
                val result = num1?.plus(num2!!);
                textResult.setText("계산결과 : " + result.toString() )
            }
        }

        btnMinus.setOnClickListener {
            val num1 : Double? = editNumber1.text.toString().toDouble()
            val num2 : Double? = editNumber2.text.toString().toDouble()
            if(checknum(num1, num2)){
                val result = num1?.minus(num2!!);
                textResult.setText("계산결과 : " + result.toString() )
            }
        }

        btnMultiple.setOnClickListener {
            val num1 : Double? = editNumber1.text.toString().toDouble()
            val num2 : Double? = editNumber2.text.toString().toDouble()
            if(checknum(num1, num2)){
                val result = num1?.times(num2!!);
                textResult.setText("계산결과 : " + result.toString() )
            }
        }

        btnDivision.setOnClickListener {
            val num1 : Double? = editNumber1.text.toString().toDouble()
            val num2 : Double? = editNumber2.text.toString().toDouble()
            if(checknum(num1, num2) && num2 != 0.0){
                val result = num1?.div(num2!!);
                textResult.setText("계산결과 : " + result.toString() )
            }
        }
    }
}