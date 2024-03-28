package com.example.unitconverterapp.compose.result

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Card
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.State
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.unitconverterapp.data.ConversionResult

@Composable
fun ResultBlock(
    list: State<List<ConversionResult>>,
) {

    val resultList = list.value

    if (resultList.isNotEmpty()) {
        Card(
            elevation = 20.dp,
            modifier = Modifier.padding(0.dp, 20.dp, 0.dp, 0.dp)
        ) {
            Column(modifier = Modifier.padding(10.dp)) {
                val lastItem = resultList.last()
                Text(
                    text = lastItem.messagePart1,
                    fontSize = 28.sp
                )

                Text(
                    text = lastItem.messagePart2,
                    fontSize = 28.sp,
                    color = Color.Blue,
                    fontWeight = FontWeight.Bold
                )
            }
        }
    }
}

