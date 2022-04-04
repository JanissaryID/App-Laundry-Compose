package com.example.mylaundryapp.components.machine

import android.widget.Toast
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import com.example.mylaundryapp.api.machine.MachineModel
import com.example.mylaundryapp.components.column.ColumnMachine

@Composable
fun LoadDataMachine(
    machine: List<MachineModel>,
    machineState: Int,
    selectedIndex: Int,
    onItemClick: (Int) -> Unit
) {
    val context = LocalContext.current
//    Log.d("debug", "Get $GET_DATA_MACHINE_STAT")
    when (machineState) {
        0 -> {
//            Log.d("debug", "Loading")
            Box(
                modifier = Modifier.fillMaxSize(),
                contentAlignment = Alignment.Center
            ) {
                CircularProgressIndicator()
            }
        }
        1 -> {
//            Log.d("debug", "Success")
            if (!machine.isNullOrEmpty()){
                Box(
                    modifier = Modifier.fillMaxSize(),
                    contentAlignment = Alignment.Center
                ) {
                    ColumnMachine(machineModel = machine, selectedIndex = selectedIndex, onItemClick = onItemClick)
                }

            }
        }
        2 -> {
            Box(
                modifier = Modifier.fillMaxSize(),
                contentAlignment = Alignment.Center
            ) {
                Text(
                    text = "Can't load data",
                    fontWeight = FontWeight.Bold,
                    color = MaterialTheme.colors.primary,
                )
            }
//            Log.d("debug", "Error")
            Toast.makeText(context, "Can't load data", Toast.LENGTH_SHORT).show()
        }
    }
}