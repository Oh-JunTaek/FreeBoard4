package com.example.freeboard4

import android.R
import android.content.ContentValues.TAG
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.ArrayAdapter
import com.example.freeboard4.databinding.ActivityFourBinding
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import com.google.firebase.firestore.FirebaseFirestore

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

        binding.btnwrite.setOnClickListener {
            val intent = Intent(this, WriteActivity::class.java)
            startActivity(intent)
        }
    }

    override fun onResume() {
        super.onResume()

        val db = FirebaseFirestore.getInstance()
        db.collection("Posts")
            .get()
            .addOnSuccessListener { result ->
                // Firestore에서 'Posts' 컬렉션의 모든 문서의 데이터를 리스트로 만듭니다.
                val titleList = result.mapNotNull { document -> document.getString("title") }

                // 불러온 게시글 목록을 ListView에 표시합니다.
                val adapter = ArrayAdapter(this@FourActivity, android.R.layout.simple_list_item_1, titleList)
                binding.view2.adapter = adapter
            }
            .addOnFailureListener { exception ->
                Log.w(TAG, "Error getting documents.", exception)
            }
    }
}