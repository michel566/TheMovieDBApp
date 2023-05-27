package com.example.themoviedbapp.ui.fragment.main

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.app.AppCompatDelegate
import androidx.appcompat.widget.SearchView
import androidx.core.view.GravityCompat
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.navigation.NavController
import androidx.navigation.fragment.findNavController
import androidx.navigation.ui.NavigationUI
import com.example.themoviedbapp.R
import com.example.themoviedbapp.databinding.FragmentMainBinding
import com.example.themoviedbapp.framework.cache.KeyCacheConstants
import com.example.themoviedbapp.framework.cache.KeyCacheConstants.PREFS_NEED_REFRESH
import com.example.themoviedbapp.framework.cache.KeyCacheConstants.PREFS_NIGHT_MODE
import com.example.themoviedbapp.framework.cache.TMDBAppCache
import com.example.themoviedbapp.framework.model.MovieDetailDomain
import com.example.themoviedbapp.ui.activity.MainActivity
import com.example.themoviedbapp.ui.fragment.favorites.FavoriteFragment
import com.example.themoviedbapp.ui.fragment.pageradapter.ViewPagerAdapter
import com.example.themoviedbapp.ui.fragment.popular.PopularFragment
import com.google.android.material.tabs.TabLayoutMediator

class MainFragment : Fragment() {

    private lateinit var binding: FragmentMainBinding
    private val tagTitle = listOf(Option.POPULAR.value, Option.FAVORITE.value)
    private val popularFragment = PopularFragment()
    private val favoriteFragment = FavoriteFragment()
    private val fragments = mutableListOf(popularFragment, favoriteFragment)
    private lateinit var pagerAdapter: ViewPagerAdapter

    private lateinit var option: Option

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentMainBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        startPrefs()
        initToolbar()
        initViewPager()
        initTabLayout()
        initNavigationView()
        setupWidgets()
    }


    private fun startPrefs() {
        setNightMode(TMDBAppCache.get(PREFS_NIGHT_MODE) ?: false)
    }

    private fun initToolbar() {
        (activity as AppCompatActivity).setSupportActionBar(binding.toolbar)
    }

    private fun initViewPager() {
        pagerAdapter = ViewPagerAdapter(context as FragmentActivity, fragments)
        binding.run {
            container.adapter = pagerAdapter
            container.isUserInputEnabled = false
        }
    }

    private fun initTabLayout() {
        TabLayoutMediator(
            binding.tabLayout,
            binding.container
        ) { tab, position ->
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
            navController = findNavController()
        )

        binding.navigationView.setNavigationItemSelectedListener {
            when (it.itemId) {
                R.id.menu_popular -> {
                    goToPopulars()
                }

                R.id.menu_favorite -> {
                    goToFavorites()
                }

                R.id.menu_mode_light -> {
                    setNightMode(false)
                    TMDBAppCache.update(PREFS_NIGHT_MODE, false)
                }

                R.id.menu_mode_dark -> {
                    setNightMode(true)
                    TMDBAppCache.update(PREFS_NIGHT_MODE, true)
                }

                else -> false
            }
            closeDrawerNav()
        }
    }

    private fun setupWidgets() = with(binding) {
        ivSearch.setOnClickListener {
            changeAppBarVisibility(false)
            svPopular.requestFocus()
        }

        svPopular.setOnQueryTextFocusChangeListener { view, isFocused ->
            changeAppBarVisibility(!isFocused)
        }

        svPopular.setOnQueryTextListener(
            object : SearchView.OnQueryTextListener {
                override fun onQueryTextSubmit(query: String?): Boolean {
                    return false
                }

                override fun onQueryTextChange(newText: String?): Boolean {
                    popularFragment.textChanged(newText)
                    return false
                }
            }
        )

        ivPopular.setOnClickListener {
            goToPopulars()
        }
    }

    private fun setNightMode(isNightMode: Boolean) {
        if (isNightMode) {
            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES)
        } else {
            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)
        }
    }

    private fun goToPopulars() = with(binding) {
        option = Option.POPULAR
        ivSearch.isVisible = true
        ivPopular.isVisible = false
        container.currentItem = option.ordinal
    }

    private fun goToFavorites() = with(binding) {
        option = Option.FAVORITE
        ivSearch.isVisible = false
        svPopular.isVisible = false
        ivPopular.isVisible = true
        container.currentItem = option.ordinal
        val isReload = TMDBAppCache.get<Boolean>(PREFS_NEED_REFRESH)
        if (isReload){
            favoriteFragment.loadFavoriteMovies()
            TMDBAppCache.update(PREFS_NEED_REFRESH, false)
        }

    }

    private fun changeAppBarVisibility(isVisible: Boolean) = with(binding) {
        toolbar.isVisible = isVisible
        svPopular.isVisible = !isVisible
    }

    private fun closeDrawerNav(): Boolean {
        binding.root.closeDrawer(GravityCompat.START)
        return true
    }

    fun goToDetailsFragment(navController: NavController, movieDetail: MovieDetailDomain) =
        navController.navigate(
            MainFragmentDirections.actionMainFragmentToDetailsFragment(movieDetail)
        )

}