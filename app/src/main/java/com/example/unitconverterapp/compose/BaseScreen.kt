package com.example.unitconverterapp.compose

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.unitconverterapp.ConverterViewModel
import com.example.unitconverterapp.compose.converter.TopScreen
import com.example.unitconverterapp.compose.result.HistoryScreen

@Composable
fun BaseScreen(
//    factory: ConverterViewModelFactory,
//    converterViewModel: ConverterViewModel = viewModel(factory = factory),
    converterViewModel : ConverterViewModel
) {
    val list = converterViewModel.getConversions()
    val historyList = converterViewModel.historyList.collectAsState(initial = emptyList())

    Column(modifier = Modifier.padding(30.dp)) {

        TopScreen(list) { message1, message2 ->
            converterViewModel.addResult(message1, message2)
        }

        Spacer(modifier = Modifier.height(20.dp))

        HistoryScreen(
            historyList,
            { item ->
                converterViewModel.removeResult(item)
            },
            {
                converterViewModel.clearAll()
            }
        )
    }

}