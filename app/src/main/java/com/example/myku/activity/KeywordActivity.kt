package com.example.myku.activity

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.myku.adapter.RecyclerAdapter
import com.example.myku.data.ListItem
import com.example.myku.databinding.ActivityKeywordBinding


class KeywordActivity : AppCompatActivity() {
    private lateinit var binding: ActivityKeywordBinding
    val itemList = arrayListOf<ListItem>()      // 아이템 배열
    val RecyclerAdapter = RecyclerAdapter(itemList)


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityKeywordBinding.inflate(layoutInflater)
        setContentView(binding.root)

        spinner_init()


        binding.rvList.layoutManager = LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)
        binding.rvList.adapter = RecyclerAdapter

        // 아이템 추가
        itemList.add(ListItem("Ada", "010-1234-5678","123"))
        itemList.add(ListItem("James", "010-1234-5678","123"))
        itemList.add(ListItem("John", "010-1234-5678","123"))
        itemList.add(ListItem("Cena", "010-1234-5678","123"))
        itemList.add(ListItem("Cendwfwa", "010-1234-5678","123"))
        itemList.add(ListItem("Cenqwa", "010-1234-5678","123"))

        // 리스트가 변경됨을 어댑터에 알림
        RecyclerAdapter.notifyDataSetChanged()

    }

    fun spinner_init(){

    }
}