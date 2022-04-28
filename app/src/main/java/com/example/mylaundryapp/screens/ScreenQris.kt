package com.example.mylaundryapp.screens

import android.os.Build
import android.util.Log
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.navigation.NavController
import com.example.mylaundryapp.*
import com.example.mylaundryapp.api.machine.MachineViewModel
import com.example.mylaundryapp.api.payment.PaymentQrisViewModel
import com.example.mylaundryapp.api.transaction.TransactionViewModel
import com.example.mylaundryapp.components.ButtonView
import com.example.mylaundryapp.components.TopAppBarView
import com.example.mylaundryapp.components.payment.LoadDataPayment
import com.example.mylaundryapp.navigation.Screens

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun ScreenQris(
    navController: NavController,
    paymentViewModel: PaymentQrisViewModel,
    machineViewModel: MachineViewModel,
    transactionViewModel: TransactionViewModel
){
    QrScaffold(
        navController = navController,
        paymentViewModel = paymentViewModel,
        machineViewModel = machineViewModel,
        transactionViewModel = transactionViewModel
    )
}

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun QrScreenWall(
    navController: NavController,
    paymentViewModel: PaymentQrisViewModel,
    machineViewModel: MachineViewModel,
    transactionViewModel: TransactionViewModel
) {
//    STOP_GET_PAYMENT_STATUS = false

    val context = LocalContext.current
    val stateQR = paymentViewModel.stateQR
    val payment = paymentViewModel.rawString

    ConstraintLayout(modifier = Modifier
        .background(MaterialTheme.colors.onPrimary)
        .fillMaxSize()
        .padding(start = 16.dp, end = 16.dp, bottom = 10.dp)) {
        val (title,number, content, buttonOK, buttonCheck) = createRefs()
        val modifier: Modifier = Modifier

//        Log.d("debug", "Price ${PRICE_VALUE[0].price}")

        Text(
            color = MaterialTheme.colors.primary,
            fontSize = MaterialTheme.typography.h4.fontSize,
            fontWeight = FontWeight.SemiBold,
            text = "Payment Qris",
            modifier = modifier.constrainAs(title){
                top.linkTo(parent.top, 30.dp)
                start.linkTo(parent.start)
                end.linkTo(parent.end)
            }
        )

        Text(
            color = MaterialTheme.colors.primary,
            fontSize = MaterialTheme.typography.h6.fontSize,
            fontWeight = FontWeight.SemiBold,
            text = "Machine Number $MACHINE_NUMBER",
            modifier = modifier.constrainAs(number){
                top.linkTo(title.bottom, 10.dp)
                start.linkTo(parent.start)
                end.linkTo(parent.end)
            }
        )

        Box(modifier = modifier.constrainAs(content) {
            bottom.linkTo(buttonCheck.top, 16.dp)
            start.linkTo(parent.start)
            end.linkTo(parent.end)
            top.linkTo(parent.top)
        }){
            LoadDataPayment(paymentState = stateQR, rawQR = payment)
            if (paymentViewModel.reffID != 0L){
                paymentViewModel.getResponsePayment()
            }
        }

        ButtonView(title = "Check", modifier.constrainAs(buttonCheck) {
            bottom.linkTo(buttonOK.top, 16.dp)
            start.linkTo(parent.start)
            end.linkTo(parent.end)
        }, enable = if(stateQR == 1) true else false){
//            CHECK_PAYMENT_OK = true
            PAYMENT_SUCCESS = true
//            STOP_GET_PAYMENT_STATUS = true
        }

        ButtonView(title = "Turn On Machine", modifier.constrainAs(buttonOK) {
            bottom.linkTo(parent.bottom, 16.dp)
            start.linkTo(parent.start)
            end.linkTo(parent.end)
        }, enable = if(PAYMENT_SUCCESS) true else false){

//            Toast.makeText(context, "MENU $MENU_VALUE", Toast.LENGTH_SHORT).show()
            machineViewModel.updateMachine(idMachine = MACHINE_ID, isPacket = PRICE_VALUE[0].isPacket!!)
            transactionViewModel.insertTransaction(
                classmachine = if(INDEX_CLASS_MACHINE == 0) false else true,
                idmachine = MACHINE_ID,
                price = PRICE_VALUE[0].price!!,
                typetransaction = MENU_VALUE,
                typePaymentTransaction = true,
                navController = navController,
                transactionMenuMachine = MENU_VALUE_MACHINE,
                numbermachine = MACHINE_NUMBER
            )
            paymentViewModel.reffID = 0L
        }
    }
}

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun QrScaffold(
    navController: NavController,
    paymentViewModel: PaymentQrisViewModel,
    machineViewModel: MachineViewModel,
    transactionViewModel: TransactionViewModel
) {
    val context = LocalContext.current

    Scaffold(
        topBar = {
            TopAppBarView(navController = navController, title = "Payment", screenBack = Screens.Machine.route)
        },
        backgroundColor = MaterialTheme.colors.onPrimary
    ){
        QrScreenWall(
            navController = navController,
            paymentViewModel = paymentViewModel,
            machineViewModel = machineViewModel,
            transactionViewModel = transactionViewModel
        )
    }
}