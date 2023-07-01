package com.example.findmovie.ui.fragment.search

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.findmovie.repo.Repository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SearchViewModel @Inject constructor(val repo: Repository): ViewModel() {
    val searcMovieList= MutableLiveData<List<com.example.findmovie.model.Result>>()



    fun searchMovie(query:String){
        viewModelScope.launch(Dispatchers.IO) {
            val response=repo.searchMovie(query)
            if (response.isSuccessful&&response.code()==200){
                val body=response.body()
                val movieList=body?.results

                if (movieList!=null){
                    searcMovieList.postValue(movieList!!)
                }

            }
        }
    }
}