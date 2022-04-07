package com.example.mylaundryapp

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.text.input.TextFieldValue
import com.example.mylaundryapp.api.machine.MachineModel
import com.example.mylaundryapp.api.price.PriceModelMenu
import com.example.mylaundryapp.api.price.PriceModelView
import com.example.mylaundryapp.api.transaction.TransactionModel
import com.example.mylaundryapp.room.setting.SettingItem

// Home Variable
val TITLE_SCREEN = listOf("Home Screen", "Setting", "Transaction Active", "List Transaction")
var MENU_VALUE: String by mutableStateOf("")
var MENU_VALUE_MACHINE: String by mutableStateOf("")
var INDEX_CLASS_MACHINE: Int by mutableStateOf(-1)
var PRICE_VALUE: ArrayList<PriceModelView> by mutableStateOf(arrayListOf())
var TEMP_MENU_VALUE: String by mutableStateOf("")
var TITLE_MENU_MACHINE:ArrayList<PriceModelMenu> by mutableStateOf(arrayListOf())

var SELECTED_INDEX: Int by mutableStateOf(-1)
var TEMP_SELECTED_INDEX: Int by mutableStateOf(-1)
val ON_CLICK_INDEX = { index: Int -> SELECTED_INDEX = index}
//var BUTTON = false

//var CLICKED_BUTTON: Boolean by mutableStateOf(false)

//Machine List Variable
var ENABLE_BUTTON: Boolean by mutableStateOf(false)

//Payment Variable
var CLIENT_ID: String by mutableStateOf("")
var CLIENT_KEY: String by mutableStateOf("")
var MERCHANT_ID: String by mutableStateOf("")

//var CLIENT_ID = "6a7ba6b1a2e6eaf211bfc87c2ba7b6dc"
//var CLIENT_KEY = "1acb950632a327bd45638e16c6766bef"
//var MERCHANT_ID = "210910003000000"

//Qris Payment Variable
var MACHINE_ID: Int by mutableStateOf(0)
var MACHINE_NUMBER: Int by mutableStateOf(0)
var PRICE_SERVICE_LAUNDRY: Int by mutableStateOf(0)
var PAYMENT_SUCCESS: Boolean by mutableStateOf(false)

//Setting Variable
var TEXT_FIELD by mutableStateOf(TextFieldValue(""))
var VALUE_SETTING:List<SettingItem> by mutableStateOf(arrayListOf())

//Network Variable
var IP_ADDRESS: String by mutableStateOf("")

//Transaction Variable
var TRANSACTION_ACTIVE: Int by mutableStateOf(0)
var IS_DIALOG_OPEN =  mutableStateOf(false)

//Excel Variable
var EXCEL_VALUE:List<TransactionModel> by mutableStateOf(arrayListOf())
var DATE_PICK: String by mutableStateOf("")