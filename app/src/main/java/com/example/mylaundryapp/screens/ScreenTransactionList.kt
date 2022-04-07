package com.example.mylaundryapp.screens

import android.os.Build
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.rememberScrollState
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Scaffold
import androidx.compose.material.Surface
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.navigation.NavController
import com.example.mylaundryapp.api.transaction.TransactionViewModel
import com.example.mylaundryapp.components.ButtonView
import com.example.mylaundryapp.components.TopAppBarView
import com.example.mylaundryapp.components.transaction.LoadDataTransaction
import com.example.mylaundryapp.navigation.Screens
import androidx.compose.material.Surface
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.compose.rememberNavController
import com.example.mylaundryapp.*
import com.example.mylaundryapp.components.TopAppBarViewTransactionList
import com.example.mylaundryapp.components.dialog.DialogCalendar
import com.example.mylaundryapp.excel.ExcelViewModel
import com.example.mylaundryapp.ui.theme.MyLaundryAppTheme

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun ScreenTransactionList(
    navController: NavController,
    transactionViewModel: TransactionViewModel,
    excelViewModel: ExcelViewModel
){
    TransactionScaffoldList(
        navController = navController,
        transactionViewModel = transactionViewModel,
        excelViewModel = excelViewModel
    )
}

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun WallTransactionList(
    navController: NavController,
    transactionViewModel: TransactionViewModel,
    excelViewModel: ExcelViewModel
) {
    val context = LocalContext.current

    val scrollState = rememberScrollState()

    var selectedIndex by remember { mutableStateOf(-1) }
    val onItemClick = { index: Int -> selectedIndex = index}

    val stateTransaction = transactionViewModel.stateTransaction
    val transaction = transactionViewModel.transactionListResponse
    Box(modifier = Modifier
        .wrapContentHeight()
        .padding(start = 16.dp, end = 16.dp)
    ){
        LoadDataTransaction(
            transaction = transaction,
            transactionState = stateTransaction,
            selectedIndex = selectedIndex,
            onItemClick = onItemClick
        )
    }
    if (IS_DIALOG_OPEN.value){
        DialogCalendar()
    }
}

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun TransactionScaffoldList(
    navController: NavController,
    transactionViewModel: TransactionViewModel,
    excelViewModel: ExcelViewModel
) {
    val context = LocalContext.current
    Scaffold(
        topBar = { TopAppBarViewTransactionList(
            navController = navController,
            title = TITLE_SCREEN[3],
            screenBack = Screens.Home.route,
            excelViewModel = excelViewModel
        ) },
        backgroundColor = MaterialTheme.colors.onPrimary
    ){
        WallTransactionList(navController = navController, transactionViewModel = transactionViewModel, excelViewModel = excelViewModel)
    }
}

@RequiresApi(Build.VERSION_CODES.O)
@Preview(showBackground = true)
@Composable
fun SettingPreview() {
    MyLaundryAppTheme {
        ScreenTransactionList(navController = rememberNavController(), transactionViewModel = TransactionViewModel(), excelViewModel = ExcelViewModel())
    }
}