package com.example.themoviedbapp.ui.fragment.favorites

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.paging.LoadState
import androidx.recyclerview.widget.GridLayoutManager
import com.example.core.model.MovieDomain
import com.example.themoviedbapp.R
import com.example.themoviedbapp.databinding.FragmentFavoriteBinding
import com.example.themoviedbapp.ui.fragment.main.Option
import com.example.themoviedbapp.ui.fragment.movieadapter.MovieAdapter
import com.example.themoviedbapp.ui.fragment.popular.viewmodel.PopularViewModel
import com.example.themoviedbapp.util.animationCancel
import com.example.themoviedbapp.util.pulseAnimation
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

@AndroidEntryPoint
class FavoriteFragment : Fragment() {

    private lateinit var binding: FragmentFavoriteBinding
    private lateinit var movieAdapter: MovieAdapter
    private val viewModel: PopularViewModel by viewModels()
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentFavoriteBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initAdapter()
        observerLoadState()
        fetchMovies()
        setupWidgets()
    }

    private fun initAdapter() {
        movieAdapter = MovieAdapter(::detail, Option.FAVORITE)
        val gridLayoutManager = GridLayoutManager(requireContext(), 2)
        with(binding.recyclerView) {
            scrollToPosition(0)
            layoutManager = gridLayoutManager
            setHasFixedSize(true)
            adapter = movieAdapter
        }
    }

    private fun detail(movie: MovieDomain) {
        val data = arrayOf(movie.fullPosterPath, movie.overview)
        //todo: criar e redirecionar o data para tela de detalhes
//        findNavController().navigate(MainFragmentDirections.actionMainFragmentToDownloadFragment(data))
    }

    private fun observerLoadState() {
        lifecycleScope.launch {
            movieAdapter.loadStateFlow.collectLatest { loadstate ->
                when (loadstate.refresh) {
                    is LoadState.Loading -> {
                        binding.imagePulseAnimation.pulseAnimation()
                    }

                    is LoadState.NotLoading -> {
                        binding.imagePulseAnimation.animationCancel()
                    }

                    is LoadState.Error -> {
                        Toast.makeText(requireContext(), "Try again later", Toast.LENGTH_LONG)
                            .show()
                    }

                    else -> {}
                }
            }
        }
    }

    private fun fetchMovies() {
        lifecycleScope.launch {
            viewLifecycleOwner.lifecycle
                .repeatOnLifecycle(Lifecycle.State.STARTED) {
                    viewModel.favoriteMovies().collect { pagingData ->
                        movieAdapter.submitData(pagingData)
                    }
                }
        }
    }

    private fun setupWidgets() {
//        binding.errorLayout.isVisible = (movieAdapter.itemCount == 0)
        if (movieAdapter.itemCount == 0) {
            binding.errorLayout.setText(getString(R.string.error_message_no_favorites))
        }
    }

}