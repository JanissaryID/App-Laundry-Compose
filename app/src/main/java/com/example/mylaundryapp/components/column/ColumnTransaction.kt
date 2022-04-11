package com.example.mylaundryapp.components.column

import android.widget.Toast
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.lazy.GridCells
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyVerticalGrid
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.mylaundryapp.INDEX_CLASS_MACHINE
import com.example.mylaundryapp.MENU_VALUE
import com.example.mylaundryapp.api.machine.MachineModel
import com.example.mylaundryapp.api.transaction.TransactionModel
import com.example.mylaundryapp.components.machine.ViewItemMachine
import com.example.mylaundryapp.components.view.ViewTransactionActive

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun ColumnTransaction(navController: NavController,is_list: Boolean,transactionModel: List<TransactionModel>, selectedIndex: Int, onItemClick: (Int) -> Unit){
    val context = LocalContext.current
    LazyColumn(
        contentPadding = PaddingValues(vertical = 8.dp),
        verticalArrangement = Arrangement.spacedBy(12.dp),
    ) {
        itemsIndexed(items = transactionModel) { index, transaction ->
            ViewTransactionActive(
                title_Menu = transaction.transactionTypeMenu.toString(),
                menu_machine = transaction.transactionMenuMachine.toString(),
                class_machine = transaction.transactionClassMachine.toString(),
                date = transaction.transactionDate.toString(),
                price = transaction.transactionPrice.toString(),
                payment = transaction.transactionTypePayment.toString(),
                is_packet = transaction.isPacket!!,
                is_list_transaction = is_list,
                step_one = transaction.stepOne!!,
                navController = navController,
                index = transaction.id!!.toInt()
            ){}
        }
    }
}