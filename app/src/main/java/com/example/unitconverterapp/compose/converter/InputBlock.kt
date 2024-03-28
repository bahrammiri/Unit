package com.example.unitconverterapp.compose.converter

import android.content.Context
import android.widget.Toast
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.OutlinedButton
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.onFocusChanged
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.platform.SoftwareKeyboardController
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardCapitalization
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.core.text.isDigitsOnly
import com.example.unitconverterapp.data.Conversion

@OptIn(ExperimentalComposeUiApi::class)
@Composable
fun InputBlock(
    conversion: Conversion,
    inputText: MutableState<String>,
    calculate: (String) -> Unit,
) {
    val context = LocalContext.current
    val keyboardController = LocalSoftwareKeyboardController.current

    Column(modifier = Modifier.padding(0.dp, 20.dp, 0.dp, 0.dp)) {
        Row(
            modifier = Modifier.fillMaxWidth(),
        ) {
            TextField(
                value = inputText.value,
                onValueChange = { inputText.value = it },
                textStyle = TextStyle(color = Color.DarkGray, fontSize = 30.sp),
                keyboardOptions = KeyboardOptions(
                    capitalization = KeyboardCapitalization.None,
                    keyboardType = KeyboardType.Number,
                    imeAction = ImeAction.Done
                ),
                keyboardActions = KeyboardActions(
                    onDone = {
                        hideKeyboardAndConvert(inputText, calculate, keyboardController, context)
                    }
                ),
                modifier = Modifier
                    .weight(0.65f)
                    .onFocusChanged { state ->
                        if (state.isFocused) {
                            keyboardController?.show()
                        }
                    }
            )
            Text(
                text = conversion.convertFrom,
                fontSize = 30.sp,
                modifier = Modifier
                    .padding(10.dp, 30.dp, 0.dp, 0.dp)
                    .weight(0.35f)
            )
        }

        Spacer(modifier = Modifier.height(20.dp))

        OutlinedButton(
            onClick = {
                hideKeyboardAndConvert(inputText, calculate, keyboardController, context)
            },
            modifier = Modifier.fillMaxWidth()
        ) {

            Text(
                text = "Convert",
                fontSize = 36.sp,
                fontWeight = FontWeight.Bold,
                color = Color.Blue
            )
        }
    }
}

@OptIn(ExperimentalComposeUiApi::class)
private fun hideKeyboardAndConvert(
    inputText: MutableState<String>,
    calculate: (String) -> Unit,
    keyboardController: SoftwareKeyboardController?,
    context: Context,
) {
    keyboardController?.hide()

    if (inputText.value.isDigitsOnly()) {
        calculate(inputText.value)
        inputText.value = ""
    } else {
        Toast.makeText(context, "Please enter a valid value", Toast.LENGTH_LONG).show()
        inputText.value = ""
    }
}


