package com.example.mylaundryapp.navigation

sealed class Screens(val route: String){
    object Home: Screens(route = "home_screen")
    object Setting: Screens(route = "setting_screen")
    object Machine: Screens(route = "machine_screen")
    object Qris: Screens(route = "qri0s_screen")
//    object ListTransactions: Screens(route = "list_transactions_screen")
//    object Qris: Screen(route = "qris_screen?price={$QR_ARGUMENT_KEY}&number={$QR_ARGUMENT_KEY1}&idIndex={$QR_ARGUMENT_KEY2}"){
//        fun passPriceAndNumber(
//            price: String = "100",
//            number: Int = 0,
//            idIndex: Int = 10
//        ): String{
//            return "qris_screen?$QR_ARGUMENT_KEY=$price&$QR_ARGUMENT_KEY1=$number&$QR_ARGUMENT_KEY2=$idIndex"
//        }
//    }
}