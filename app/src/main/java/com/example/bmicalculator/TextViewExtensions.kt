package com.example.bmicalculator

import android.widget.TextView

fun TextView.textToInt() = this.text.toString().toInt()

fun TextView.textToFloat() = this.text.toString().toFloat()