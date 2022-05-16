package com.xizz.viewpager.ui.main

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.xizz.viewpager.R

private val TAB_TITLES = arrayOf(
    R.string.tab_text_1,
    R.string.tab_text_2
)

class SectionsPagerAdapter(private val activity: FragmentActivity) : FragmentStateAdapter(activity) {

    override fun getItemCount(): Int = 2

    override fun createFragment(position: Int): Fragment {
        return PlaceholderFragment.newInstance(position + 1)
    }

    fun getPageTitle(position: Int): CharSequence {
        return activity.resources.getString(TAB_TITLES[position])
    }
}