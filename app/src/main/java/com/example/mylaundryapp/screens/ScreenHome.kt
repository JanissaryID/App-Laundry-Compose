package com.example.mylaundryapp.screens

import android.util.Log
import android.widget.Toast
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
import com.example.mylaundryapp.api.price.PriceApp
import com.example.mylaundryapp.api.price.PriceViewModel
import com.example.mylaundryapp.components.ButtonView
import com.example.mylaundryapp.components.TopAppBarViewHome
import com.example.mylaundryapp.navigation.Screens
import com.example.mylaundryapp.room.setting.SettingViewModel
import com.example.mylaundryapp.ui.theme.MyLaundryAppTheme
import com.example.mylaundryapp.view.ContentHome

@Composable
fun ScreenHome(
    navController: NavController,
    priceViewModel: PriceViewModel,
    settingViewModel: SettingViewModel
) {
    Scaffold(
        topBar = { TopAppBarViewHome(
            navController = navController,
            title = TITLE_SCREEN[0]
        ) },
        backgroundColor = MaterialTheme.colors.background
    ){
        SELECTED_INDEX = -1
        VALUE_SETTING = settingViewModel.readAllData.observeAsState(listOf()).value
        WallHome(navController = navController, priceViewModel = priceViewModel, settingViewModel = settingViewModel)
    }
}

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun WallHome(navController: NavController, priceViewModel: PriceViewModel, settingViewModel: SettingViewModel) {

    if (!VALUE_SETTING.isNullOrEmpty()){
        IP_ADDRESS = VALUE_SETTING[0].valueSetting.toString()
        CLIENT_ID = VALUE_SETTING[1].valueSetting.toString()
        CLIENT_KEY = VALUE_SETTING[2].valueSetting.toString()
        MERCHANT_ID = VALUE_SETTING[3].valueSetting.toString()
//        Log.d("debug", "Setting IP : ${VALUE_SETTING[0].valueSetting}")
//        Log.d("debug", "Client ID : ${VALUE_SETTING[1].valueSetting}")
//        Log.d("debug", "Client Key : ${VALUE_SETTING[2].valueSetting}")
//        Log.d("debug", "Merchant iD : ${VALUE_SETTING[3].valueSetting}")
    }

    PAYMENT_SUCCESS = false

    val context = LocalContext.current

    val selectionMenu = listOf("Menu", "Class Machine", "Menu Machine", "Transaction Active")

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
                    ContentHome(index = index, priceViewModel = priceViewModel)
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