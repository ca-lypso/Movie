package com.example.findmovie.ui.fragment.favorites

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import com.example.findmovie.R
import com.example.findmovie.databinding.FragmentFavoritesBinding
import com.example.findmovie.ui.adapter.FavoriteAdapter
import com.example.findmovie.ui.adapter.FavoriteClickListeener
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class FavoritesFragment : Fragment() {


    private lateinit var binding: FragmentFavoritesBinding
    private val viewModel by viewModels<FavoriteViewModel>()
    private val adapter by lazy {
        FavoriteAdapter(object : FavoriteClickListeener {
            override fun movieClickListener(movie_id: Int) {
                findNavController().navigate(FavoritesFragmentDirections.actionFavoritesFragmentToMovieDetailFragment(movie_id))
            }

        }, emptyList())
    }
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding= DataBindingUtil.inflate(inflater,R.layout.fragment_favorites, container, false)
        // Inflate the layout for this fragment
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.rv.layoutManager= GridLayoutManager(requireContext(),2)
        binding.rv.adapter=adapter

        viewModel.getFavs().observe(viewLifecycleOwner){
            adapter.updateMovieList(it)
        }

    }


}