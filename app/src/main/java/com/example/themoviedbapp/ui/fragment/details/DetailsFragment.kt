package com.example.themoviedbapp.ui.fragment.details

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
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.example.core.GeneralConstants.PATTERN_DD_MM_YYYY
import com.example.core.GeneralConstants.PATTERN_YYYY_MM_DD
import com.example.core.model.Genre
import com.example.themoviedbapp.databinding.FragmentDetailsBinding
import com.example.themoviedbapp.framework.cache.KeyCacheConstants
import com.example.themoviedbapp.framework.cache.TMDBAppCache
import com.example.themoviedbapp.framework.model.MovieDetailDomain
import com.example.themoviedbapp.ui.fragment.details.viewmodel.GenreViewModel
import com.example.themoviedbapp.ui.fragment.favorites.viewmodel.FavoriteViewModel
import com.example.themoviedbapp.util.DataMapper
import com.example.themoviedbapp.util.Utils
import com.example.themoviedbapp.util.animationCancel
import com.example.themoviedbapp.util.pulseAnimation
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class DetailsFragment : Fragment() {

    private lateinit var binding: FragmentDetailsBinding
    private val args: DetailsFragmentArgs by navArgs()
    private val genreViewModel: GenreViewModel by viewModels()

    private val favoriteViewModel: FavoriteViewModel by viewModels()

    private lateinit var genres: List<Genre>
    private lateinit var movieDetail: MovieDetailDomain

    private var isFavorite = false

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentDetailsBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        loadFavoriteMovies()
    }

    private fun loadFavoriteMovies() {
        movieDetail = args.movieDetail
        loadImage(false)
        lifecycleScope.launch {
            viewLifecycleOwner.lifecycle
                .repeatOnLifecycle(Lifecycle.State.STARTED) {
                    favoriteViewModel.getAllFavorites(
                        onSuccess = { data ->
                            data.forEach {
                                if (it.isFavorite && it.id == args.movieDetail.id) {
                                    movieDetail = DataMapper.movieDomainToDetail(it)
                                }
                            }
                            setupWidgets(movieDetail)
                        },
                        onEmptyList = {
                            setupWidgets(movieDetail)
                        },
                        onError = {}
                    )
                }
        }
    }

    private fun reload() =
        TMDBAppCache.update(KeyCacheConstants.PREFS_NEED_REFRESH, true)

    private fun setupWidgets(movie: MovieDetailDomain) = with(binding) {
        getGenreList()
        ivBack.setOnClickListener {
            findNavController().popBackStack()
        }

        movieDetail = movie
        movieDetail.let {
            tvToolbarTitle.text = it.title
            isFavorite = it.isFavorite
            setFavoriteButton()
            Utils.setupResourceImage(requireContext(), ivImage, it.imagePath)
            tvTitle.text = it.originalTitle
            tvDate.text = buildString {
                append("Release date: ")
                append(
                    Utils.parseDateToddMMyyyy(
                        PATTERN_YYYY_MM_DD,
                        it.releaseDate,
                        PATTERN_DD_MM_YYYY
                    )
                )
            }
            tvOverview.text = it.overview
        }
    }

    private fun getGenreList() {
        viewLifecycleOwner.lifecycleScope.launch {
            viewLifecycleOwner.repeatOnLifecycle(Lifecycle.State.STARTED) {
                genres = genreViewModel.getGenreList()
                setupGenres()
                loadImage(true)
            }
        }
    }

    private fun setupGenres() = with(binding) {
        tvGenre.text = Utils.setGenresInText(genres, movieDetail.genresId)
    }

    private fun setFavoriteButton() =
        with(binding.ivFavorite) {
            isHovered = isFavorite

            setOnClickListener {
                isFavorite = !isFavorite
                isHovered = isFavorite
                movieDetail.isFavorite = isFavorite

                if (isFavorite)
                    saveFavorite()
                else
                    removeFavorite()
            }
        }

    private fun saveFavorite() {
        lifecycleScope.launch {
            viewLifecycleOwner.lifecycle
                .repeatOnLifecycle(Lifecycle.State.STARTED) {
                    favoriteViewModel.saveFavorite(
                        DataMapper.movieDetailToDomain(movieDetail)
                    )
                    reload()
                }
        }
    }

    private fun removeFavorite() {
        lifecycleScope.launch {
            viewLifecycleOwner.lifecycle
                .repeatOnLifecycle(Lifecycle.State.STARTED) {
                    movieDetail.id?.let { id ->
                        favoriteViewModel.deleteFavoriteById(id)
                    }
                    reload()
                }

        }
    }

    private fun loadImage(show: Boolean) = with(binding) {
        ivImage.isVisible = show
        if (show) imagePulseAnimation.animationCancel()
        else imagePulseAnimation.pulseAnimation()
    }


}