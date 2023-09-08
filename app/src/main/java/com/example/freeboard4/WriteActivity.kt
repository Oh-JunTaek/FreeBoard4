package com.example.freeboard4

import android.app.AlarmManager
import android.app.PendingIntent
import android.content.ContentValues.TAG
import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AlertDialog
import com.example.freeboard4.databinding.ActivityFourBinding
import com.example.freeboard4.databinding.ActivityWriteBinding
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.firestore.FirebaseFirestore
import java.util.concurrent.TimeUnit

class WriteActivity : AppCompatActivity() {

    private lateinit var binding: ActivityWriteBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityWriteBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        binding.btngomain.setOnClickListener {
            AlertDialog.Builder(this)
                .setTitle("Confirm")
                .setMessage("Would you like to save your post as a draft or discard it?")
                .setPositiveButton("Save Draft") { _, _ ->
                    // 임시 저장한다면 SharedPreferences에 내용을 저장합니다.
                    val sharedPref = getSharedPreferences("Drafts", Context.MODE_PRIVATE)
                    with(sharedPref.edit()) {
                        putString("draft_content", binding.editContent.text.toString())
                        apply()
                    }

                    // 7일 후에 이 임시 저장된 내용을 삭제하기 위한 알람 설정
                    val alarmManager = getSystemService(Context.ALARM_SERVICE) as AlarmManager
                    val intent = Intent(this, DeleteDraftReceiver::class.java)
                    val pendingIntent = PendingIntent.getBroadcast(this, 0, intent, PendingIntent.FLAG_UPDATE_CURRENT)

                    // 현재 시간으로부터 정확히 7일 후에 알람이 발생하도록 설정합니다.
                    alarmManager.set(AlarmManager.RTC_WAKEUP,
                        System.currentTimeMillis() + TimeUnit.DAYS.toMillis(7),
                        pendingIntent)

                    // FourActivity로 돌아갑니다.
                    startActivity(Intent(this@WriteActivity, FourActivity::class.java))
                    finish()
                }
                .setNegativeButton("Discard") { _, _ ->
                    // 아무런 작업도 수행하지 않고 바로 FourActivity로 돌아갑니다.
                    startActivity(Intent(this@WriteActivity, FourActivity::class.java))
                    finish()
                }
                .create().show()
        }
        binding.btnwritefin.setOnClickListener {
            Log.d("WriteActivity", "Button clicked")
            val title = binding.editTitle.text.toString()
            val content = binding.editContent.text.toString()

            // Firebase Firestore에 제목과 내용을 저장합니다.
            val db = FirebaseFirestore.getInstance()

            // 게시물의 정보를 Map 형태로 구성합니다.
            val post: MutableMap<String, Any> = HashMap()
            post["title"] = title
            post["content"] = content

            // 'Posts' 컬렉션에 새 문서를 추가하고, 성공/실패 여부를 로그로 출력합니다.
            db.collection("Posts")
                .add(post)
                .addOnSuccessListener { documentReference ->
                    Log.d(TAG, "DocumentSnapshot added with ID: ${documentReference.id}")
                }
                .addOnFailureListener { e ->
                    Log.w(TAG, "Error adding document", e)
                }

            // FourActivity로 돌아갑니다.
            startActivity(Intent(this@WriteActivity, FourActivity::class.java))
        }
    }
}