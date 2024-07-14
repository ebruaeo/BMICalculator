package com.example.bmicalculator

import android.content.Intent
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.annotation.DrawableRes
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.bmicalculator.databinding.ActivityResultBinding
import org.w3c.dom.Text

class ResultActivity : AppCompatActivity() {

    private lateinit var binding: ActivityResultBinding

    companion object {
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

        val bmiValue = intent.getFloatExtra(BMI_KEY, 0f)
        binding.resultText.text = bmiValue.toString()
        setResultScreen(bmiValue)

        binding.recalculateButton.setOnClickListener {
            val intent = Intent(this@ResultActivity, MainActivity::class.java)
            intent.putExtra("result", binding.resultExplaination.text)
            setResult(RESULT_OK, intent)
            finish()

        }
    }

    private fun setResultScreen(bmiValue: Float) {
        if (bmiValue < 18.5) {
            setExplanationText("Eat more pies!")
            setBackgroundColor(R.drawable.blue_color)
        } else if (bmiValue < 24.9 && bmiValue > 18.5
        ) {
            setExplanationText("Fit as a fiddle!")
            setBackgroundColor(R.drawable.green_color)
        } else {
            setExplanationText("Eat less pies!")
            setBackgroundColor(R.drawable.red_color)
        }
    }

    private fun setExplanationText(text: String) {
        binding.resultExplaination.text = text
    }

    private fun setBackgroundColor(@DrawableRes resId: Int) {
        binding.background.setBackgroundResource(resId)
    }
}