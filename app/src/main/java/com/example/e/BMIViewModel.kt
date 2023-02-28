package com.example.e

import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel

class BMIViewModel: ViewModel() {

    var heightInput = mutableStateOf("")
    var weightInput = mutableStateOf("")

    fun setHeightInput (value : String) {
        heightInput.value = value
    }

    fun setWeightInput (value : String) {
        weightInput.value = value
    }

    private var height : Float = 0.0f
    get() {
        return heightInput.value.toFloatOrNull() ?: 0.0f;
    }

    private var weight : Int = 0
    get() {
        return weightInput.value.toIntOrNull() ?: 0;
    }

    fun calculateBMI () : Float {

        return if (weight > 0 && height > 0) weight / (height * height) else 0.0f;
    }





}