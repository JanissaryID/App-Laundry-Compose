package com.example.mylaundryapp.components.dialog

import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import com.example.mylaundryapp.*
import com.example.mylaundryapp.components.ButtonView
import com.example.mylaundryapp.navigation.Screens
import io.github.boguszpawlowski.composecalendar.SelectableCalendar
import io.github.boguszpawlowski.composecalendar.StaticCalendar
import io.github.boguszpawlowski.composecalendar.day.DayState
import io.github.boguszpawlowski.composecalendar.rememberSelectableCalendarState

@Composable
fun DialogCalendar() {

    val calendarState = rememberSelectableCalendarState()

    val context = LocalContext.current

    Dialog(onDismissRequest = { IS_DIALOG_OPEN.value = true }) {
        Card(modifier = Modifier
            .wrapContentHeight(),
            shape = RoundedCornerShape(10),
            elevation = 0.dp,
            backgroundColor = MaterialTheme.colors.surface
        ) {
            Column(modifier = Modifier.padding(16.dp)) {
                SelectableCalendar(
                    calendarState = calendarState
                )
                var getDate = calendarState.selectionState.selection.joinToString { it.toString() }
                var picDate = getDate.split('-')
                if(picDate.size == 3){
                    DATE_PICK = "${picDate[2]}-${picDate[1]}-${picDate[0]}"
                }
                ButtonView(title = "Pick Date", enable = true
                ){
                    IS_DIALOG_OPEN.value = false
                    Toast.makeText(context, "$DATE_PICK", Toast.LENGTH_SHORT).show()
                }
            }
        }
    }

}