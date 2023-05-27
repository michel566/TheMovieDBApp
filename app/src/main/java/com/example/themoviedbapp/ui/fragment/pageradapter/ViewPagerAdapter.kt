package com.example.themoviedbapp.ui.fragment.pageradapter

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter

class ViewPagerAdapter(
    container: FragmentActivity,
    private val fragmentList: MutableList<Fragment>
) :
    FragmentStateAdapter(container) {
    override fun getItemCount() = fragmentList.size
    override fun createFragment(position: Int) = fragmentList[position]

}