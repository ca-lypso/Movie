package com.example.findmovie.repo

import androidx.lifecycle.LiveData
import com.example.findmovie.local.MovieDao
import com.example.findmovie.model.FavoritMovie
import com.example.findmovie.model.MovieResponse
import com.example.findmovie.model.MovieResponseById
import com.example.findmovie.model.MovieVideoResponseById
import com.example.findmovie.network.ApiUtils
import retrofit2.Response
import javax.inject.Inject

class Repository @Inject constructor(private val db: MovieDao){
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
   suspend fun getMovieById(movie_id:Long): Response<MovieResponseById> {
      return api.movieMovieById(movie_id)
   }
   suspend fun getMovieVideosById(movie_id:Long): Response<MovieVideoResponseById> {
      return api.getMovieVideosById(movie_id)
   }

   suspend fun searchMovie(query:String): Response<MovieResponse> {
      return api.searchMovie(query)
   }

   fun getAllFavMovies(): LiveData<List<FavoritMovie>> {
      return db.getAllFavoriteMovies()
   }

   suspend fun insertMovieFavorite(movie:FavoritMovie){
      return db.insertFavoriteMovie(movie)
   }

   suspend fun deleteFavoriteMovie(movie:FavoritMovie){
      return db.deleteFavoriteMovie(movie)
   }

   suspend fun isMovieIdExists(movieId:Int): Boolean {
      return db.isMovieIdExists(movieId)
   }

}