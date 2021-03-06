package com.example.viewpagerfragment

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.viewpagerfragment.databinding.ActivityMainBinding
import com.google.android.material.tabs.TabLayoutMediator

class MainActivity : AppCompatActivity() {

    val binding by lazy {ActivityMainBinding.inflate(layoutInflater)}

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

//        val fragmentList = listOf(FragmentA(), FragmentB(), FragmentC(), FragmentD())
//        val adapter = FragmentAdapter(this)
//        adapter.fragmentList = fragmentList
//        binding.viewPager.adapter = adapter

        val textList = listOf("뷰A", "뷰B", "뷰C", "뷰D")
        val customAdapter = CustomPagerAdapter()
        customAdapter.textList = textList
        binding.viewPager.adapter = customAdapter

        val tabTitles = listOf("View A", "View B", "View C", "View D")
        TabLayoutMediator(binding.tabLayout, binding.viewPager) {tab, position ->
            tab.text = tabTitles[position]
        }.attach()
    }
}
