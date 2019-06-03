package com.example.cubosteste.ui.movies.view

import android.app.SearchManager
import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.View
import android.widget.Toast
import androidx.appcompat.widget.SearchView
import androidx.recyclerview.widget.GridLayoutManager
import com.example.cubosteste.R
import com.example.cubosteste.data.model.movies.MovieResponse
import com.example.cubosteste.presentation.movies.MovieContract
import com.example.cubosteste.ui.movie_detail.ActivityMovieDetail
import com.example.cubosteste.ui.movies.adapter.MoviesPageAdapter
import com.example.cubosteste.ui.movies.adapter.MoviesRecyclerAdapter
import kotlinx.android.synthetic.main.activity_main.*
import org.koin.android.ext.android.inject

class HomeActivity : AppCompatActivity(), MovieContract.View {

    override val presenter by inject<MovieContract.Presenter>()

    private val recyclerAdapter by lazy { MoviesRecyclerAdapter(::callbackMovieClicked) }

    val adapter by lazy {
        MoviesPageAdapter(
            supportFragmentManager
        )
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setSupportActionBar(toolbar)

        setupPager()

        listGeneric.layoutManager = GridLayoutManager(this, 2)
        listGeneric.adapter = recyclerAdapter

    }

    override fun onResume() {
        super.onResume()
        presenter.view = this
    }

    private fun setupPager() {
        adapter.addFragment(FragmentActionMovie.newInstance(::callbackMovieClicked))
        adapter.addFragment(FragmentDramaMovie.newInstance(::callbackMovieClicked))
        adapter.addFragment(FragmentFantasyMovie.newInstance(::callbackMovieClicked))
        adapter.addFragment(FragmentFictionMovie.newInstance(::callbackMovieClicked))

        viewPager.adapter = adapter
        tableLayout.setupWithViewPager(viewPager)
    }

    private fun callbackMovieClicked(content: MovieResponse) {
        val intent = Intent(this, ActivityMovieDetail::class.java)
        intent.putExtra(ActivityMovieDetail.IMAGE_PATH, content.posterPath)
        intent.putExtra(ActivityMovieDetail.MOVIE_DESCRIPTION, content.overview)
        intent.putExtra(ActivityMovieDetail.MOVIE_TITLE, content.title)
        startActivity(intent)
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {

        val menuInflater = menuInflater
        menuInflater.inflate(R.menu.search_item, menu)
        val searchItem = menu.findItem(R.id.action_search)
        val searchManager = getSystemService(Context.SEARCH_SERVICE) as SearchManager

        var searchView: SearchView? = null
        if (searchItem != null) {
            searchView = searchItem.actionView as SearchView
            observerSearchText(searchView)
        }
        searchView?.setSearchableInfo(searchManager.getSearchableInfo(componentName))
        searchView?.isSubmitButtonEnabled = true

        return true
    }

    private fun observerSearchText(searchText: SearchView) {
        searchText.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                searchMovieMode(query?.isEmpty() ?: true)
                Toast.makeText(this@HomeActivity, "Pesquisar: $query", Toast.LENGTH_SHORT).show()
                progress.visibility = View.VISIBLE
                presenter.getMoviesByQuery(query ?: "")
                return true
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                if (newText?.isEmpty() != false) {
                    searchMovieMode(true)
                }

                //if (newText?.isEmpty() == false) presenter.getMoviesByQuery(newText ?: "")
                return true
            }

        })
    }

    private fun searchMovieMode(isEmpty: Boolean) {

        if (isEmpty) {
            viewPager.visibility = View.VISIBLE
            tableLayout.visibility = View.VISIBLE
            listGeneric.visibility = View.INVISIBLE
        } else {
            viewPager.visibility = View.GONE
            tableLayout.visibility = View.GONE
            listGeneric.visibility = View.VISIBLE
        }
    }

    override var isActive: Boolean
        get() = TODO("not implemented") //To change initializer of created properties use File | Settings | File Templates.
        set(value) {}

    override fun showMovies(movies: List<MovieResponse>) {
        Log.d("Home", "moviesfoi")
        movies.let {
            Log.d("Home", "moviesfoiii")
            recyclerAdapter.setList(it)
            recyclerAdapter.notifyDataSetChanged()
            progress.visibility = View.GONE
            tvMovieError.visibility = View.INVISIBLE
        }
    }

    override fun setLoadingIndicator(show: Boolean) {
        if (show) {
            progress.visibility = View.VISIBLE
        } else progress.visibility = View.GONE
    }

    override fun showMovieNotFound() {
        Toast.makeText(this, "Não foi possível encontrar esse filme no momento!", Toast.LENGTH_LONG).show()
        progress.visibility = View.GONE
    }
}
