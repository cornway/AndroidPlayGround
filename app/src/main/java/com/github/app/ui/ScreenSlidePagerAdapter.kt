package com.github.app.ui

import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentStatePagerAdapter
import com.github.app.ui.common.BaseFragment

class ScreenSlidePagerAdapter(fm: FragmentManager,
                              private val pages: LinkedHashMap<String, BaseFragment>) :
    FragmentStatePagerAdapter(fm) {
    override fun getCount(): Int = pages.size
    override fun getItem(position: Int): BaseFragment {
        val key = pages.keys.toTypedArray()[position % pages.size]
        return pages[key] ?: throw Exception()
    }

    companion object {
        fun getIndexForKey(
            pages: LinkedHashMap<String, BaseFragment>,
            item: Pair<String, BaseFragment>
        ): Int {
            var index = 0
            pages.keys.toTypedArray().forEachIndexed { i, key ->
                if (key == item.first) {
                    index = i
                }
            }
            return index
        }
    }
}