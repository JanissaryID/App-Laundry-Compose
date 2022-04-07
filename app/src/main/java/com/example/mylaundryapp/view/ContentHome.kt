package com.example.mylaundryapp.view

import androidx.compose.runtime.Composable
import androidx.navigation.NavController
import androidx.navigation.NavHostController
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
            TransactionItem(){
                navController.navigate(route = Screens.ListTransactionsActive.route)
            }
            TransactionItem(title = "List Transaction"){
                navController.navigate(route = Screens.ListTransactions.route)
            }
        }
    }
}