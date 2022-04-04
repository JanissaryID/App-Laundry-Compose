package com.example.mylaundryapp.view

import androidx.compose.runtime.Composable
import com.example.mylaundryapp.api.price.PriceViewModel
import com.example.mylaundryapp.components.DropDownMenu
import com.example.mylaundryapp.components.TransactionItem

@Composable
fun ContentHome(index: Int, priceViewModel: PriceViewModel) {
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
            TransactionItem()
        }
    }
}