package com.example.mylaundryapp.components

import android.util.Log
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.mylaundryapp.*

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun ViewButtonMenu(
    title: String,
    color: Color,
    index: Int,
    selected: Boolean,
    onClick: (Int) -> Unit
) {
//    TEMP_SELECTED_INDEX = index
    val context = LocalContext.current
    TEMP_SELECTED_INDEX = INDEX_CLASS_MACHINE
    Card(
        modifier = Modifier.padding(top = 16.dp, bottom = 16.dp, start = 4.dp, end = 4.dp),
        shape = RoundedCornerShape(40),
        backgroundColor = Color.Transparent,
        elevation = 0.dp,
    ) {
        Surface(
            modifier = Modifier
                .height(48.dp)
                .fillMaxWidth()
                .clickable {
                    if (!MENU_VALUE.isNullOrBlank()){
                        onClick.invoke(index)
                        INDEX_CLASS_MACHINE = index
                        if(SELECTED_INDEX != TEMP_SELECTED_INDEX){
                            MENU_VALUE_MACHINE = ""
                            TITLE_MENU_MACHINE.clear()
//                            SELECTED_MENU_MACHINE_VALUE = ""
                        }
                    }
                },
            color = if (!selected) MaterialTheme.colors.primary else color,
            shape = RoundedCornerShape(40),
            contentColor = if (!selected) color else MaterialTheme.colors.primary,
        ){
            Text(
                text = title,
                fontWeight = FontWeight.SemiBold,
                fontSize = 18.sp,
                textAlign = TextAlign.Center,
                modifier = Modifier.wrapContentHeight().padding(
                    start = 24.dp,
                    end = 24.dp
                )
            )
        }
    }
}