package com.example.freeboard4

import android.content.ContentValues.TAG
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.content.Intent
import android.os.Handler
import android.os.Looper
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.TextView
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase


class MainActivity : AppCompatActivity() {
//    private var testNumber = 0
    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
//        val db = Firebase.firestore
//
//        val city = hashMapOf(
//            "name" to "Los Angeles",
//            "state" to "CA",
//            "country" to "USA"
//        )
//        Log.d("MyTag", "===============")
//        db.collection("cities").document("LA")
//            .set(city)
//            .addOnSuccessListener { Log.d(TAG, "DocumentSnapshot successfully written!") }
//            .addOnFailureListener { e -> Log.w(TAG, "Error writing document", e) }
//        Log.d("MyTag", "===============")


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
//    companion object {
//        const val TAG = "MyLog"
//    }

}