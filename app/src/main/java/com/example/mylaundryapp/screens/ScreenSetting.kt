package com.example.mylaundryapp.screens

import android.util.Log
import android.widget.Toast
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.gestures.scrollable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Card
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.mylaundryapp.*
import com.example.mylaundryapp.api.transaction.TransactionViewModel
import com.example.mylaundryapp.components.ButtonView
import com.example.mylaundryapp.components.TextFieldOutline
import com.example.mylaundryapp.components.TopAppBarView
import com.example.mylaundryapp.components.view.ViewSetting
import com.example.mylaundryapp.navigation.Screens
import com.example.mylaundryapp.room.setting.SettingItem
import com.example.mylaundryapp.room.setting.SettingViewModel
import com.example.mylaundryapp.ui.theme.MyLaundryAppTheme
import com.example.mylaundryapp.view.ContentHome

@Composable
fun ScreenSetting(navController: NavController, settingViewModel: SettingViewModel){
    SettingScaffold(navController = navController, settingViewModel = settingViewModel)
}

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun WallSetting(navController: NavController,settingViewModel: SettingViewModel) {
    val context = LocalContext.current

    val scrollState = rememberScrollState()

    ConstraintLayout(modifier = Modifier
        .padding(start = 16.dp, end = 16.dp, top = 16.dp)
        .fillMaxSize()
    ) {
        val (content,button_pick_machine) = createRefs()
        val modifier = Modifier

        LazyColumn(
            verticalArrangement = Arrangement.spacedBy(16.dp),
            modifier = modifier
                .constrainAs(content) {
                    top.linkTo(parent.top)
                    start.linkTo(parent.start)
                    end.linkTo(parent.end)
                }
        ){
            itemsIndexed(items = VALUE_SETTING) { index, item ->
                ViewSetting(
                    modifier = modifier.verticalScroll(state = scrollState),
                    valueItem = item.valueSetting.toString(),
                    texts = item.nameSetting.toString()
                ) {
//                    Toast.makeText(context, "Value Setting in Field $index is ${TEXT_FIELD.text}", Toast.LENGTH_SHORT).show()
                    val settingItem = SettingItem(
                        idSettings = index,
                        nameSetting = item.nameSetting.toString(),
                        valueSetting = TEXT_FIELD.text
                    )
                    settingViewModel.updateSettings(settingItem)
                }
            }

        }
    }
}

@Composable
fun SettingScaffold(navController: NavController,settingViewModel: SettingViewModel) {
    val context = LocalContext.current
    Scaffold(
        topBar = { TopAppBarView(
            navController = navController,
            title = TITLE_SCREEN[1],
            screenBack = Screens.Home.route
        ) },
        backgroundColor = MaterialTheme.colors.onPrimary
    ){
        WallSetting(navController = navController, settingViewModel = settingViewModel)
//        SettingWall(navController = navController)
    }
}

@Composable
fun insertData(settingViewModel: SettingViewModel) {
    val context = LocalContext.current

    val selectionMenu = listOf("IP Address", "Client ID", "Client Key", "Merchant ID")

    selectionMenu.forEachIndexed { index, s ->
        val settingItem = SettingItem(
            idSettings = index,
            nameSetting = selectionMenu[index],
            valueSetting = ""
        )
        settingViewModel.addSettings(settingItem)
    }
}

