package com.example.github

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainActivity : AppCompatActivity(), SwipeRefreshLayout.OnRefreshListener {
    private lateinit var swipeRefreshLayout: SwipeRefreshLayout

    override fun onRefresh() {
        val apiInterface = GithubApi.create().getFollowers()

        apiInterface.enqueue( object : Callback<List<Followers>>{
            override fun onResponse(
                call: Call<List<Followers>>,
                response: Response<List<Followers>>
            ) {

                response.body()?.forEach {
                    Log.d("onResponse; login:", it.login)
                }
            }

            override fun onFailure(call: Call<List<Followers>>, t: Throwable) {
                Log.d("onFailure", "Failed")
            }
        })
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        swipeRefreshLayout =  findViewById(R.id.swipe_refresh)
        swipeRefreshLayout.setOnRefreshListener(this)

    }
}