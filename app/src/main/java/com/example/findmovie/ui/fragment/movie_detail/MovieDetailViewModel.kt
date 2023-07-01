package com.example.findmovie.ui.fragment.movie_detail

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.findmovie.model.FavoritMovie
import com.example.findmovie.model.MovieResponseById
import com.example.findmovie.model.Video
import com.example.findmovie.repo.Repository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MovieDetailViewModel @Inject constructor(private val repo: Repository) : ViewModel() {
    val movieDetailLiveData = MutableLiveData<MovieResponseById>()
    val movieVideoLiveData = MutableLiveData<List<Video>?>()
    val isMovieIdExistsLiveData= MutableLiveData<Boolean>()


    fun insertMovieFavorite(movie: FavoritMovie) {
        viewModelScope.launch(IO) {
            repo.insertMovieFavorite(movie)
        }
    }

    fun deleteFavoriteMovie(movie: FavoritMovie) {
        viewModelScope.launch(IO) {
            repo.deleteFavoriteMovie(movie)
        }
    }


    fun isMovieIdExists(movieId:Int){
        viewModelScope.launch (IO){
            val result=repo.isMovieIdExists(movieId)
            isMovieIdExistsLiveData.postValue(result)
        }
    }

    fun getFavs() = repo.getAllFavMovies()

    fun getMovieById(movie_id: Long) {
        viewModelScope.launch(IO) {

            val response = repo.getMovieById(movie_id)

            if (response.isSuccessful && response.code() == 200) {
                val body = response.body()
                if (body != null) {
                    movieDetailLiveData.postValue(body!!)
                }

            }

        }
    }

    fun getMovieVideosById(movie_id: Long) {
        viewModelScope.launch(IO) {

            val response = repo.getMovieVideosById(movie_id)

            if (response.isSuccessful && response.code() == 200) {
                val body = response.body()?.results
                if (body != null) {
                    movieVideoLiveData.postValue(body)
                }

            }

        }
    }


}