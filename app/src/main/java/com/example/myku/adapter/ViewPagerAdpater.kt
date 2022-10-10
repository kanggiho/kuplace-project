package com.example.myku

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.example.myku.fragment.EventFragment
import com.example.myku.fragment.HomeFragment
import com.example.myku.fragment.MyinfoFragment
import com.example.myku.fragment.PlaceFragment

class ViewPagerAdapter (fragment : FragmentActivity) : FragmentStateAdapter(fragment){
    override fun getItemCount(): Int = 4

    override fun createFragment(position: Int): Fragment {
        return when (position) {
            0 -> HomeFragment()
            1 -> PlaceFragment()
            2 -> EventFragment()
            else -> MyinfoFragment()
        }
    }
}