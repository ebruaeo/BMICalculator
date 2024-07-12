package com.example.bmicalculator

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.bmicalculator.databinding.ActivityDetailBinding

class DetailActivity : AppCompatActivity() {

    private lateinit var binding: ActivityDetailBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = ActivityDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        val adapterChosenDate = intent
        var chosenDateDetail = adapterChosenDate.getSerializableExtra("chosenDate") as BMIRecord
        binding.dateTextView.text = chosenDateDetail.date
        binding.heightTextDetail.text = chosenDateDetail.height.toString()
        binding.weightTextDetail.text = chosenDateDetail.weight.toString()
        binding.resultTextDetail.text = chosenDateDetail.result

        binding.backBtnDetail.setOnClickListener {
            finish()
        }
    }
}