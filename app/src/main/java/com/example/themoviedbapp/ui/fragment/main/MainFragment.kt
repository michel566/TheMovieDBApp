package com.example.themoviedbapp.ui.fragment.main

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.GravityCompat
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.navigation.fragment.findNavController
import androidx.navigation.ui.NavigationUI
import com.example.themoviedbapp.R
import com.example.themoviedbapp.databinding.FragmentMainBinding
import com.example.themoviedbapp.ui.fragment.favorites.FavoriteFragment
import com.example.themoviedbapp.ui.fragment.pageradapter.ViewPagerAdapter
import com.example.themoviedbapp.ui.fragment.popular.PopularFragment
import com.google.android.material.tabs.TabLayoutMediator

class MainFragment : Fragment() {

    private lateinit var binding: FragmentMainBinding
    private val tagTitle = listOf(Option.POPULAR.value, Option.FAVORITE.value)
    private val fragments = listOf(PopularFragment(), FavoriteFragment())

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentMainBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initToolbar()
        initViewPager()
        initTabLayout()
        initNavigationView()
    }

    private fun initToolbar(){
        (activity as AppCompatActivity).setSupportActionBar(binding.toolbar)
    }

    private fun initViewPager(){
        val pagerAdapter = ViewPagerAdapter(context as FragmentActivity, fragments)
        binding.run {
            container.adapter = pagerAdapter
            container.isUserInputEnabled = false
        }
    }

    private fun initTabLayout() {
        TabLayoutMediator(binding.tabLayout,
            binding.container) { tab, position ->

            tab.text = tagTitle[position]
        }.attach()
    }

    private fun initNavigationView() {
        binding.ivMenu.setOnClickListener {
            binding.root.openDrawer(GravityCompat.START)
        }

        binding.navigationView.itemIconTintList = null
        NavigationUI.setupWithNavController(
            navigationView = binding.navigationView,
            navController = findNavController())

        binding.navigationView.setNavigationItemSelectedListener {
            when(it.itemId){
                R.id.menu_popular -> {
                    binding.container.currentItem = Option.POPULAR.ordinal
                    closeDrawerNav()
                }
                R.id.menu_favorite -> {
                    binding.container.currentItem = Option.FAVORITE.ordinal
                    closeDrawerNav()
                }
                else -> false
            }
        }
    }

    private fun closeDrawerNav(): Boolean {
        binding.root.closeDrawer(GravityCompat.START)
        return true
    }

}