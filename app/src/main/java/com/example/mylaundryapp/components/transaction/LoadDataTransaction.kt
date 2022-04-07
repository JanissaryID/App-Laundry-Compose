package com.example.mylaundryapp.components.transaction

import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.asImageBitmap
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.example.mylaundryapp.PAYMENT_SUCCESS
import com.example.mylaundryapp.R
import com.example.mylaundryapp.api.machine.MachineModel
import com.example.mylaundryapp.api.transaction.TransactionModel
import com.example.mylaundryapp.components.QRGenerator
import com.example.mylaundryapp.components.column.ColumnMachine
import com.example.mylaundryapp.components.column.ColumnTransaction

@Composable
fun LoadDataTransaction(
    transaction: List<TransactionModel>,
    transactionState: Int,
    selectedIndex: Int,
    onItemClick: (Int) -> Unit
) {
    val context = LocalContext.current
//    Log.d("debug", "Get $GET_DATA_MACHINE_STAT")
    when (transactionState) {
        0 -> {
//            Log.d("debug", "Loading")
            Box(
                modifier = Modifier.fillMaxSize(),
                contentAlignment = Alignment.Center
            ) {
                CircularProgressIndicator()
            }
        }
        1 -> {
//            Log.d("debug", "Success")
            if (!transaction.isNullOrEmpty()){
                Box(
                    modifier = Modifier.fillMaxSize(),
                    contentAlignment = Alignment.Center
                ) {
                    ColumnTransaction(transactionModel = transaction, selectedIndex = selectedIndex, onItemClick = onItemClick)
                }

            }
        }
        2 -> {
            Box(
                modifier = Modifier.fillMaxSize(),
                contentAlignment = Alignment.Center
            ) {
                Text(
                    text = "Can't load data",
                    fontWeight = FontWeight.Bold,
                    color = MaterialTheme.colors.primary,
                )
            }
//            Log.d("debug", "Error")
            Toast.makeText(context, "Can't load data", Toast.LENGTH_SHORT).show()
        }
    }
}