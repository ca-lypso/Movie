package com.example.findmovie.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.findmovie.databinding.MovieItemBinding
import com.example.findmovie.model.FavoritMovie
import com.example.findmovie.utils.Constant
import com.squareup.picasso.Picasso

class FavoriteAdapter (private val movieClickListener: FavoriteClickListeener,private var movieList:List<FavoritMovie>):
    RecyclerView.Adapter<FavoriteAdapter.ViewHolder>() {
    inner class  ViewHolder(val view: MovieItemBinding):RecyclerView.ViewHolder(view.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val view = MovieItemBinding.inflate(layoutInflater, parent, false)
        return ViewHolder(view)
    }

    override fun getItemCount(): Int {
        return  movieList.size
    }


    fun updateMovieList(newList:List<FavoritMovie>){
        this.movieList=newList
        notifyDataSetChanged()
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val movie=movieList[position]
        val b=holder.view

        b.movieCard.setOnClickListener {

            movieClickListener.movieClickListener(movie.id)
        }

        b.movieTitle.text=movie.title
        Picasso.get().load("${Constant.IMAGE_BASE_URL}${movie.posterPath}").into(b.poster)
    }


}
interface FavoriteClickListeener{
    fun movieClickListener(movie_id:Int)
}