package com.example.mylaundryapp.screens

import android.os.Build
import android.util.Log
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.mylaundryapp.*
import com.example.mylaundryapp.api.machine.MachineViewModel
import com.example.mylaundryapp.api.price.PriceApp
import com.example.mylaundryapp.api.price.PriceViewModel
import com.example.mylaundryapp.api.transaction.TransactionViewModel
import com.example.mylaundryapp.components.ButtonView
import com.example.mylaundryapp.components.TopAppBarViewHome
import com.example.mylaundryapp.navigation.Screens
import com.example.mylaundryapp.room.setting.SettingViewModel
import com.example.mylaundryapp.ui.theme.MyLaundryAppTheme
import com.example.mylaundryapp.view.ContentHome
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun ScreenHome(
    navController: NavController,
    priceViewModel: PriceViewModel,
    settingViewModel: SettingViewModel,
    machineViewModel: MachineViewModel,
    transactionViewModel: TransactionViewModel
) {
    Scaffold(
        topBar = { TopAppBarViewHome(
            navController = navController,
            title = TITLE_SCREEN[0]
        ) },
        backgroundColor = MaterialTheme.colors.background
    ){
        val current = LocalDateTime.now()

        val formatDay = DateTimeFormatter.ofPattern("dd-MM-yyyy")
        val date = current.format(formatDay)
        DATE_PICK = date

        SELECTED_INDEX = -1
        VALUE_SETTING = settingViewModel.readAllData.observeAsState(listOf()).value
        WallHome(navController = navController, priceViewModel = priceViewModel, machineViewModel = machineViewModel)
    }
}

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun WallHome(navController: NavController, priceViewModel: PriceViewModel, machineViewModel: MachineViewModel) {
//    if (!CLICKED_BUTTON){
//        MENU_VALUE = ""
//        MENU_VALUE_MACHINE = ""
//        INDEX_CLASS_MACHINE = -1
//        CLICKED_BUTTON = true
//    }
//    paymentViewModel.reffID = 0L
    Log.d("debug", "Data home : $MENU_VALUE - $INDEX_CLASS_MACHINE - $MENU_VALUE_MACHINE")

    if (!VALUE_SETTING.isNullOrEmpty()){
        KEY_URL = VALUE_SETTING[0].valueSetting.toString()
        CLIENT_ID = VALUE_SETTING[1].valueSetting.toString()
        CLIENT_KEY = VALUE_SETTING[2].valueSetting.toString()
        MERCHANT_ID = VALUE_SETTING[3].valueSetting.toString()
    }

    PAYMENT_SUCCESS = false

    val context = LocalContext.current

    val selectionMenu = listOf("Menu", "Class Machine", "Menu Machine", "Transaction")

    ConstraintLayout(modifier = Modifier
        .padding(start = 16.dp, end = 16.dp, top = 16.dp)
        .fillMaxSize()
    ) {
        val (content,button_pick_machine) = createRefs()
        val modifier = Modifier

        LazyColumn(modifier = modifier.constrainAs(content){
            top.linkTo(parent.top)
            start.linkTo(parent.start)
            end.linkTo(parent.end)
        },
        ){
            selectionMenu.forEachIndexed {index,  sections ->
                stickyHeader {
                    Text(
                        text = sections,
                        fontSize = 24.sp,
                        color = MaterialTheme.colors.onSurface
                    )
                    ContentHome(index = index, priceViewModel = priceViewModel, navController = navController)
                }
            }
        }
        ButtonView(title = "Pick Machine", modifier.constrainAs(button_pick_machine) {
            bottom.linkTo(parent.bottom, 16.dp)
            start.linkTo(parent.start)
            end.linkTo(parent.end)
        }, enable =
            if (!MENU_VALUE.isNullOrEmpty() && !MENU_VALUE_MACHINE.isNullOrEmpty() && INDEX_CLASS_MACHINE != -1){
                true
            }
            else{
                false
            }
        ){
            navController.navigate(route = Screens.Machine.route)
//            Toast.makeText(context, "Value Menu $MENU_VALUE And $INDEX_CLASS_MACHINE and $INDEX_CLASS_MACHINE", Toast.LENGTH_SHORT).show()
        }
    }
}