package com.example.bmicalculator

import android.content.Intent
import android.os.Bundle
import android.widget.SeekBar
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.bmicalculator.databinding.ActivityMainBinding
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private var BMIRecordList: ArrayList<BMIRecord> = arrayListOf()
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
        openHistoryPage()


    }

    val activityLauncher: ActivityResultLauncher<Intent> = registerForActivityResult(
        ActivityResultContracts.StartActivityForResult(), { activityResult ->
            val resultCode = activityResult.resultCode
            if (resultCode == RESULT_OK) {
                activityResult.data?.getStringExtra("result")?.let {
                    val newRecord = BMIRecordList.last().copy(result = it)
                    BMIRecordList.removeLast()
                    BMIRecordList.add(newRecord)
                }
            }
        }
    )


    private fun setCalculateButtonClickListener() {
        binding.buttonCalculate.setOnClickListener {
            binding.run {
                var bmiValue =
                    weightText.textToFloat() / (textToFloat(heightText) * textToFloat(heightText))
                bmiValue = (bmiValue * 100).toInt() / 100f
                val intent = Intent(this@MainActivity, ResultActivity::class.java)
                intent.putExtra(ResultActivity.BMI_KEY, bmiValue)
                activityLauncher.launch(intent)

                BMIRecordList.add(
                    BMIRecord(
                        "${getCurrentDate()}",
                        weightText.textToDouble(),
                        heightText.textToDouble(),
                        ""
                    )
                )

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

    private fun openHistoryPage() {
        binding.historyButton.setOnClickListener {
            val intent = Intent(this@MainActivity, HistoryActivity::class.java)
            intent.putExtra("history", BMIRecordList)
            startActivity(intent)
        }
    }

//    private fun textToInt(textView: TextView): Int {
//        return textView.text.toString().toInt()
//    }

    private fun textToFloat(textView: TextView) = textView.text.toString().toFloat()


    //Extension Function
//    private fun TextView.textToInt() = text.toString().toInt()
    // private fun TextView.textToInt() = this.text.toString().toInt()

}

fun getCurrentDate(): String {
    val dateFormat = SimpleDateFormat("dd/MM/yyyy", Locale.getDefault())
    return dateFormat.format(Date())
}

interface SeekbarProgressChangeListener : SeekBar.OnSeekBarChangeListener {

    override fun onStartTrackingTouch(seekBar: SeekBar?) {
    }

    override fun onStopTrackingTouch(seekBar: SeekBar?) {
    }

}