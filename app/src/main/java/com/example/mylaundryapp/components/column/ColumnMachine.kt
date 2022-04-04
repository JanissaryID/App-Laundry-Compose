package com.example.mylaundryapp.components.column

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.lazy.GridCells
import androidx.compose.foundation.lazy.LazyVerticalGrid
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.runtime.Composable
import androidx.compose.ui.unit.dp
import com.example.mylaundryapp.api.machine.MachineModel
import com.example.mylaundryapp.components.machine.ViewItemMachine

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun ColumnMachine(machineModel: List<MachineModel>, selectedIndex: Int, onItemClick: (Int) -> Unit){
    LazyVerticalGrid(
        cells = GridCells.Fixed(3),
        contentPadding = PaddingValues(0.dp),
    ) {
        itemsIndexed(items = machineModel) { index, machine ->
            ViewItemMachine(
                machine = machine,
                selectedIndex = selectedIndex,
                index = index,
                onItemClick = onItemClick
            )
        }
    }
}