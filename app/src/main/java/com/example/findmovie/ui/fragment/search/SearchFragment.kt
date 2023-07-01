package com.example.findmovie.ui.fragment.search

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.SearchView
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import com.example.findmovie.R
import com.example.findmovie.databinding.FragmentSearchBinding
import com.example.findmovie.ui.adapter.MovieAdapter
import com.example.findmovie.ui.adapter.MovieClickListener
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class SearchFragment : Fragment() {

    private lateinit var binding: FragmentSearchBinding
    private val viewModel by viewModels<SearchViewModel> ()
    private val adapter by lazy {
        MovieAdapter(object : MovieClickListener {
            override fun movieClickListener(movie_id: Int) {
                findNavController().navigate(SearchFragmentDirections.actionSearchFragmentToMovieDetailFragment(movie_id))
            }

        }, emptyList())
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding= DataBindingUtil.inflate(inflater,R.layout.fragment_search, container, false)

        binding.rv.layoutManager= GridLayoutManager(requireContext(),2)
        binding.rv.adapter=adapter
        observes()
        setSearchViewListener()


        // Inflate the layout for this fragment
        return binding.root
    }

    private fun setSearchViewListener(){

        binding.searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener,
            androidx.appcompat.widget.SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String): Boolean {
                if (query.trim() != "") {
                    binding.searchView.clearFocus()
                    viewModel.searchMovie(query)
                }
                return true
            }

            override fun onQueryTextChange(newText: String): Boolean {

                if (newText.trim() == "") {

                } else {
                    viewModel.searchMovie(newText)
                }
                return true
            }


        })

    }



    private fun observes(){
        viewModel.searcMovieList.observe(viewLifecycleOwner){
            adapter.updateMovieList(it)
        }

    }




}