package com.example.freeboard4

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.content.Intent
import android.net.Uri
import com.example.freeboard4.databinding.ActivitySecBinding

class SecActivity : AppCompatActivity() {
    private lateinit var binding: ActivitySecBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivitySecBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.btnfind.setOnClickListener {
            val url = "https://dhwnsxor.netlify.app/"
            val intent = Intent(Intent.ACTION_VIEW, Uri.parse(url))
            startActivity(intent)
        }

        binding.btnnew.setOnClickListener {
            val intent = Intent(this, ThirdActivity::class.java)
            startActivity(intent)
        }
    }
}