package com.example.findmovie.ui.fragment.home

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.findmovie.R
import com.example.findmovie.databinding.FragmentHomeBinding
import com.example.findmovie.ui.adapter.MovieAdapter
import com.example.findmovie.ui.adapter.MovieClickListener
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class HomeFragment : Fragment() {
    private lateinit var binding: FragmentHomeBinding
    private val viewModel by viewModels<HomeViewModel>()
    private val topRatedAdapter by lazy {
        MovieAdapter(object : MovieClickListener {
            override fun movieClickListener(movie_id: Int) {
                findNavController().navigate(HomeFragmentDirections.actionHomeFragmentToMovieDetailFragment(movie_id))
            }

        }, emptyList())
    }
    private val popularAdapter by lazy {
        MovieAdapter(object : MovieClickListener {
            override fun movieClickListener(movie_id: Int) {
                findNavController().navigate(HomeFragmentDirections.actionHomeFragmentToMovieDetailFragment(movie_id))
            }

        }, emptyList())
    }
    private val upcomingAdapter by lazy {
        MovieAdapter(object : MovieClickListener {
            override fun movieClickListener(movie_id: Int) {
                findNavController().navigate(HomeFragmentDirections.actionHomeFragmentToMovieDetailFragment(movie_id))
            }

        }, emptyList())
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_home, container, false)
        // Inflate the layout for this fragment
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.popularRv.layoutManager =
            LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false)
        binding.topRatedRv.layoutManager =
            LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false)
        binding.upcomigRv.layoutManager =
            LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false)

        binding.popularRv.adapter = popularAdapter
        binding.topRatedRv.adapter = topRatedAdapter
        binding.upcomigRv.adapter = upcomingAdapter

        observes()
    }

    private fun observes() {
        viewModel.popularMovieList.observe(viewLifecycleOwner) {
            popularAdapter.updateMovieList(it)
        }
        viewModel.topRatedMovieList.observe(viewLifecycleOwner) {
            topRatedAdapter.updateMovieList(it)
        }
        viewModel.upcomingMovieList.observe(viewLifecycleOwner) {
            upcomingAdapter.updateMovieList(it)
        }

    }
}