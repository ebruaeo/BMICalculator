package com.example.bmicalculator

import java.io.Serializable

class BMIRecord(val date: String, val weight: Double, val height: Double, val result: String) : Serializable {
}