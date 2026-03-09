package com.example.appointmentapp

import android.app.DatePickerDialog
import android.app.TimePickerDialog
import android.content.Intent
import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.CheckBox
import android.widget.EditText
import android.widget.RadioButton
import android.widget.RadioGroup
import android.widget.Spinner
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import java.util.Calendar

class BookAppointmentActivity : AppCompatActivity() {

    private var selectedDate = ""
    private var selectedTime = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_book_appointment)

        val etFullName = findViewById<EditText>(R.id.etFullName)
        val etPhone = findViewById<EditText>(R.id.etPhone)
        val etEmail = findViewById<EditText>(R.id.etEmail)
        val spinnerApptType = findViewById<Spinner>(R.id.spinnerApptType)
        val btnSelectDate = findViewById<Button>(R.id.btnSelectDate)
        val tvSelectedDate = findViewById<TextView>(R.id.tvSelectedDate)
        val btnSelectTime = findViewById<Button>(R.id.btnSelectTime)
        val tvSelectedTime = findViewById<TextView>(R.id.tvSelectedTime)
        val rgGender = findViewById<RadioGroup>(R.id.rgGender)
        val cbTerms = findViewById<CheckBox>(R.id.cbTerms)
        val btnConfirmBooking = findViewById<Button>(R.id.btnConfirmBooking)

        // Setup Spinner
        val apptTypes = arrayOf("Doctor Consultation", "Dentist Appointment", "Eye Specialist", "Skin Specialist", "General Checkup")
        val adapter = ArrayAdapter(this, android.R.layout.simple_spinner_item, apptTypes)
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        spinnerApptType.adapter = adapter

        // Date Picker
        btnSelectDate.setOnClickListener {
            val c = Calendar.getInstance()
            val year = c.get(Calendar.YEAR)
            val month = c.get(Calendar.MONTH)
            val day = c.get(Calendar.DAY_OF_MONTH)

            val dpd = DatePickerDialog(this, { _, year, monthOfYear, dayOfMonth ->
                selectedDate = "$dayOfMonth/${monthOfYear + 1}/$year"
                tvSelectedDate.text = selectedDate
            }, year, month, day)
            dpd.show()
        }

        // Time Picker
        btnSelectTime.setOnClickListener {
            val c = Calendar.getInstance()
            val hour = c.get(Calendar.HOUR_OF_DAY)
            val minute = c.get(Calendar.MINUTE)

            val tpd = TimePickerDialog(this, { _, hourOfDay, minute ->
                selectedTime = String.format("%02d:%02d", hourOfDay, minute)
                tvSelectedTime.text = selectedTime
            }, hour, minute, true)
            tpd.show()
        }

        btnConfirmBooking.setOnClickListener {
            val name = etFullName.text.toString()
            val phone = etPhone.text.toString()
            val email = etEmail.text.toString()
            val apptType = spinnerApptType.selectedItem.toString()
            val genderId = rgGender.checkedRadioButtonId

            if (name.isEmpty()) {
                etFullName.error = "Name is required"
                return@setOnClickListener
            }
            if (phone.isEmpty()) {
                etPhone.error = "Phone is required"
                return@setOnClickListener
            }
            if (email.isEmpty()) {
                etEmail.error = "Email is required"
                return@setOnClickListener
            }
            if (selectedDate.isEmpty()) {
                Toast.makeText(this, "Please select a date", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }
            if (selectedTime.isEmpty()) {
                Toast.makeText(this, "Please select a time", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }
            if (genderId == -1) {
                Toast.makeText(this, "Please select gender", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }
            if (!cbTerms.isChecked) {
                Toast.makeText(this, "Please accept Terms and Conditions", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            val gender = findViewById<RadioButton>(genderId).text.toString()

            val intent = Intent(this, ConfirmationActivity::class.java).apply {
                putExtra("NAME", name)
                putExtra("PHONE", phone)
                putExtra("EMAIL", email)
                putExtra("TYPE", apptType)
                putExtra("DATE", selectedDate)
                putExtra("TIME", selectedTime)
                putExtra("GENDER", gender)
            }
            startActivity(intent)
        }
    }
}