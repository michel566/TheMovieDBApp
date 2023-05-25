package com.example.themoviedbapp.ui.fragment.details

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
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
import com.example.themoviedbapp.ui.fragment.details.viewmodel.GenreViewModel
import com.example.themoviedbapp.ui.fragment.favorites.viewmodel.FavoriteViewModel
import com.example.themoviedbapp.util.DataMapper
import com.example.themoviedbapp.util.Utils
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class DetailsFragment : Fragment() {

    private lateinit var binding: FragmentDetailsBinding
    private val args: DetailsFragmentArgs by navArgs()
    private val genreViewModel: GenreViewModel by viewModels()
    private val favoriteViewModel: FavoriteViewModel by viewModels()

    private lateinit var genres: List<Genre>
    private val movieDetail by lazy {
        args.movieDetail
    }

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
        setupWidgets()
        getGenreList()
    }

    private fun setupWidgets() = with(binding) {
        ivBack.setOnClickListener {
            findNavController().popBackStack()
        }

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
                }
        }
    }

    private fun removeFavorite() {
        lifecycleScope.launch {
            viewLifecycleOwner.lifecycle
                .repeatOnLifecycle(Lifecycle.State.STARTED) {
                    movieDetail.genresId?.let { list ->
                        movieDetail.id?.let { id ->
                            favoriteViewModel.deleteFavoriteById(
                                id, list
                            )
                        }
                    }
                }
        }
    }


}