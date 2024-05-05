package com.example.bmicalculator

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.bmicalculator.databinding.ActivityResultBinding

class ResultActivity : AppCompatActivity() {

    private lateinit var binding: ActivityResultBinding
    companion object{
        const val BMI_KEY = "bmiKey"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = ActivityResultBinding.inflate(layoutInflater)
        setContentView(binding.root)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        val intent = intent
        val alinanVeri = intent.getFloatExtra(BMI_KEY, 0f)
        binding.resultText.text = alinanVeri.toString()

        if (binding.resultText.text.toString().toFloat() < 18.5) {
            binding.resultExplaination.text = "Eat more pies!"
            binding.background.setBackgroundResource(R.drawable.blue_color)
        } else if (binding.resultText.text.toString()
                .toFloat() < 24.9 && binding.resultText.text.toString().toFloat() > 18.5
        ) {
            binding.resultExplaination.text = "Fit as a fiddle!"
            binding.background.setBackgroundResource(R.drawable.green_color)


        } else {
            binding.resultExplaination.text = "Eat less pies!"
            binding.background.setBackgroundResource(R.drawable.red_color)


        }

        binding.recalculateButton.setOnClickListener {
            finish()

        }
    }
}