package com.example.themoviedbapp.ui.fragment.popular

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
import androidx.navigation.fragment.findNavController
import androidx.paging.LoadState
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.core.model.MovieDomain
import com.example.themoviedbapp.databinding.FragmentPopularBinding
import com.example.themoviedbapp.framework.model.MovieDetailDomain
import com.example.themoviedbapp.ui.fragment.main.MainFragment
import com.example.themoviedbapp.ui.fragment.main.Option
import com.example.themoviedbapp.ui.fragment.movieadapter.MovieAdapter
import com.example.themoviedbapp.ui.fragment.popular.viewmodel.PopularViewModel
import com.example.themoviedbapp.util.DataMapper
import com.example.themoviedbapp.util.animationCancel
import com.example.themoviedbapp.util.pulseAnimation
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

@AndroidEntryPoint
class PopularFragment : Fragment() {

    private lateinit var binding: FragmentPopularBinding
    private lateinit var movieAdapter: MovieAdapter
    private val viewModel: PopularViewModel by viewModels()

    private val mainFragment by lazy {
        MainFragment()
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentPopularBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initAdapter()
        observerLoadState()
        fetchMovies()
    }

    fun textChanged(newText: String?) {

    }

    private fun initAdapter() {
        movieAdapter = MovieAdapter(::detail, Option.POPULAR)
        val linearLayoutManager = LinearLayoutManager(requireContext())
        with(binding.recyclerView) {
            scrollToPosition(0)
            layoutManager = linearLayoutManager
            setHasFixedSize(true)
            adapter = movieAdapter
        }
    }

    private fun detail(movie: MovieDomain) {
        mainFragment.goToDetailsFragment(findNavController(), DataMapper.movieDomainToDetail(movie))
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
                    viewModel.popularMovies().collectLatest { pagingData ->
                        movieAdapter.submitData(pagingData)
                    }
                }
        }
    }

}