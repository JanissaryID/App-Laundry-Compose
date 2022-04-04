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
    var class_giant = 0
    var class_titan = 1
    var menu_value = "Dryer"

    if(MENU_VALUE != menu_value){
        if (INDEX_CLASS_MACHINE == class_giant){
            if(!machine.machineType!! && !machine.machineClass!!){
                ViewMachine(
                    machineModel = machine,
                    color = MaterialTheme.colors.surface,
                    index = if(selectedIndex != index) index else -1,
                    selected = if(selectedIndex == index) false else true,
                    onClick = onItemClick,
                    usedMachine = machine.machineStatus!!
                )
                if(selectedIndex != -1)ENABLE_BUTTON = true else ENABLE_BUTTON = false
            }
        }
        else{
            if(!machine.machineType!! && machine.machineClass!!){
                ViewMachine(
                    machineModel = machine,
                    color = MaterialTheme.colors.surface,
                    index = if(selectedIndex != index) index else -1,
                    selected = if(selectedIndex == index) false else true,
                    onClick = onItemClick,
                    usedMachine = machine.machineStatus!!
                )
                if(selectedIndex != -1)ENABLE_BUTTON = true else ENABLE_BUTTON = false
            }
        }
    }
    else{
        if (INDEX_CLASS_MACHINE == class_titan){
            if(machine.machineType!! && machine.machineClass!!){
                ViewMachine(
                    machineModel = machine,
                    color = MaterialTheme.colors.surface,
                    index = if(selectedIndex != index) index else -1,
                    selected = if(selectedIndex == index) false else true,
                    onClick = onItemClick,
                    usedMachine = machine.machineStatus!!
                )
                if(selectedIndex != -1)ENABLE_BUTTON = true else ENABLE_BUTTON = false
            }
        }
        else{
            if(machine.machineType!! && !machine.machineClass!!){
                ViewMachine(
                    machineModel = machine,
                    color = MaterialTheme.colors.surface,
                    index = if(selectedIndex != index) index else -1,
                    selected = if(selectedIndex == index) false else true,
                    onClick = onItemClick,
                    usedMachine = machine.machineStatus!!
                )
                if(selectedIndex != -1)ENABLE_BUTTON = true else ENABLE_BUTTON = false
            }
        }
    }
}