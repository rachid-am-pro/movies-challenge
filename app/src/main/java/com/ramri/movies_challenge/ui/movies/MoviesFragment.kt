package com.ramri.movies_challenge.ui.movies

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.ramri.MoviesChallenge.databinding.FragmentMoviesBinding
import com.ramri.movies_challenge.data.model.Movie
import com.ramri.movies_challenge.ui.movies.adapter.MoviesAdapter
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

@AndroidEntryPoint
class MoviesFragment : Fragment() {
    private var _binding: FragmentMoviesBinding? = null

    private val binding get() = _binding!!

    val viewModel: MoviesViewModel by viewModels()

    lateinit var moviesRecyclerView: RecyclerView
    lateinit var moviesAdapter: MoviesAdapter
    lateinit var movieList: ArrayList<Movie>

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentMoviesBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        moviesRecyclerView = binding.recyclerView

        movieList = ArrayList()

        val layoutManager = GridLayoutManager(requireActivity(), 2)

        moviesRecyclerView.layoutManager = layoutManager

        moviesAdapter = MoviesAdapter(movieList, requireActivity())

        moviesRecyclerView.adapter = moviesAdapter

        lifecycleScope.launch {
            viewModel.load()
            viewModel.movieList.collectLatest {
                moviesAdapter.setMovieList(it)
                moviesAdapter.notifyDataSetChanged()
            }
            moviesAdapter.notifyDataSetChanged()
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}