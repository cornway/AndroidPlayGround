
package com.example.reposapp

import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import com.example.github.*
import github.domain.viewmodel.modules.githubModules
import org.koin.core.context.startKoin

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_repos)
        var button: Button = findViewById(R.id.repositories_button)

        button.setOnClickListener {
            val intent = Intent(this, MyRepoActivity::class.java)
            intent.data = (Uri.parse("cornway"))
            startActivity(intent)
        }
        button = findViewById(R.id.trending_repos_button)
        button.setOnClickListener {
            val intent = Intent(this, RepositoriesActivity::class.java)
            intent.data = (Uri.parse(""))
            startActivity(intent)
        }
    }
}