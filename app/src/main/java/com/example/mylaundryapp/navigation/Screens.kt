package com.example.mylaundryapp.navigation

sealed class Screens(val route: String){
    object Home: Screens(route = "home_screen")
    object Setting: Screens(route = "setting_screen")
    object Machine: Screens(route = "machine_screen")
    object MachineDryer: Screens(route = "machine_dryer_screen")
    object Qris: Screens(route = "qri0s_screen")
    object ListTransactionsActive: Screens(route = "list_transactions_screen_active")
    object ListTransactions: Screens(route = "list_transactions_screen")
}