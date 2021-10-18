package com.example.basicsyntax

import android.app.Activity
import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.*
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        fun loadData(): MutableList<Memo>{
            val data:MutableList<Memo> = mutableListOf()

            for(no in 1..100){
                val title = "이것이 코틀린 안드로이드다 ${no + 1}"
                val date = System.currentTimeMillis()

                var memo = Memo(no, title, date)
                data.add(memo)
            }
            return data;
        }


        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        btnNaver.setOnClickListener {
            val address = "https://www.naver.com";
            val intent = Intent(Intent.ACTION_VIEW, Uri.parse(address))
            startActivity(intent);
        }

        btnEmergency.setOnClickListener{
            val intent = Intent(Intent.ACTION_DIAL)
            intent.data = Uri.parse("tel:911")
            if(intent.resolveActivity(packageManager) != null){
                startActivity(intent)
            }
        }

        btnGallery.setOnClickListener{
            val intent = Intent(Intent.ACTION_VIEW, Uri.parse("content://media/internal/images/media"))
            startActivity(intent)
        }

        btnEnd.setOnClickListener{
            finish()
        }

        btnStart.setOnClickListener {
            val intent = Intent(this, SubActivity::class.java)
            intent.putExtra("from1", "hello Bundle")
            intent.putExtra("from2", 2020)
            startActivityForResult(intent, 99)
        }

        var data = listOf("-선택하세요 -", "1월", "2월", "3월", "4월", "5월", "6월", )
        var adapter = ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, data)
        spinner.adapter = adapter

        spinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener{
            override fun onItemSelected(
                parent: AdapterView<*>?,
                view: View?,
                position: Int,
                id: Long
            ) {
                result.text = data.get(position)
            }

            override fun onNothingSelected(parent: AdapterView<*>?) {
                TODO("Not yet implemented")
            }
        }

        val data2:MutableList<Memo> = loadData()
        var adapter2 = CustomAdapter()
        adapter2.listData = data2
        recyclerView.adapter = adapter2
        recyclerView.layoutManager = LinearLayoutManager(this)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if(resultCode == Activity.RESULT_OK){
            when(requestCode){
                99 -> {
                    val message = data?.getStringExtra("returnValue")
                    Toast.makeText(this, message, Toast.LENGTH_LONG).show()
                }
            }
        }
    }
    
    
}