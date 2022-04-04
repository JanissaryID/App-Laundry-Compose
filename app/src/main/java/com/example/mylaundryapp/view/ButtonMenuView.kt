package com.example.mylaundryapp.view

import android.util.Log
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.mylaundryapp.ON_CLICK_INDEX
import com.example.mylaundryapp.SELECTED_INDEX
import com.example.mylaundryapp.TEMP_SELECTED_INDEX
import com.example.mylaundryapp.components.ViewButtonMenu

@Composable
fun ButtonMenuView() {

    val selectionMenu = listOf("Giant", "Titan")

    LazyRow(
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.spacedBy(40.dp)
    ){

        itemsIndexed(items = selectionMenu){index, menu ->
            ViewButtonMenu(
                title = menu,
                index = if(SELECTED_INDEX != index){
                    index
                }  else -1,
                selected = if(SELECTED_INDEX == index) false else true,
                onClick = ON_CLICK_INDEX,
                color = MaterialTheme.colors.surface
            )
        }
    }
}