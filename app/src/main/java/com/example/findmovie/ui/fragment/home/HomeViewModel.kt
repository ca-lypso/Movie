package com.example.findmovie.ui.fragment.home

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.findmovie.model.Result
import com.example.findmovie.repo.Repository
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.launch

class HomeViewModel:ViewModel() {
    val popularMovieList=MutableLiveData<List<Result>>()
    val topRatedMovieList=MutableLiveData<List<Result>>()
    val upcomingMovieList=MutableLiveData<List<Result>>()


    val repo by lazy {
        Repository()
    }
    init {
        getPopularMovies()
        getTopRatedMovies()
        getUpcomingMovies()
    }

    private fun getTopRatedMovies() {
        viewModelScope.launch (IO){
            try {
                val response=repo.getTopRatedMovies()

                if (response.isSuccessful&&response.code()==200){
                    val body=response.body()
                    if (body != null) {
                        topRatedMovieList.postValue(body.results)
                    }
                }

            }catch (e:Exception){


            }
        }
    }

    private fun getPopularMovies(){

        viewModelScope.launch (IO){
            try {
                val response=repo.getPopularMovies()

                if (response.isSuccessful&&response.code()==200){
                    val body=response.body()
                    if (body != null) {
                        popularMovieList.postValue(body.results)
                    }
                }

            }catch (e:Exception){


            }
        }
    }
    private fun getUpcomingMovies(){
        viewModelScope.launch (IO){
            try {
                val response=repo.getUpcomingMovies()

                if (response.isSuccessful&&response.code()==200){
                    val body=response.body()
                    if (body != null) {
                        upcomingMovieList.postValue(body.results)
                    }
                }

            }catch (e:Exception){


            }
        }
    }
}