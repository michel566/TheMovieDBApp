package com.example.themoviedbapp.ui.fragment.favorites

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
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

@AndroidEntryPoint
class FavoriteFragment : Fragment() {

    private lateinit var binding: FragmentFavoriteBinding
    private val viewModel: FavoriteViewModel by viewModels()

    private var list: List<MovieDomain>? = null
    private var listGenreEntity: List<GenreEntity>? = null
    private var listMovieEntity: List<MovieEntity>? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentFavoriteBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        fetchFavoriteMovies()
    }

    private fun fetchFavoriteMovies() {
        viewModel.getAllFavorites().second.observeForever { listGenre ->
            listGenreEntity = listGenre
            syncAndSetList()
        }
        viewModel.getAllFavorites().first.observeForever { listMovie ->
            listMovieEntity = listMovie
            syncAndSetList()
        }
    }

    private fun syncAndSetList(){
        if (!listGenreEntity.isNullOrEmpty() && !listMovieEntity.isNullOrEmpty())
            listGenreEntity?.let { it1->
                listMovieEntity?.let { it2->
                    list = DataMapper.pairOfEntitiesToListMovieDomain(it2, it1)
                    initAdapter(list)
                }
            }
        showAdapter()
    }

    private fun initAdapter(list: List<MovieDomain>?) {
        val favoriteAdapter = FavoriteAdapter(::detail)
        with(binding.recyclerView){
            scrollToPosition(0)
            layoutManager = GridLayoutManager(requireContext(), 2)
            setHasFixedSize(true)
            adapter = favoriteAdapter
        }
        favoriteAdapter.updateList(list)
    }

    private fun detail(movie: MovieDomain) {
        val data = arrayOf(movie.fullPosterPath, movie.overview)
        //todo: criar e redirecionar o data para tela de detalhes
//        findNavController().navigate(MainFragmentDirections.actionMainFragmentToDownloadFragment(data))
    }

    private fun showAdapter() {
        binding.errorLayout.isVisible = (list.isNullOrEmpty())
        if (list.isNullOrEmpty()) {
            binding.errorLayout.setText(getString(R.string.error_message_no_favorites))
        }
    }

}