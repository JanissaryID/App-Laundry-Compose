package com.example.mylaundryapp.components.machine

import android.util.Log
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.*
import com.example.mylaundryapp.*
import com.example.mylaundryapp.api.machine.MachineModel
import com.example.mylaundryapp.components.view.ViewMachine

@Composable
fun ViewItemMachine(
    machine: MachineModel,
    selectedIndex: Int,
    index: Int,
    onItemClick:(Int) ->Unit
) {
    ViewMachine(
        machineModel = machine,
        color = MaterialTheme.colors.surface,
        index = if(selectedIndex != index) index else -1,
        selected = if(selectedIndex == index) false else true,
        onClick = onItemClick,
        usedMachine = machine.machineStatus!!
    )
}