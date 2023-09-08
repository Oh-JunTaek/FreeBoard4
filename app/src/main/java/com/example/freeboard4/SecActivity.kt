package com.example.freeboard4

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.content.Intent
import android.net.Uri
import android.widget.Toast
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

        val sharedPref = getSharedPreferences("FreeBoard4", Context.MODE_PRIVATE)

        binding.btnlogin.setOnClickListener {
            val savedId = sharedPref.getString("id", "")
            val savedPassword = sharedPref.getString("password", "")

            if (binding.inputid.text.toString() == savedId && binding.inputpw.text.toString() == savedPassword) {
                // ID와 비밀번호가 일치하면 FourActivity로 이동합니다.
                val intent = Intent(this, FourActivity::class.java)
                startActivity(intent)
            } else {
                // ID나 비밀번호가 일치하지 않으면 오류 메시지를 표시합니다.
                Toast.makeText(this, "ID or Password is incorrect.", Toast.LENGTH_SHORT).show()
            }
        }
    }
}