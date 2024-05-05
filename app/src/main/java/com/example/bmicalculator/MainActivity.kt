package com.example.bmicalculator

import android.content.Intent
import android.os.Bundle
import android.widget.SeekBar
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.bmicalculator.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private var minValue = 0.0f
    private var stepSize = 1.0f
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        setHeightSeekbarListener()
        setWeightSeekbarListener()
        setCalculateButtonClickListener()

    }

    private fun setCalculateButtonClickListener() {
        binding.buttonCalculate.setOnClickListener {
            binding.run {
                var bmiValue =
                    textToFloat(weightText) / (textToFloat(heightText) * textToFloat(heightText))
                bmiValue = (bmiValue * 100).toInt() / 100f
                val intent = Intent(this@MainActivity, ResultActivity::class.java)
                intent.putExtra(ResultActivity.BMI_KEY, bmiValue)
                startActivity(intent)
            }
        }
    }

    private fun setWeightSeekbarListener() {
        binding.seekBarWeight.setOnSeekBarChangeListener(object : SeekbarProgressChangeListener {
            override fun onProgressChanged(seekBar: SeekBar?, progress: Int, fromUser: Boolean) {
                binding.weightText.text = "${progress / 10.0}"
            }
        })
    }

    private fun setHeightSeekbarListener() {
        binding.seekBarHeight.setOnSeekBarChangeListener(object : SeekbarProgressChangeListener {
            override fun onProgressChanged(seekBar: SeekBar?, progress: Int, fromUser: Boolean) {
                binding.heightText.text = "${progress / 100.0}"
            }

        })
    }

//    private fun textToInt(textView: TextView): Int {
//        return textView.text.toString().toInt()
//    }

    private fun textToFloat(textView: TextView) = textView.text.toString().toFloat()


    //Extension Function
//    private fun TextView.textToInt() = text.toString().toInt()
    // private fun TextView.textToInt() = this.text.toString().toInt()

}

interface SeekbarProgressChangeListener : SeekBar.OnSeekBarChangeListener {

    override fun onStartTrackingTouch(seekBar: SeekBar?) {
    }

    override fun onStopTrackingTouch(seekBar: SeekBar?) {
    }

}