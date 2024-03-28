package com.example.unitconverterapp.compose.converter

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.width
import androidx.compose.material.DropdownMenu
import androidx.compose.material.DropdownMenuItem
import androidx.compose.material.Icon
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.material.icons.filled.KeyboardArrowUp
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.onSizeChanged
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import com.example.unitconverterapp.data.Conversion

@Composable
fun ConversionMenu(
    list: List<Conversion>,
    convert: (Conversion) -> Unit,
) {
    var expanded by remember { mutableStateOf(false) }
    var selectedConversion by remember { mutableStateOf("Please select a conversion") }
    var textFieldWidth by remember { mutableStateOf(0) }

    val icon = if (expanded) {
        Icons.Filled.KeyboardArrowUp
    } else {
        Icons.Filled.KeyboardArrowDown
    }

    Column() {
        OutlinedTextField(
            value = selectedConversion,
            onValueChange = { selectedConversion = it },
            label = { Text(text = "Conversion type") },

            modifier = Modifier
                .fillMaxWidth()
                .onSizeChanged {
                    textFieldWidth = it.width
                },

            trailingIcon = {
                Icon(icon, contentDescription = "icon", Modifier.clickable { expanded = !expanded })
            },
            readOnly = true
        )

        DropdownMenu(
            expanded = expanded,
            onDismissRequest = { expanded = false },
            modifier = Modifier.width(LocalDensity.current.run {
                textFieldWidth.toDp()
            })
        ) {
            list.forEach { conversion ->
                DropdownMenuItem(
                    onClick = {
                        selectedConversion = conversion.description
                        expanded = false
                        convert(conversion) //لامیدا زا اجرا می کنیم  پاس میدهیم
                    }
                ) {
                    Text(
                        text = conversion.description,
                        fontSize = 24.sp,
                        fontWeight = FontWeight.Bold
                    )
                }
            }
        }


    }
}

