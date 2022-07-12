package com.github.app.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentStatePagerAdapter
import androidx.viewpager.widget.ViewPager
import com.example.github.databinding.ActivityAppBinding
import com.github.app.ui.mywork.MyWorkFragment
import com.github.app.ui.trendingfeed.TrendingFeedFragment

class MainActivity : FragmentActivity() {
    private lateinit var mPager: ViewPager
    private val NUM_PAGES: Int = 2

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val binding = ActivityAppBinding.inflate(layoutInflater)
        setContentView(binding.root)

        mPager = binding.viewPagerMain
        mPager.adapter = ScreenSlidePagerAdapter(supportFragmentManager)

        binding.mainAppPrevButton.setOnClickListener {
            mPager.currentItem--
        }
        binding.mainAppNextButton.setOnClickListener {
            mPager.currentItem++
        }
    }

    private inner class ScreenSlidePagerAdapter(fm: FragmentManager) :
        FragmentStatePagerAdapter(fm) {
        override fun getCount(): Int = NUM_PAGES

        override fun getItem(position: Int): Fragment =
            when (position % NUM_PAGES) {
                0 -> TrendingFeedFragment()
                else -> MyWorkFragment()
            }
    }
}