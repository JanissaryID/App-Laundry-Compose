package com.example.mylaundryapp.screens

import android.os.Build
import androidx.activity.compose.BackHandler
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
fun ScreenMachineList(
    navController: NavController,
    machineViewModel: MachineViewModel,
    transactionViewModel: TransactionViewModel
){
    MachineScaffold(
        navController = navController,
        machineViewModel = machineViewModel,
        transactionViewModel = transactionViewModel
    )
}

@RequiresApi(Build.VERSION_CODES.O)
@OptIn(ExperimentalFoundationApi::class)
@Composable
fun MachineWall(
    navController: NavController,
    machineViewModel: MachineViewModel,
    transactionViewModel: TransactionViewModel
) {
    val context = LocalContext.current

    var selectedIndex by remember { mutableStateOf(-1) }
    val onItemClick = { index: Int -> selectedIndex = index}

    val stateMachine = machineViewModel.stateMachine
    val machine = machineViewModel.machineListResponse

    ConstraintLayout(modifier = Modifier
        .fillMaxSize()
        .padding(start = 16.dp, end = 16.dp)) {
        val (button_Qris, button_Cash, content) = createRefs()
        val modifier: Modifier = Modifier

        Box(modifier = modifier.constrainAs(content) {
            bottom.linkTo(button_Cash.top, 16.dp)
            start.linkTo(parent.start)
            end.linkTo(parent.end)
            top.linkTo(parent.top)
        }){
            LoadDataMachine(machine = machine,machineState = stateMachine, selectedIndex = selectedIndex, onItemClick = onItemClick)
        }

        ButtonView(title = "Pay with Cash", modifier.constrainAs(button_Cash) {
            bottom.linkTo(button_Qris.top, 16.dp)
            start.linkTo(parent.start)
            end.linkTo(parent.end)
        }, ENABLE_BUTTON){
//            Toast.makeText(context, "Price ${PRICE_SERVICE_LAUNDRY}", Toast.LENGTH_SHORT).show()
            machineViewModel.updateMachine(idMachine = MACHINE_ID, isPacket = PRICE_VALUE[0].isPacket!!)
            transactionViewModel.insertTransaction(
                classmachine = if(INDEX_CLASS_MACHINE == 0) false else true,
                idmachine = MACHINE_ID,
                price = PRICE_VALUE[0].price!!,
                typetransaction = MENU_VALUE,
                typePaymentTransaction = false,
                navController = navController,
                transactionMenuMachine = MENU_VALUE_MACHINE,
                numbermachine = MACHINE_NUMBER
            )
        }

        ButtonView(title = "Pay with Qris", modifier.constrainAs(button_Qris) {
            bottom.linkTo(parent.bottom, 16.dp)
            start.linkTo(parent.start)
            end.linkTo(parent.end)
        }, ENABLE_BUTTON){
            PAYMENT_SUCCESS = false
//            Toast.makeText(context, "Machine Number $MACHINE_NUMBER price $price", Toast.LENGTH_SHORT).show()
            navController.navigate(route = Screens.Qris.route)
        }
    }
}

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun MachineScaffold(
    navController: NavController,
    machineViewModel: MachineViewModel,
    transactionViewModel: TransactionViewModel
) {
    val context = LocalContext.current
    Scaffold(
        topBar = {
//            BackHandler(enabled = true) {
//
//            }
            TopAppBarView(navController = navController, title = "Machine", screenBack = Screens.Home.route)
        },
        backgroundColor = MaterialTheme.colors.onPrimary
    ){
        MachineWall(
            navController = navController,
            machineViewModel = machineViewModel,
            transactionViewModel = transactionViewModel
        )
    }
}