package com.example.mylaundryapp.view

import androidx.compose.runtime.Composable
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import com.example.mylaundryapp.INDEX_CLASS_MACHINE
import com.example.mylaundryapp.MENU_VALUE
import com.example.mylaundryapp.MENU_VALUE_MACHINE
import com.example.mylaundryapp.api.price.PriceViewModel
import com.example.mylaundryapp.components.DropDownMenu
import com.example.mylaundryapp.components.TransactionItem
import com.example.mylaundryapp.navigation.Screens

@Composable
fun ContentHome(index: Int, priceViewModel: PriceViewModel, navController: NavController) {
    when (index){
        0 ->{
            DropDownMenu(menuHome = false, priceViewModel = priceViewModel)
//            FilterPrice(false, priceViewModel = priceViewModel)
        }
        1 ->{
            ButtonMenuView()
        }
        2 ->{
            DropDownMenu(menuHome = true, priceViewModel = priceViewModel)
        }
        3 ->{
            TransactionItem{
                MENU_VALUE = ""
                MENU_VALUE_MACHINE = ""
                INDEX_CLASS_MACHINE = -1
//                CLICKED_BUTTON = false
                navController.navigate(route = Screens.ListTransactionsActive.route)
            }
            TransactionItem(title = "List Transaction"){
                MENU_VALUE = ""
                MENU_VALUE_MACHINE = ""
                INDEX_CLASS_MACHINE = -1
//                CLICKED_BUTTON = false/**/
                navController.navigate(route = Screens.ListTransactions.route)
            }
        }
    }
}