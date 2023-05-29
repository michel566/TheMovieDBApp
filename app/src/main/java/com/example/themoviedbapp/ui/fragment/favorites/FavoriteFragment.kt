package com.example.themoviedbapp.ui.fragment.favorites

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.recyclerview.widget.GridLayoutManager
import com.example.core.model.MovieDomain
import com.example.themoviedbapp.R
import com.example.themoviedbapp.databinding.FragmentFavoriteBinding
import com.example.themoviedbapp.ui.fragment.favorites.viewmodel.FavoriteViewModel
import com.example.themoviedbapp.ui.fragment.popular.adapter.FavoriteAdapter
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class FavoriteFragment : Fragment() {

    private lateinit var binding: FragmentFavoriteBinding
    private val viewModel: FavoriteViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentFavoriteBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        loadFavoriteMovies()
    }

    fun loadFavoriteMovies() {
        lifecycleScope.launch {
            viewLifecycleOwner.lifecycle
                .repeatOnLifecycle(Lifecycle.State.STARTED) {
                    viewModel.getAllFavorites(
                        onSuccess = { data ->
                            showError(false, null)
                            initAdapter(data)
                        },
                        onEmptyList = {
                            showError(true, getString(R.string.error_message_no_favorites))
                            initAdapter(listOf())
                        },
                        onError = {}
                    )
                }
        }
    }

    fun textChanged(newText: CharSequence) {
        lifecycleScope.launch {
            viewLifecycleOwner.lifecycle
                .repeatOnLifecycle(Lifecycle.State.STARTED) {
                    viewModel.filterFavorites(
                        onSuccess = { data ->
                            showError(false, null)
                            initAdapter(data)
                        },
                        onEmptyList = {
                            showError(true, getString(R.string.error_message_no_favorites))
                            initAdapter(listOf())
                        },
                        onError = {},
                        char = newText
                    )
                }
        }
    }

    private fun reload() = loadFavoriteMovies()

    private fun initAdapter(list: List<MovieDomain>) {
        val favoriteAdapter = FavoriteAdapter(::saveFavorite, ::removeFavorite)
        with(binding.recyclerView) {
            scrollToPosition(0)
            layoutManager = GridLayoutManager(requireContext(), 2)
            setHasFixedSize(true)
            adapter = favoriteAdapter
        }
        favoriteAdapter.submitList(list)
    }

    private fun showError(show: Boolean, textError: String?) {
        with(binding.errorLayout) {
            isVisible = show

            if (show)
                setText(textError)
        }
    }

    private fun saveFavorite(movieDetail: MovieDomain) {
        lifecycleScope.launch {
            viewLifecycleOwner.lifecycle
                .repeatOnLifecycle(Lifecycle.State.STARTED) {
                    viewModel.saveFavorite(movieDetail)
                    reload()
                }
        }
    }

    private fun removeFavorite(movieDetail: MovieDomain) {
        lifecycleScope.launch {
            viewLifecycleOwner.lifecycle
                .repeatOnLifecycle(Lifecycle.State.STARTED) {
                    movieDetail.id.let { id ->
                        viewModel.deleteFavoriteById(
                            id
                        )
                    }
                    reload()
                }
        }
    }

}