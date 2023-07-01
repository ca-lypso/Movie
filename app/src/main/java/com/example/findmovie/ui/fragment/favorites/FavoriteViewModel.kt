package com.example.findmovie.ui.fragment.favorites

import androidx.lifecycle.ViewModel
import com.example.findmovie.repo.Repository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class FavoriteViewModel @Inject constructor(private val repo: Repository) :ViewModel() {


    fun getFavs() = repo.getAllFavMovies()

}