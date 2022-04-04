package com.example.mylaundryapp.api.transaction

import android.os.Build
import android.util.Log
import androidx.annotation.RequiresApi
import androidx.lifecycle.ViewModel
import androidx.navigation.NavController
import com.example.mylaundryapp.navigation.Screens
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

class TransactionViewModel: ViewModel() {

    @RequiresApi(Build.VERSION_CODES.O)
    fun insertTransaction(
        classmachine: Boolean,
        numbermachine: Int,
        price: String,
        typetransaction: String,
        typePaymentTransaction: Boolean,
        navController: NavController,
        transactionMenuMachine: String
    ){
        val current = LocalDateTime.now()

        val formatDay = DateTimeFormatter.ofPattern("dd-MM-yyyy")
        val date = current.format(formatDay)

//        Log.d("debug", "Type Transaction is in ViewModel $typetransaction")

        val bodyUpdate = TransactionModel(
            transactionFinish = false,
            transactionStore = 1,
            transactionTypePayment = if(typePaymentTransaction) "Qris" else "Cash",
            transactionPrice = price,
            transactionNumberMachine = numbermachine,
            transactionClassMachine = if(classmachine) "Titan" else "Giant",
            transactionDate = date,
            transactionTypeMenu = typetransaction,
            transactionMenuMachine = transactionMenuMachine
        )

        TransactionApp.CreateInstance().insertTransactions(bodyUpdate).enqueue(object :
            Callback<TransactionModel> {
            override fun onResponse(call: Call<TransactionModel>, response: Response<TransactionModel>) {

                navController.navigate(route = Screens.Home.route){
                    popUpTo(Screens.Home.route) {
                        inclusive = true
                    }
                }

            }

            override fun onFailure(call: Call<TransactionModel>, t: Throwable) {
                Log.d("error", t.message.toString())
                if (t.message == t.message){

//                    BtnOnMachine.isEnabled =true

//                    Toast.makeText(requireContext(), "Tidak ada koneksi Internet" , Toast.LENGTH_SHORT).show()
                }
            }
        })
    }
}