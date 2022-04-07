package com.example.mylaundryapp.components.column

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.lazy.GridCells
import androidx.compose.foundation.lazy.LazyVerticalGrid
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.unit.dp
import com.example.mylaundryapp.ENABLE_BUTTON
import com.example.mylaundryapp.api.machine.MachineModel
import com.example.mylaundryapp.components.view.ViewMachine

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun ColumnMachine(machineModel: List<MachineModel>, selectedIndex: Int, onItemClick: (Int) -> Unit){
    LazyVerticalGrid(
        cells = GridCells.Fixed(3),
        contentPadding = PaddingValues(0.dp),
    ) {
        itemsIndexed(items = machineModel) { index, machine ->
            ViewMachine(
                machineModel = machine,
                color = MaterialTheme.colors.surface,
                index = if(selectedIndex != index) index else -1,
                selected = if(selectedIndex == index) false else true,
                onClick = onItemClick,
                usedMachine = machine.machineStatus!!
            )
            if(selectedIndex != -1) ENABLE_BUTTON = true else ENABLE_BUTTON = false
        }
    }
}