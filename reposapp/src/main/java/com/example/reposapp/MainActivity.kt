
package com.example.reposapp

import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import com.example.github.*

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_repos)
        val button: Button = findViewById(R.id.repositories_button)

        button.setOnClickListener {
            val intent = Intent(this, ReposActivity::class.java)
            intent.data = (Uri.parse("cornway"))
            startActivity(intent)
        }
    }
}