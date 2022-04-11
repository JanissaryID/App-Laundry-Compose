package com.example.mylaundryapp.components

import android.os.Build
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.runtime.*
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.mylaundryapp.*
import com.example.mylaundryapp.R
import com.example.mylaundryapp.components.dialog.DialogCalendar
import com.example.mylaundryapp.excel.ExcelViewModel
import com.example.mylaundryapp.navigation.Screens

@Composable
fun TopAppBarView(
    navController: NavController,
    title: String,
    screenBack: String
) {
    val context = LocalContext.current

    TopAppBar(
        title = {
            Text(
                text = title,
                fontWeight = FontWeight.Bold,
                color = MaterialTheme.colors.primary,
            )
        },
        backgroundColor = MaterialTheme.colors.onPrimary,
        elevation = 0.dp,
        navigationIcon = {
            IconButton(
                onClick = {
                    navController.navigate(route = screenBack) {
                        if (screenBack == "machine_screen"){
                            PAYMENT_SUCCESS = true
                        }
                        popUpTo(screenBack) {
                            inclusive = true
                        }
                    }
//                        Toast.makeText(context, "Screen $screenBack", Toast.LENGTH_SHORT).show()
                }) {
                Icon(
                    imageVector = Icons.Default.ArrowBack,
                    contentDescription = "Menu",
                    tint = MaterialTheme.colors.primary
                )
            }
        }
    )
}

@Composable
fun TopAppBarViewHome(
    navController: NavController,
    title: String,
) {
    val context = LocalContext.current

    TopAppBar(
        title = {
            Text(
                text = title,
                fontWeight = FontWeight.Bold,
                color = MaterialTheme.colors.primary,
            )
        },
        backgroundColor = MaterialTheme.colors.onPrimary,
        elevation = 0.dp,
        actions = {
            IconButton(
                onClick = {
                    navController.navigate(route = Screens.Setting.route)
//                    Toast.makeText(context, "Date", Toast.LENGTH_SHORT).show()
                }) {
                Icon(imageVector = Icons.Default.MoreVert, contentDescription = "Menu", tint = MaterialTheme.colors.primary)
            }
        }
    )
}

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun TopAppBarViewTransactionList(
    navController: NavController,
    title: String,
    screenBack: String,
    excelViewModel: ExcelViewModel
) {
    val context = LocalContext.current

    TopAppBar(
        title = {
            Text(
                text = title,
                fontWeight = FontWeight.Bold,
                color = MaterialTheme.colors.primary,
            )
        },
        backgroundColor = MaterialTheme.colors.onPrimary,
        elevation = 0.dp,
        navigationIcon = {
            IconButton(
                onClick = {
                    navController.navigate(route = screenBack) {
                        if (screenBack == "machine_screen"){
                            PAYMENT_SUCCESS = true
                        }
                        popUpTo(screenBack) {
                            inclusive = true
                        }
                    }
//                        Toast.makeText(context, "Screen $screenBack", Toast.LENGTH_SHORT).show()
                }) {
                Icon(
                    imageVector = Icons.Default.ArrowBack,
                    contentDescription = "Menu",
                    tint = MaterialTheme.colors.primary
                )
            }
        },
        actions = {
            IconButton(
                onClick = {
                    IS_DIALOG_OPEN.value = true
//                    navController.navigate(route = Screens.Setting.route)
//                    Toast.makeText(context, "Calendar", Toast.LENGTH_SHORT).show()
                }) {
                Icon(painter = painterResource(id = R.drawable.ic_baseline_calendar_today_24), contentDescription = "Calendar", tint = MaterialTheme.colors.primary)
            }
            IconButton(
                onClick = {
//                    navController.navigate(route = Screens.Setting.route)
//                    Log.d("debug", "Data : $EXCEL_VALUE")
//                    Toast.makeText(context, "Export", Toast.LENGTH_SHORT).show()
                    excelViewModel.createExcelSheet(DATE_PICK)
                    if (excelViewModel.stateExcel == 1){
                        Toast.makeText(context, "Success Export", Toast.LENGTH_SHORT).show()
                    }
                }) {
                Icon(painter = painterResource(id = R.drawable.ic_baseline_input_24), contentDescription = "Export", tint = MaterialTheme.colors.primary)
            }
        }
    )
}