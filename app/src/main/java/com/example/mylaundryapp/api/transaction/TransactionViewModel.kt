package com.example.mylaundryapp.api.transaction

import android.os.Build
import android.util.Log
import androidx.annotation.RequiresApi
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.navigation.NavController
import com.example.mylaundryapp.*
import com.example.mylaundryapp.api.machine.MachineApp
import com.example.mylaundryapp.api.machine.MachineModelUpdate
import com.example.mylaundryapp.navigation.Screens
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

class TransactionViewModel: ViewModel() {

    var transactionListResponse:List<TransactionModel> by mutableStateOf(listOf())
    var transactionListResponseActive:List<TransactionModel> by mutableStateOf(listOf())
    var stateTransaction: Int by mutableStateOf(0)
    var errorMessage: String by mutableStateOf("")

    @RequiresApi(Build.VERSION_CODES.O)
    fun insertTransaction(
        classmachine: Boolean,
        idmachine: Int,
        price: String,
        typetransaction: String,
        typePaymentTransaction: Boolean,
        navController: NavController,
        transactionMenuMachine: String,
    ){
        val current = LocalDateTime.now()

        val formatDay = DateTimeFormatter.ofPattern("dd-MM-yyyy")
        val date = current.format(formatDay)

//        Log.d("debug", "Type Transaction is in ViewModel $typetransaction")

        val bodyUpdate = TransactionModel(
            transactionStore = 1,
            transactionTypePayment = if(typePaymentTransaction) "Qris" else "Cash",
            transactionPrice = price,
            transactionNumberMachine = idmachine,
            transactionClassMachine = if(classmachine) "Titan" else "Giant",
            transactionDate = date,
            transactionTypeMenu = typetransaction,
            transactionMenuMachine = transactionMenuMachine,
            isPacket = if(MENU_VALUE == "Wash Iron" || MENU_VALUE == "Sell Service") true else false,
            stepOne = false,
            transactionFinish = false
        )

        TransactionApp.CreateInstance().insertTransactions(bodyUpdate).enqueue(object :
            Callback<TransactionModel> {
            override fun onResponse(call: Call<TransactionModel>, response: Response<TransactionModel>) {

                navController.navigate(route = Screens.Home.route){
                    popUpTo(Screens.Home.route) {
                        inclusive = true
                    }
                }

                MENU_VALUE = ""
                MENU_VALUE_MACHINE = ""
                INDEX_CLASS_MACHINE = -1

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

    fun getTransaction(){
        try {
            TransactionApp.CreateInstance().getFilterTransactionsFinishDate(date = "$DATE_PICK", finish =  true).enqueue(object :
                Callback<List<TransactionModel>> {
                override fun onResponse(call: Call<List<TransactionModel>>, response: Response<List<TransactionModel>>) {
                    Log.d("debug", "Date : ${DATE_PICK}")
                    stateTransaction = 0
                    if(response.code() == 200){
                        response.body()?.let {
                            transactionListResponse = response.body()!!
                            EXCEL_VALUE = transactionListResponse
//                            Log.d("debug", "Code : ${machineListResponse}")
                            stateTransaction = 1
                        }
                        TRANSACTION_ACTIVE = transactionListResponse.size
                        Log.d("debug", "Code : ${TRANSACTION_ACTIVE}")
                    }
                }

                override fun onFailure(call: Call<List<TransactionModel>>, t: Throwable) {
                    Log.d("debug", "Fail get Data ${t.message.toString()}")
                    if (t.message == t.message){
                        Log.d("debug", "Failed")
                        stateTransaction = 2
//                        Toast.makeText(requireContext(), "Failed connect to server" , Toast.LENGTH_SHORT).show()
                    }
                }
            })
        }
        catch (e : Exception){
//            Log.d("debug", "ERROR")
            errorMessage = e.message.toString()
//            Toast.makeText(requireContext(), "Error $e" , Toast.LENGTH_SHORT).show()
        }
    }

    @RequiresApi(Build.VERSION_CODES.O)
    fun getFilterTransactionActive(){
        try {
            TransactionApp.CreateInstance().getFilterTransactionsFinish(finish =  false).enqueue(object :
                Callback<List<TransactionModel>> {
                override fun onResponse(call: Call<List<TransactionModel>>, response: Response<List<TransactionModel>>) {
                    stateTransaction = 0
                    if(response.code() == 200){
                        response.body()?.let {
                            transactionListResponseActive = response.body()!!
                            EXCEL_VALUE = transactionListResponse
//                            Log.d("debug", "Code : ${machineListResponse}")
                            stateTransaction = 1
                        }
                        TRANSACTION_ACTIVE = transactionListResponse.size
                        Log.d("debug", "Code : ${TRANSACTION_ACTIVE}")
                    }
                }

                override fun onFailure(call: Call<List<TransactionModel>>, t: Throwable) {
                    Log.d("debug", "Fail get Data ${t.message.toString()}")
                    if (t.message == t.message){
                        Log.d("debug", "Failed")
                        stateTransaction = 2
//                        Toast.makeText(requireContext(), "Failed connect to server" , Toast.LENGTH_SHORT).show()
                    }
                }
            })
        }
        catch (e : Exception){
//            Log.d("debug", "ERROR")
            errorMessage = e.message.toString()
//            Toast.makeText(requireContext(), "Error $e" , Toast.LENGTH_SHORT).show()
        }
    }

    fun updateTransaction(idTransaction: Int,navController: NavController){
        val bodyUpdate = TransactionModel(
            stepOne = false,
            transactionNumberMachine = MACHINE_ID
        )

        try {
            TransactionApp.CreateInstance().putTransaction(idTransaction, bodyUpdate).enqueue(object : Callback<TransactionModel> {
                override fun onResponse(call: Call<TransactionModel>, response: Response<TransactionModel>) {
                    if(response.code() == 200){

                        navController.navigate(route = Screens.Home.route){
                            popUpTo(Screens.Home.route) {
                                inclusive = true
                            }
                        }

                        MENU_VALUE = ""
                        MENU_VALUE_MACHINE = ""
                        INDEX_CLASS_MACHINE = -1
                        DRYER_INDEX_TRANSACTION = 0

                    }
                }

                override fun onFailure(call: Call<TransactionModel>, t: Throwable) {
                    Log.d("error", t.message.toString())
                    if (t.message == t.message){
//                        Toast.makeText(requireContext(), "Tidak ada koneksi Internet" , Toast.LENGTH_SHORT).show()
                    }
                }
            })
        }
        catch (e : java.lang.Exception){
            Log.d("debug", "ERROR")
//            Toast.makeText(requireContext(), "Error $e" , Toast.LENGTH_SHORT).show()
        }
    }
}