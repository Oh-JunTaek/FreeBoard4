package com.example.freeboard4

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.content.Intent
import android.os.Handler
import android.os.Looper
import android.view.View
import android.widget.Button
import android.widget.TextView


class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val txtHi = findViewById<TextView>(R.id.txthi)
        val btnStart = findViewById<Button>(R.id.btnstart)

        // 초기에는 버튼을 숨깁니다.
        btnStart.visibility = View.INVISIBLE

        Handler(Looper.getMainLooper()).postDelayed({
            // 3초 후에 텍스트 뷰를 숨기고 버튼을 보여줍니다.
            txtHi.visibility = View.INVISIBLE
            btnStart.visibility = View.VISIBLE

            // 버튼 클릭 시 SecActivity로 이동합니다.
            btnStart.setOnClickListener {
                val intent = Intent(this, SecActivity::class.java)
                startActivity(intent)
            }
        }, 3000) // delay for 3 seconds (3000 milliseconds)
    }
}