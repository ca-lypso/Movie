package com.example.findmovie.network

import com.example.findmovie.model.MovieResponse
import com.example.findmovie.model.MovieResponseById
import com.example.findmovie.model.MovieVideoResponseById
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.Path
import retrofit2.http.Query

interface WebApiService {

    //eyJhbGciOiJIUzI1NiJ9.eyJhdWQiOiJlOGQzZWZiNTZmNjFkYjhjNmMyYzIxOTY1MmEyZTUxZCIsInN1YiI6IjYzODYzMjVhMDM5OGFiMDA3ZjhhYmM3OSIsInNjb3BlcyI6WyJhcGlfcmVhZCJdLCJ2ZXJzaW9uIjoxfQ.ISjbVnvHqbX_mWAsXPbg76B-te4ZWWJmszcxt45x5wI

    @Headers("accept:application/json",
        "Authorization:Bearer eyJhbGciOiJIUzI1NiJ9.eyJhdWQiOiJlOGQzZWZiNTZmNjFkYjhjNmMyYzIxOTY1MmEyZTUxZCIsInN1YiI6IjYzODYzMjVhMDM5OGFiMDA3ZjhhYmM3OSIsInNjb3BlcyI6WyJhcGlfcmVhZCJdLCJ2ZXJzaW9uIjoxfQ.ISjbVnvHqbX_mWAsXPbg76B-te4ZWWJmszcxt45x5wI")
    @GET("movie/popular")
    suspend fun getPopularMovies(
                          @Query("language") lan:String="en-US",
                          @Query("page") page: Int=1): Response<MovieResponse>


    @Headers("accept:application/json",
        "Authorization:Bearer eyJhbGciOiJIUzI1NiJ9.eyJhdWQiOiJlOGQzZWZiNTZmNjFkYjhjNmMyYzIxOTY1MmEyZTUxZCIsInN1YiI6IjYzODYzMjVhMDM5OGFiMDA3ZjhhYmM3OSIsInNjb3BlcyI6WyJhcGlfcmVhZCJdLCJ2ZXJzaW9uIjoxfQ.ISjbVnvHqbX_mWAsXPbg76B-te4ZWWJmszcxt45x5wI")
    @GET("movie/top_rated")
    suspend fun getTopRatedMovies(
        @Query("language") lan:String="en-US",
        @Query("page") page: Int=1): Response<MovieResponse>


    @Headers("accept:application/json",
        "Authorization:Bearer eyJhbGciOiJIUzI1NiJ9.eyJhdWQiOiJlOGQzZWZiNTZmNjFkYjhjNmMyYzIxOTY1MmEyZTUxZCIsInN1YiI6IjYzODYzMjVhMDM5OGFiMDA3ZjhhYmM3OSIsInNjb3BlcyI6WyJhcGlfcmVhZCJdLCJ2ZXJzaW9uIjoxfQ.ISjbVnvHqbX_mWAsXPbg76B-te4ZWWJmszcxt45x5wI")
    @GET("movie/upcoming")
    suspend fun getUpComingMovies(
        @Query("language") lan:String="en-US",
        @Query("page") page: Int=1): Response<MovieResponse>


    @Headers("accept:application/json",
        "Authorization:Bearer eyJhbGciOiJIUzI1NiJ9.eyJhdWQiOiJlOGQzZWZiNTZmNjFkYjhjNmMyYzIxOTY1MmEyZTUxZCIsInN1YiI6IjYzODYzMjVhMDM5OGFiMDA3ZjhhYmM3OSIsInNjb3BlcyI6WyJhcGlfcmVhZCJdLCJ2ZXJzaW9uIjoxfQ.ISjbVnvHqbX_mWAsXPbg76B-te4ZWWJmszcxt45x5wI")
    @GET("movie/{id}")
    suspend fun movieMovieById(
        @Path("id") movie_id:Int,
        @Query("language") lan:String="en-US",
        @Query("page") page: Int=1): Response<MovieResponseById>


    @Headers("accept:application/json",
        "Authorization:Bearer eyJhbGciOiJIUzI1NiJ9.eyJhdWQiOiJlOGQzZWZiNTZmNjFkYjhjNmMyYzIxOTY1MmEyZTUxZCIsInN1YiI6IjYzODYzMjVhMDM5OGFiMDA3ZjhhYmM3OSIsInNjb3BlcyI6WyJhcGlfcmVhZCJdLCJ2ZXJzaW9uIjoxfQ.ISjbVnvHqbX_mWAsXPbg76B-te4ZWWJmszcxt45x5wI")
    @GET("movie/{id}/videos")
    suspend fun getMovieVideosById(
        @Path("id") movie_id:Int,
        @Query("language") lan:String="en-US"
        ): Response<MovieVideoResponseById>

}