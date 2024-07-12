package com.example.bmicalculator

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.bmicalculator.databinding.ActivityHistoryBinding

class HistoryActivity : AppCompatActivity() {

    private lateinit var binding: ActivityHistoryBinding
    private lateinit var BMIRecordList: ArrayList<BMIRecord>
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = ActivityHistoryBinding.inflate(layoutInflater)
        setContentView(binding.root)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        binding.backBtnHistory.setOnClickListener {
            finish()
        }


        BMIRecordList = arrayListOf(
            BMIRecord("11.02.2022", 60.0, 170.0, "Fit as a Fiddle"),
            BMIRecord("21.04.2022", 80.0, 180.0, "Eat more pies!"),
            BMIRecord("11.02.2022", 60.0, 170.0, "Fit as a Fiddle"),
            BMIRecord("21.04.2022", 80.0, 180.0, "Eat more pies!"),
            BMIRecord("11.02.2023", 90.0, 140.0, "Eat less pies!")

        )

        val adapter = HistoryAdapter(BMIRecordList)
        binding.recyclerView.layoutManager = LinearLayoutManager(this)
        binding.recyclerView.adapter = adapter
    }
}