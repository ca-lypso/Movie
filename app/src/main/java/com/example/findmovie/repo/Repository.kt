package com.example.findmovie.repo

import com.example.findmovie.model.MovieResponse
import com.example.findmovie.model.MovieResponseById
import com.example.findmovie.model.MovieVideoResponseById
import com.example.findmovie.network.ApiUtils
import retrofit2.Response

class Repository {
    private val api by lazy { ApiUtils.instance }

   suspend fun getPopularMovies(): Response<MovieResponse> {
      return api.getPopularMovies()
   }
   suspend fun getTopRatedMovies(): Response<MovieResponse> {
      return api.getTopRatedMovies()
   }
   suspend fun getUpcomingMovies(): Response<MovieResponse> {
      return api.getUpComingMovies()
   }
   suspend fun getMovieById(movie_id:Int): Response<MovieResponseById> {
      return api.movieMovieById(movie_id)
   }
   suspend fun getMovieVideosById(movie_id:Int): Response<MovieVideoResponseById> {
      return api.getMovieVideosById(movie_id)
   }

}