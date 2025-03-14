package com.example.mvvmproject.ui

import android.app.DatePickerDialog
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.mvvmproject.R
import com.example.mvvmproject.databinding.ActivityHomeBinding
import com.example.mvvmproject.databinding.ActivitySampleBinding
import java.util.Calendar


class SampleActivity : AppCompatActivity() {

    private lateinit var binding: ActivitySampleBinding
    private lateinit var datePickerDialog: DatePickerDialog

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySampleBinding.inflate(layoutInflater)
        setContentView(binding.root)
        initDatePicker()

        binding.btnShowDialog.setOnClickListener {
            datePickerDialog.show()
        }
    }

    private fun initDatePicker() {
        val calendar = Calendar.getInstance()
        datePickerDialog = DatePickerDialog(
            this,
            { _, year, month, dayOfMonth ->
                // Menampilkan tanggal yang dipilih
                binding.tvDate.text = "Tanggal yang dipilih: $dayOfMonth/${month + 1}/$year"
            },
            2025,
            7,
            calendar.get(Calendar.DAY_OF_MONTH))
    }
}