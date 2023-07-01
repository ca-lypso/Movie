package com.example.findmovie.local

import androidx.lifecycle.LiveData
import androidx.room.*
import com.example.findmovie.model.FavoritMovie

@Dao
interface MovieDao {

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertFavoriteMovie(movie: FavoritMovie)

    @Query("SELECT * FROM favorite_movies")
    fun getAllFavoriteMovies(): LiveData<List<FavoritMovie>>

    @Delete
    suspend fun deleteFavoriteMovie(movie: FavoritMovie)

    @Query("SELECT EXISTS(SELECT 1 FROM favorite_movies WHERE id = :movieId LIMIT 1)")
    suspend fun isMovieIdExists(movieId: Int): Boolean
}