package com.example.myku.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.myku.R
import com.example.myku.data.ListItem

class RecyclerAdapter(val itemList: ArrayList<ListItem>): RecyclerView.Adapter<RecyclerAdapter.ViewHolder>() {
    // (1) 아이템 레이아웃과 결합
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerAdapter.ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.list_item, parent, false)
        return ViewHolder(view)
    }
    // (2) 리스트 내 아이템 개수
    override fun getItemCount(): Int {
        return itemList.size
    }
    // (3) View에 내용 입력
    override fun onBindViewHolder(holder: RecyclerAdapter.ViewHolder, position: Int) {
        holder.name.text = itemList[position].li_name
        holder.address.text = itemList[position].li_address
        holder.rate.text = itemList[position].li_rate

    }
    // (4) 레이아웃 내 View 연결
    class ViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {
        val name: TextView = itemView.findViewById(R.id.rv_name)
        val address: TextView = itemView.findViewById(R.id.rv_address)
        val rate: TextView = itemView.findViewById(R.id.rv_rate)
    }
}