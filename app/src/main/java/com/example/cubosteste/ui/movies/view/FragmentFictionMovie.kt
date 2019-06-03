package com.example.cubosteste.ui.movies.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.GridLayoutManager
import com.example.cubosteste.R
import com.example.cubosteste.app.utils.Constants
import com.example.cubosteste.data.model.movies.MovieResponse
import com.example.cubosteste.presentation.movies.MovieContract
import com.example.cubosteste.ui.movies.adapter.MoviesRecyclerAdapter
import kotlinx.android.synthetic.main.fragment_movies_content.*
import org.koin.android.ext.android.inject

class FragmentFictionMovie : Fragment(), MovieContract.View {
    override fun showMovieNotFound() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override val presenter by inject<MovieContract.Presenter>()

    val adapter by lazy { MoviesRecyclerAdapter(callbackClick) }

    lateinit var callbackClick: ((MovieResponse) -> Unit)


    override var isActive: Boolean = false
        get() = isAdded

    override fun onResume() {
        super.onResume()
        recycler.layoutManager = GridLayoutManager(requireActivity(), 2)
        recycler.adapter = adapter
        presenter.view = this
        presenter.getMovies(Constants.FICTION_REQUEST_ID, 1)
    }

    override fun onCreateView(inflater: LayoutInflater,
                              container: ViewGroup?,
                              savedInstanceState: Bundle?
    ): View? {
        super.onCreateView(inflater, container, savedInstanceState)
        return inflater.inflate(R.layout.fragment_movies_content, container, false)
    }

    override fun showMovies(movies: List<MovieResponse>) {
        movies.let {
            adapter.setList(movies)
            adapter.notifyDataSetChanged()
        }
    }

    override fun setLoadingIndicator(show: Boolean) {
        //
    }

    override fun onDestroy() {
        super.onDestroy()
        presenter.destroy()
    }

    companion object {
        fun newInstance(callback: ((MovieResponse) -> Unit)): FragmentFictionMovie {
            val frag = FragmentFictionMovie()
            frag.callbackClick = callback
            return frag
        }
    }
}