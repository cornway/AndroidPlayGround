package com.github.app.ui

import android.animation.ObjectAnimator
import android.os.Bundle
import android.widget.*
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentStatePagerAdapter
import androidx.viewpager.widget.ViewPager
import com.example.github.R
import com.example.github.databinding.ActivityAppBinding
import com.github.app.ui.common.BaseFragment
import com.github.app.ui.mywork.MyWorkFragment
import com.github.app.ui.trendingfeed.TrendingFeedFragment

class MainActivity : MainActivityBase() {
    private val pages = linkedMapOf(
        Pair("Trending", TrendingFeedFragment()),
        Pair("My Work", MyWorkFragment())
    )
    private val spinnerItems = listOf(
        SpinnerAdapter.FragmentItem(TrendingFeedFragment(), "Trending"),
        SpinnerAdapter.FragmentItem(MyWorkFragment(), "My Work"),
        SpinnerAdapter.FragmentItem(MyWorkFragment(), "His Work")
    )
    private lateinit var mPager: ViewPager
    private var buttonPrev: Button? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val binding = ActivityAppBinding.inflate(layoutInflater)
        setContentView(binding.root)

        mPager = binding.viewPagerMain
        mPager.adapter = ScreenSlidePagerAdapter(supportFragmentManager, pages)

        binding.mainAppPrevButton.setOnClickListener {
            mPager.currentItem--
        }
        binding.mainAppNextButton.setOnClickListener {
            mPager.currentItem++
        }

        val spinner: Spinner = findViewById(R.id.app_main_add_quick_buttons_menu)
        val spinnerAdapter = SpinnerAdapter(context = this, items = spinnerItems)
        spinnerAdapter.onItemSelected = ::addPage
        spinner.adapter = spinnerAdapter
        spinner.onItemSelectedListener = spinnerAdapter
    }

    fun addPage(item: Pair<String, BaseFragment>) {

        pages[item.first] = item.second
        mPager.adapter?.notifyDataSetChanged()
        buttonPrev = addNextQuickButton(buttonPrev, item.first)
        buttonPrev?.setOnClickListener {
            mPager.currentItem =  ScreenSlidePagerAdapter.getIndexForKey(pages, item)
            it.animate().rotation(360f)
        }
    }
}