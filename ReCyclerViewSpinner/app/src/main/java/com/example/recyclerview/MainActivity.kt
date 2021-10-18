package com.example.recyclerview

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
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
}