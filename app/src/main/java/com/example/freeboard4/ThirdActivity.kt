package com.example.freeboard4

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.freeboard4.databinding.ActivityThirdBinding

class ThirdActivity : AppCompatActivity() {
    private lateinit var binding: ActivityThirdBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityThirdBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // 버튼 클릭 시 입력된 회원 정보를 SharedPreferences에 저장합니다.
        binding.btnreturn.setOnClickListener {
            // SharedPreferences 인스턴스 획득
            val sharedPref = getSharedPreferences("FreeBoard4", Context.MODE_PRIVATE)

            with (sharedPref.edit()) {
                putString("id", binding.newid.text.toString())
                putString("password", binding.newpw.text.toString())
                putString("email", binding.newemail.text.toString())
                putString("phone", binding.newphone.text.toString())
                apply()
            }
            // SecActivity로 이동합니다.
            val intent = Intent(this, SecActivity::class.java)
            startActivity(intent)

            // 다른 작업들...
        }
    }
}