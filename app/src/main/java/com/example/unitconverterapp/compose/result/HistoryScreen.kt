package com.example.unitconverterapp.compose.result

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.OutlinedButton
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.State
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.example.unitconverterapp.data.ConversionResult

@Composable
fun HistoryScreen(
    list: State<List<ConversionResult>>,
    onCloseTask: (ConversionResult) -> Unit,
    onClearAllTask: () -> Unit,
) {

    val listSize = list.value.size
    var prevListSize by remember { mutableStateOf(listSize) }

    var showResultBlock by remember { mutableStateOf(true) }

    if (listSize > prevListSize) {

        showResultBlock = true
    } else if (listSize < prevListSize) {

        showResultBlock = false
    }
    prevListSize = listSize

    Column {
        if (showResultBlock) {
            ResultBlock(list)
        }

        if (list.value.isNotEmpty()) {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(bottom = 10.dp),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Text(
                    text = "History",
                    color = Color.Gray
                )
                OutlinedButton(onClick = { onClearAllTask() }) {
                    Text(
                        text = "Clear All",
                        color = Color.Gray
                    )
                }
            }
        }

        HistoryList(
            list = list,
            onCloseTask = { item ->
                onCloseTask(item)
            }
        )
    }
}
