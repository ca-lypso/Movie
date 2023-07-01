package com.example.findmovie.network

import com.example.findmovie.model.MovieResponse
import com.example.findmovie.model.MovieResponseById
import com.example.findmovie.model.MovieVideoResponseById
import com.example.findmovie.utils.Constant.AUTH_KEY
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.Path
import retrofit2.http.Query

interface WebApiService {


    //  Bearer den sonra sizde olan size aid kodu yazmalisiz

    @Headers("accept:application/json",
        "Authorization:Bearer $AUTH_KEY")
    @GET("movie/popular")
    suspend fun getPopularMovies(
        @Query("language") lan:String="en-US",
        @Query("page") page: Int=1): Response<MovieResponse>


    @Headers("accept:application/json",
        "Authorization:Bearer $AUTH_KEY")
    @GET("movie/top_rated")
    suspend fun getTopRatedMovies(
        @Query("language") lan:String="en-US",
        @Query("page") page: Int=1): Response<MovieResponse>


    @Headers("accept:application/json",
        "Authorization:Bearer $AUTH_KEY")
    @GET("movie/upcoming")
    suspend fun getUpComingMovies(
        @Query("language") lan:String="en-US",
        @Query("page") page: Int=1): Response<MovieResponse>


    @Headers("accept:application/json",
        "Authorization:Bearer $AUTH_KEY")
    @GET("movie/{id}")
    suspend fun movieMovieById(
        @Path("id") movie_id:Long,
        @Query("language") lan:String="en-US",
        @Query("page") page: Int=1): Response<MovieResponseById>


    @Headers("accept:application/json",
        "Authorization:Bearer $AUTH_KEY")
    @GET("movie/{id}/videos")
    suspend fun getMovieVideosById(
        @Path("id") movie_id:Long,
        @Query("language") lan:String="en-US"
    ): Response<MovieVideoResponseById>



    @Headers("accept:application/json",
        "Authorization:Bearer $AUTH_KEY")
    @GET(" search/movie")
    suspend fun searchMovie(
        @Query("query") query: String,
        @Query("include_adult") include_adult:Boolean=false,
        @Query("language") lan:String="en-US",
        @Query("page") page: Int=1
    ): Response<MovieResponse>


}