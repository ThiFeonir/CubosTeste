package com.example.cubosteste.ui.movies.adapter

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import com.example.cubosteste.ui.movies.view.FragmentActionMovie

class MoviesPageAdapter(
    fm: FragmentManager
) : FragmentPagerAdapter(fm, BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT) {

    private val fragments: ArrayList<Fragment> = ArrayList()

    override fun getItem(position: Int): Fragment {
        return fragments[position]
    }

    override fun getCount(): Int {
        return 4
    }

    override fun getPageTitle(position: Int): CharSequence? {
        return when (position) {
            0 -> "Ação"
            1 -> "Drama"
            2 -> "Fantasia"
            3 -> "Ficção"
            else ->  ""
        }
    }

    fun addFragment(fragment: Fragment) {
        fragments.add(fragment)
    }
}