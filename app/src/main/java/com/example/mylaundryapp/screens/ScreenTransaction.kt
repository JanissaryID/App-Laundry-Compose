package com.example.mylaundryapp.screens

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Scaffold
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.navigation.NavController
import com.example.mylaundryapp.TEXT_FIELD
import com.example.mylaundryapp.TITLE_SCREEN
import com.example.mylaundryapp.VALUE_SETTING
import com.example.mylaundryapp.api.transaction.TransactionViewModel
import com.example.mylaundryapp.components.TopAppBarView
import com.example.mylaundryapp.components.machine.LoadDataMachine
import com.example.mylaundryapp.components.transaction.LoadDataTransaction
import com.example.mylaundryapp.components.view.ViewSetting
import com.example.mylaundryapp.navigation.Screens
import com.example.mylaundryapp.room.setting.SettingItem
import com.example.mylaundryapp.room.setting.SettingViewModel

@Composable
fun ScreenTransaction(navController: NavController, transactionViewModel: TransactionViewModel){
    TransactionScaffold(navController = navController, transactionViewModel = transactionViewModel)
}

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun WallTransaction(navController: NavController, transactionViewModel: TransactionViewModel) {
    val context = LocalContext.current

    val scrollState = rememberScrollState()

    var selectedIndex by remember { mutableStateOf(-1) }
    val onItemClick = { index: Int -> selectedIndex = index}

    val stateTransaction = transactionViewModel.stateTransaction
    val transaction = transactionViewModel.transactionListResponse

    Box(modifier = Modifier.padding(start = 16.dp, end = 16.dp)){
        LoadDataTransaction(
            transaction = transaction,
            transactionState = stateTransaction,
            selectedIndex = selectedIndex,
            onItemClick = onItemClick
        )
    }
}

@Composable
fun TransactionScaffold(navController: NavController, transactionViewModel: TransactionViewModel) {
    val context = LocalContext.current
    Scaffold(
        topBar = { TopAppBarView(
            navController = navController,
            title = TITLE_SCREEN[2],
            screenBack = Screens.Home.route
        ) },
        backgroundColor = MaterialTheme.colors.onPrimary
    ){
        WallTransaction(navController = navController, transactionViewModel = transactionViewModel)
    }
}