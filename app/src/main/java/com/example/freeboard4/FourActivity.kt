package com.example.freeboard4

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import com.example.freeboard4.databinding.ActivityFourBinding

class FourActivity : AppCompatActivity() {

    private lateinit var binding: ActivityFourBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityFourBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        // 초기 상태 설정. 시작 시에는 view1만 보이도록 합니다.
        binding.view1.visibility = View.VISIBLE
        binding.view2.visibility = View.GONE

        binding.btnchange.setOnCheckedChangeListener { buttonView, isChecked ->
            if (isChecked) {
                // 스위치가 ON 상태일 때는 view2를 보이게 하고, view1을 숨깁니다.
                binding.view1.visibility = View.GONE
                binding.view2.visibility = View.VISIBLE
            } else {
                // 스위치가 OFF 상태일 때는 반대로 작동합니다.
                binding.view2.visibility = View.GONE
                binding.view1.visibility = View.VISIBLE
            }
        }
    }
}