package com.example.findmovie.ui.fragment.movie_detail

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.findmovie.R
import com.example.findmovie.databinding.FragmentMovieDetailBinding
import com.example.findmovie.model.FavoritMovie
import com.example.findmovie.model.MovieResponseById

import com.example.findmovie.ui.adapter.VideoAdapter
import com.example.findmovie.utils.Constant
import com.squareup.picasso.Picasso
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MovieDetailFragment : Fragment() {


    private lateinit var binding: FragmentMovieDetailBinding
    private val viewModel by viewModels<MovieDetailViewModel>()
    private val args by navArgs<MovieDetailFragmentArgs>()
    private val adapter=VideoAdapter(emptyList())
    private lateinit var movieDetail: MovieResponseById
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding =
            DataBindingUtil.inflate(inflater, R.layout.fragment_movie_detail, container, false)
        // Inflate the layout for this fragment
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val movieId = args.movieId
        binding.vide0Rv.layoutManager=LinearLayoutManager(requireActivity(),LinearLayoutManager.HORIZONTAL,false)
        binding.vide0Rv.adapter=adapter

        viewModel.getMovieById(movieId.toLong())
        viewModel.getMovieVideosById(movieId.toLong())
        viewModel.isMovieIdExists(movieId)


        observes()
        binding.favFab.setOnClickListener {
            if(binding.favFab.tag=="nofav"){
                binding.favFab.tag="isfav"
                binding.favFab.setImageResource(R.drawable.fav)
                val movie=FavoritMovie(args.movieId,movieDetail.title,movieDetail.poster_path)
                viewModel.insertMovieFavorite(movie)
                return@setOnClickListener
            }
            if (binding.favFab.tag=="isfav"){
                binding.favFab.tag="nofav"
                val movie= FavoritMovie(args.movieId,movieDetail.title,movieDetail.poster_path)
                viewModel.deleteFavoriteMovie(movie)
                binding.favFab.setImageResource(R.drawable.no_fav)
                return@setOnClickListener
            }
        }

    }

    private fun observes() {

        viewModel.isMovieIdExistsLiveData.observe(viewLifecycleOwner){
            if (it){
                binding.favFab.setImageResource(R.drawable.fav)
                binding.favFab.tag="isfav"
                Log.e("isfav", "observes: is fav $it ", )

            }else{
                binding.favFab.setImageResource(R.drawable.no_fav)
                binding.favFab.tag="nofav"
                Log.e("TAG",(binding.favFab.tag=="nofav").toString())
                Log.e("nofav", "observes: is fav $it ", )


            }
        }

        viewModel.movieDetailLiveData.observe(viewLifecycleOwner){
            movieDetail=it
            Picasso.get().load("${Constant.IMAGE_BASE_URL}${it.backdrop_path}").into(binding.backdropPath)
            Picasso.get().load("${Constant.IMAGE_BASE_URL}${it.poster_path}").into(binding.poster)
            binding.overview.text=it.overview
            binding.title.text=it.title
            binding.releaseDate.text=it.release_date
            binding.imdbRating.text=it.vote_average.toString()
            binding.time.text="${it.runtime} minutes"

            var genre = ""
            val genres = it.genres
            genres.forEach { genre += it.name + ", " }
            genre = genre.dropLast(2)

            binding.genres.text=genre




        }
        viewModel.movieVideoLiveData.observe(viewLifecycleOwner){


            if (it != null) {
                adapter.updateList(it)
            }

        }


        viewModel.getFavs().observe(viewLifecycleOwner){
            Log.e("list",it.toString())
        }
    }

}