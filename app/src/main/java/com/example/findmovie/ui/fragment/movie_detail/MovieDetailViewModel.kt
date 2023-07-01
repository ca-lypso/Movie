package com.example.findmovie.ui.fragment.movie_detail

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.findmovie.model.MovieResponseById
import com.example.findmovie.model.Video
import com.example.findmovie.repo.Repository
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.launch

class MovieDetailViewModel: ViewModel() {
    val movieDetailLiveData=MutableLiveData<MovieResponseById>()
    val movieVideoLiveData=MutableLiveData<List<Video>?>()

    val repo= Repository()


    fun getMovieById(movie_id:Int){
        viewModelScope.launch(IO) {

            val response=repo.getMovieById(movie_id)

            if (response.isSuccessful&&response.code()==200){
                val body=response.body()
                if (body != null){
                    movieDetailLiveData.postValue(body!!)
                }

            }

        }
    }
    fun getMovieVideosById(movie_id:Int){
        viewModelScope.launch(IO) {

            val response=repo.getMovieVideosById(movie_id)

            if (response.isSuccessful&&response.code()==200){
                val body=response.body()?.results
                if (body != null){
                    movieVideoLiveData.postValue(body)
                }

            }

        }
    }


}