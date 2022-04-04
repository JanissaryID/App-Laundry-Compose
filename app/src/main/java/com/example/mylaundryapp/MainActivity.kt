package com.example.mylaundryapp

import android.os.Build
import android.os.Bundle
import android.view.WindowManager
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.annotation.RequiresApi
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.example.mylaundryapp.api.machine.MachineViewModel
import com.example.mylaundryapp.api.payment.PaymentQrisViewModel
import com.example.mylaundryapp.api.price.PriceViewModel
import com.example.mylaundryapp.api.transaction.TransactionViewModel
import com.example.mylaundryapp.navigation.NavGraphSetup
import com.example.mylaundryapp.room.setting.SettingViewModel
import com.example.mylaundryapp.screens.insertData
import com.example.mylaundryapp.ui.theme.MyLaundryAppTheme

class MainActivity : ComponentActivity() {

    lateinit var navController: NavHostController

    val machineViewModel by viewModels<MachineViewModel>()
    val paymentViewModel by viewModels<PaymentQrisViewModel>()
    val transactionViewModel by viewModels<TransactionViewModel>()
    val priceViewModel by viewModels<PriceViewModel>()
    val settingViewModel by viewModels<SettingViewModel>()

    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

//        window.setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_RESIZE)
        setContent {
            MyLaundryAppTheme {
                // A surface container using the 'background' color from the theme
                navController = rememberNavController()
                NavGraphSetup(
                    navController = navController,
                    paymentViewModel = paymentViewModel,
                    settingViewModel = settingViewModel,
                    machineViewModel = machineViewModel,
                    transactionViewModel = transactionViewModel,
                    priceViewModel = priceViewModel
                )
            }
            insertData(settingViewModel = settingViewModel)
        }
    }
}