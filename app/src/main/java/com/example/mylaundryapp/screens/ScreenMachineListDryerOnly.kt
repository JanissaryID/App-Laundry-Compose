package com.example.mylaundryapp.screens

import android.os.Build
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Scaffold
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.navigation.NavController
import com.example.mylaundryapp.*
import com.example.mylaundryapp.api.machine.MachineViewModel
import com.example.mylaundryapp.api.transaction.TransactionViewModel
import com.example.mylaundryapp.components.ButtonView
import com.example.mylaundryapp.components.TopAppBarView
import com.example.mylaundryapp.components.machine.LoadDataMachine
import com.example.mylaundryapp.navigation.Screens

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun ScreenMachineListDryerOnly(
    navController: NavController,
    machineViewModel: MachineViewModel,
    transactionViewModel: TransactionViewModel
){
    MachineScaffoldDryerOnly(
        navController = navController,
        machineViewModel = machineViewModel,
        transactionViewModel = transactionViewModel
    )
}

@RequiresApi(Build.VERSION_CODES.O)
@OptIn(ExperimentalFoundationApi::class)
@Composable
fun MachineWallDryerOnly(
    navController: NavController,
    machineViewModel: MachineViewModel,
    transactionViewModel: TransactionViewModel
) {
//    CLICKED_BUTTON = false
    val context = LocalContext.current

//    var enabled by remember { mutableStateOf(false) }

    var selectedIndex by remember { mutableStateOf(-1) }
    val onItemClick = { index: Int -> selectedIndex = index}

    val stateMachine = machineViewModel.stateMachine
    val machine = machineViewModel.machineListResponse

    ConstraintLayout(modifier = Modifier
        .fillMaxSize()
        .padding(start = 16.dp, end = 16.dp)) {
        val (button_active, content) = createRefs()
        val modifier: Modifier = Modifier

        Box(modifier = modifier.constrainAs(content) {
            bottom.linkTo(button_active.top, 16.dp)
            start.linkTo(parent.start)
            end.linkTo(parent.end)
            top.linkTo(parent.top)
        }){
            LoadDataMachine(machine = machine,machineState = stateMachine, selectedIndex = selectedIndex, onItemClick = onItemClick)
        }

        ButtonView(title = "Active Machine", modifier.constrainAs(button_active) {
            bottom.linkTo(parent.bottom, 16.dp)
            start.linkTo(parent.start)
            end.linkTo(parent.end)
        }, ENABLE_BUTTON){
            machineViewModel.updateMachine(idMachine = MACHINE_ID)
            transactionViewModel.updateTransaction(
                idTransaction = DRYER_INDEX_TRANSACTION,
                navController = navController
            )
        }
    }
}

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun MachineScaffoldDryerOnly(
    navController: NavController,
    machineViewModel: MachineViewModel,
    transactionViewModel: TransactionViewModel
) {
    val context = LocalContext.current
    Scaffold(
        topBar = {
            TopAppBarView(navController = navController, title = "Machine", screenBack = Screens.Home.route)
        },
        backgroundColor = MaterialTheme.colors.onPrimary
    ){
        MachineWallDryerOnly(
            navController = navController,
            machineViewModel = machineViewModel,
            transactionViewModel = transactionViewModel
        )
    }
}