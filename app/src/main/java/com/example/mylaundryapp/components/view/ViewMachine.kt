package com.example.mylaundryapp.components.view

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.mylaundryapp.MACHINE_ID
import com.example.mylaundryapp.MACHINE_NUMBER
import com.example.mylaundryapp.api.machine.MachineModel

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun ViewMachine(
    machineModel: MachineModel,
    usedMachine: Boolean,
    color: Color = MaterialTheme.colors.surface,
    index: Int,
    selected: Boolean,
    onClick: (Int) -> Unit) {
    val context = LocalContext.current

    Card(
        modifier = Modifier.padding(top = 10.dp, bottom = 10.dp, start = 4.dp, end = 4.dp),
        shape = RoundedCornerShape(25),
        backgroundColor = Color.Transparent,
        elevation = 0.dp,
        border = if (usedMachine) BorderStroke(6.dp, MaterialTheme.colors.secondary) else BorderStroke(6.dp, MaterialTheme.colors.primary)
    ) {
        Surface(
            modifier = Modifier
                .padding(7.dp)
                .clickable {
                    if(!usedMachine){
                        MACHINE_NUMBER = machineModel.machineNumber!!
                        MACHINE_ID = machineModel.id!!
                        onClick.invoke(index)
                    }
                },
            color = if (usedMachine){ MaterialTheme.colors.secondary }
            else{ if (selected) MaterialTheme.colors.primary else color },
            shape = RoundedCornerShape(22),
            contentColor = if (selected) color else MaterialTheme.colors.primary,
        ){
            Text(
                text = machineModel.machineNumber!!.toString(),
                fontSize = 42.sp,
                textAlign = TextAlign.Center,
                modifier = Modifier.padding(24.dp)
            )
        }
    }
}