package com.example.bai_tap

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.bai_tap.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
                override fun onCreate(savedInstanceState: Bundle?) {
            super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        var numbersList = mutableListOf(
            Numbers(997),
            Numbers(998),
            Numbers(999),
            Numbers(1000),
            Numbers(1001),
        )

        val adapter = NumbersAdapter(numbersList)
        binding.recyclerView.adapter = adapter
        binding.recyclerView.layoutManager = LinearLayoutManager(this)
    }
}