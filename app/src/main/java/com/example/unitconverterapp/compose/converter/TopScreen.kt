package com.example.unitconverterapp.compose.converter

import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import com.example.unitconverterapp.data.Conversion
import java.math.RoundingMode
import java.text.DecimalFormat

@Composable
fun TopScreen(
    list: List<Conversion>,
    save: (String, String) -> Unit,
) {
    val selectedConversion: MutableState<Conversion?> = remember { mutableStateOf(null) }
    val typedValue: MutableState<String> = remember { mutableStateOf("0.0") }
    val inputText: MutableState<String> = remember { mutableStateOf("") }

    ConversionMenu(list = list) {
        selectedConversion.value = it  // selectedConversion را به روز کردیم
        typedValue.value = "0.0"
    }

    selectedConversion.value?.let {
        InputBlock(conversion = it, inputText = inputText) { input ->
            typedValue.value = input
        }
    }

    if (typedValue.value != "0.0") {
        val input = typedValue.value.toDouble()
        val multiply = selectedConversion.value!!.multiplyBy
        val result = input * multiply

        val df = DecimalFormat("#.##")
        df.roundingMode = RoundingMode.DOWN
        val roundedResult = df.format(result)

        val message1 = "${typedValue.value} ${selectedConversion.value!!.convertFrom} is equal to"
        val message2 = "$roundedResult ${selectedConversion.value!!.convertTo}"
        save(message1, message2)
    }
}
