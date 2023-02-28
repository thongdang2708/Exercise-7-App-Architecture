package com.example.e

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.e.ui.theme.ETheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ETheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background
                ) {
                    Bmi()
                }
            }
        }
    }
}

@Composable
fun Bmi (bmiViewModel: BMIViewModel = viewModel()) {

    var heightInput = remember {
        mutableStateOf("")
    }

    var weightInput = remember {
         mutableStateOf("")
    }



    val validHeightInputState = remember (bmiViewModel.heightInput.value) {
        bmiViewModel.heightInput.value.trim().isNotEmpty()
    }

    val validWeightInputState = remember (bmiViewModel.weightInput.value){
        bmiViewModel.weightInput.value.trim().isNotEmpty()
    }

    val focusManager = LocalFocusManager.current;

    Column(
        modifier = Modifier.padding(8.dp),
        verticalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        Text(
            text = stringResource(R.string.BMI),
            color = MaterialTheme.colors.primary,
            textAlign = TextAlign.Center,
            fontSize = 24.sp,
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 16.dp, bottom = 16.dp)
        )
        OutlinedTextField(value = bmiViewModel.heightInput.value, onValueChange = { bmiViewModel.setHeightInput(it.replace(",", "."))}, singleLine = true, label = {Text(
            text = stringResource(R.string.Height1)
        )}, keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number), modifier = Modifier.fillMaxWidth(), keyboardActions = KeyboardActions(onDone = {
            if (!validHeightInputState) return@KeyboardActions
            focusManager.clearFocus()
        }))
        OutlinedTextField(value = bmiViewModel.weightInput.value, onValueChange = { bmiViewModel.setWeightInput(it.replace(",", "."))}, singleLine = true, label = {Text(
           text = stringResource(R.string.weight)
        )}, keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number), modifier = Modifier.fillMaxWidth(), keyboardActions = KeyboardActions {
            if (!validWeightInputState) return@KeyboardActions
            focusManager.clearFocus()
        })
        Text(text = stringResource(R.string.result, String.format("%.2f", bmiViewModel.calculateBMI()).replace(",", ".")), style = TextStyle(color = MaterialTheme.colors.primary, fontSize = 20.sp))
}}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    ETheme {
        Bmi()
    }
}