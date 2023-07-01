package com.example.findmovie.ui.fragment.movie_detail

import android.os.Bundle
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

import com.example.findmovie.ui.adapter.VideoAdapter
import com.example.findmovie.utils.Constant
import com.squareup.picasso.Picasso


class MovieDetailFragment : Fragment() {

    private lateinit var binding: FragmentMovieDetailBinding
    private val viewModel by viewModels<MovieDetailViewModel>()
    private val args by navArgs<MovieDetailFragmentArgs>()
    private val adapter= VideoAdapter(emptyList())
    private lateinit var videoId:String
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

        viewModel.getMovieById(movieId)
        viewModel.getMovieVideosById(movieId)

        observes()

    }

    private fun observes() {
        viewModel.movieDetailLiveData.observe(viewLifecycleOwner){
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
            val videoList=it

            if (it != null) {
                adapter.updateList(it)
            }
//            val trailer=videoList?.find {
//                it.type=="Trailer"
//            }
//            if (trailer != null) {
//                videoId=trailer.key
//            }
//            getVideo()
        }
    }
//    private fun getVideo() {
//        binding.youtubePlayerView.addYouTubePlayerListener(object : AbstractYouTubePlayerListener() {
//            override fun onReady(youTubePlayer: YouTubePlayer) {
//                super.onReady(youTubePlayer)
//                youTubePlayer.cueVideo(videoId, 0f)
//            }
//        })
//    }

}