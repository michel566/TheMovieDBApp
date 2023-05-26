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
import com.example.core.model.entities.GenreEntity
import com.example.core.model.entities.MovieEntity
import com.example.themoviedbapp.R
import com.example.themoviedbapp.databinding.FragmentFavoriteBinding
import com.example.themoviedbapp.ui.fragment.favorites.viewmodel.FavoriteViewModel
import com.example.themoviedbapp.ui.fragment.popular.adapter.FavoriteAdapter
import com.example.themoviedbapp.util.DataMapper
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class FavoriteFragment : Fragment() {

    private lateinit var binding: FragmentFavoriteBinding
    private val viewModel: FavoriteViewModel by viewModels()

    private var list: List<MovieDomain>? = null

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

    private fun loadFavoriteMovies() {
        viewModel.getAllFavorites().observeForever { listEntity ->
            list = listEntity.map { DataMapper.movieWithGenreEntityToDomain(it) }
            initAdapter(list)
            showAdapter()
        }
        showAdapter()

    }

    private fun initAdapter(list: List<MovieDomain>?) {
        val favoriteAdapter = FavoriteAdapter(::goToDetails, ::saveFavorite, ::removeFavorite)
        with(binding.recyclerView){
            scrollToPosition(0)
            layoutManager = GridLayoutManager(requireContext(), 2)
            setHasFixedSize(true)
            adapter = favoriteAdapter
        }
        favoriteAdapter.updateList(list)
    }

    private fun showAdapter() {
        binding.errorLayout.isVisible = (list.isNullOrEmpty())
        if (list.isNullOrEmpty()) {
            binding.errorLayout.setText(getString(R.string.error_message_no_favorites))
        }
    }

    private fun goToDetails(movie: MovieDomain) {
        val data = arrayOf(movie.fullPosterPath, movie.overview)
        //todo: criar e redirecionar o data para tela de detalhes
//        findNavController().navigate(MainFragmentDirections.actionMainFragmentToDownloadFragment(data))
    }

    private fun saveFavorite(movieDetail: MovieDomain) {
        lifecycleScope.launch {
            viewLifecycleOwner.lifecycle
                .repeatOnLifecycle(Lifecycle.State.STARTED) {
                    viewModel.saveFavorite(movieDetail)
                }
        }
    }

    private fun removeFavorite(movieDetail: MovieDomain) {
        lifecycleScope.launch {
            viewLifecycleOwner.lifecycle
                .repeatOnLifecycle(Lifecycle.State.STARTED) {
                    movieDetail.genreIds.let { list ->
                        movieDetail.id.let { id ->
                            viewModel.deleteFavoriteById(
                                id, list
                            )
                        }
                    }
                }
        }
    }

}