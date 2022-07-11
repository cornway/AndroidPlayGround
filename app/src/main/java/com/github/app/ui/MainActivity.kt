package com.github.app.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.fragment.app.add
import androidx.fragment.app.commit
import com.example.github.R
import com.github.app.ui.mywork.MyWorkFragment
import com.github.app.ui.trendingfeed.TrendingFeedFragment
import org.koin.androidx.fragment.android.replace

class MainActivity : AppCompatActivity(R.layout.activity_app) {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        if (savedInstanceState == null) {
            val bundle = bundleOf("userName" to "cornway")
            supportFragmentManager.commit {
                setReorderingAllowed(true)
                add<TrendingFeedFragment>(R.id.fragment_trending_feed, args = bundle)
            }
        }

        var button: Button = findViewById(R.id.repositories_button)
        button.setOnClickListener {
            setupFragment<TrendingFeedFragment>(R.id.fragment_trending_feed)
        }
        button = findViewById(R.id.trending_repos_button)
        button.setOnClickListener {
            val bundle = bundleOf("ownerName" to "cornway")
            setupFragment<MyWorkFragment>(R.id.fragment_trending_feed, bundle)
        }
    }

    inline fun <reified T : Fragment>setupFragment(id: Int, bundle: Bundle? = null) {
        supportFragmentManager.commit {
            setReorderingAllowed(true)
            replace<T>(id, args = bundle)
        }
    }
}