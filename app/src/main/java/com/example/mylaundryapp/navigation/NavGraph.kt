package com.example.mylaundryapp.navigation

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.mylaundryapp.*
import com.example.mylaundryapp.api.machine.MachineViewModel
import com.example.mylaundryapp.api.payment.PaymentQrisViewModel
import com.example.mylaundryapp.api.price.PriceViewModel
import com.example.mylaundryapp.api.transaction.TransactionViewModel
import com.example.mylaundryapp.excel.ExcelViewModel
import com.example.mylaundryapp.room.setting.SettingViewModel
import com.example.mylaundryapp.screens.*
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

@RequiresApi(Build.VERSION_CODES.O)
@OptIn(ExperimentalAnimationApi::class)
@Composable
fun NavGraphSetup(
    navController: NavHostController,
    machineViewModel: MachineViewModel,
    paymentViewModel: PaymentQrisViewModel,
    transactionViewModel: TransactionViewModel,
    priceViewModel: PriceViewModel,
    settingViewModel: SettingViewModel,
    excelViewModel: ExcelViewModel
) {
    NavHost(navController = navController, startDestination = Screens.Home.route){

        composable(
            route = Screens.Home.route,
        ){
            priceViewModel.getPrice()
            ScreenHome(
                navController = navController,
                priceViewModel = priceViewModel,
                settingViewModel = settingViewModel,
                transactionViewModel = transactionViewModel,
                machineViewModel = machineViewModel
            )
        }

        composable(
            route = Screens.Setting.route,
        ){
            ScreenSetting(navController = navController, settingViewModel = settingViewModel)
        }

        composable(
            route = Screens.Machine.route,
        ){
            paymentViewModel.reffID = 0L
            paymentViewModel.rawString = "" //untuk mengkosongkan raw
            paymentViewModel.stateQR = 0    //untuk mengembalikan state ke loading
            machineViewModel.getMachine()
            ScreenMachineList(navController = navController, machineViewModel = machineViewModel, transactionViewModel = transactionViewModel)
        }

        composable(
            route = Screens.Qris.route,
        ){
            //untuk stop get data status payment
            if (!PAYMENT_SUCCESS){
                paymentViewModel.getQR()
            }
            ScreenQris(
                navController = navController,
                paymentViewModel = paymentViewModel,
                machineViewModel = machineViewModel,
                transactionViewModel = transactionViewModel
            )
        }

        composable(
            route = Screens.ListTransactionsActive.route,
        ){
            transactionViewModel.getFilterTransactionActive()
            ScreenTransaction(navController = navController, transactionViewModel = transactionViewModel)
        }

        composable(
            route = Screens.ListTransactions.route,
        ){
            if (DATE_PICK != ""){
                transactionViewModel.getTransaction()
            }
            ScreenTransactionList(navController = navController, transactionViewModel = transactionViewModel, excelViewModel = excelViewModel)
        }

        composable(
            route = Screens.MachineDryer.route,
        ){
            machineViewModel.getMachine()
            ScreenMachineListDryerOnly(navController = navController, machineViewModel = machineViewModel, transactionViewModel = transactionViewModel)
        }

    }
}