package com.example.freeboard4

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent

class DeleteDraftReceiver : BroadcastReceiver() {

    override fun onReceive(context: Context?, intent: Intent?) {
        context?.let {
            val sharedPref = it.getSharedPreferences("Drafts", Context.MODE_PRIVATE)
            with(sharedPref.edit()) {
                remove("draft_content")
                apply()
            }
        }
    }
}