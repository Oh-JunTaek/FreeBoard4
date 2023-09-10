package com.example.freeboard4

import android.content.ContentValues.TAG
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.google.firebase.firestore.FirebaseFirestore

class FBActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_fbactivity)

        val db = FirebaseFirestore.getInstance()

        db.collection("Posts").document("postId")
            .get()
            .addOnSuccessListener { document ->

                if (document != null) {
                    Log.d(TAG, "DocumentSnapshot data: ${document.data}")
                    val title = document.getString("title") // Replace with your actual field name
                    val content = document.getString("content") // Replace with your actual field name
                    // Display the title and content in your UI here.
                } else {
                    Log.d(TAG, "No such document")
                }
            }
            .addOnFailureListener { exception ->
                Log.d(TAG, "get failed with ", exception)
            }
    }
}