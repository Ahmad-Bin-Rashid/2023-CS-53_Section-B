package com.example.appointmentapp

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class ConfirmationActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_confirmation)

        val name = intent.getStringExtra("NAME")
        val phone = intent.getStringExtra("PHONE")
        val email = intent.getStringExtra("EMAIL")
        val type = intent.getStringExtra("TYPE")
        val date = intent.getStringExtra("DATE")
        val time = intent.getStringExtra("TIME")
        val gender = intent.getStringExtra("GENDER")

        findViewById<TextView>(R.id.tvResultName).text = "Full Name: $name"
        findViewById<TextView>(R.id.tvResultPhone).text = "Phone: $phone"
        findViewById<TextView>(R.id.tvResultEmail).text = "Email: $email"
        findViewById<TextView>(R.id.tvResultType).text = "Type: $type"
        findViewById<TextView>(R.id.tvResultDate).text = "Date: $date"
        findViewById<TextView>(R.id.tvResultTime).text = "Time: $time"
        findViewById<TextView>(R.id.tvResultGender).text = "Gender: $gender"

        findViewById<Button>(R.id.btnBackHome).setOnClickListener {
            val intent = Intent(this, MainActivity::class.java)
            intent.flags = Intent.FLAG_ACTIVITY_CLEAR_TOP
            startActivity(intent)
        }
    }
}