package com.example.mylaundryapp.api.price

import android.util.Log
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import com.example.mylaundryapp.IP_ADDRESS
import kotlinx.coroutines.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.lang.Exception

class PriceViewModel: ViewModel() {

    var priceListResponse:List<PriceModel> by mutableStateOf(listOf())
    var statePrice: Int by mutableStateOf(0)
    var errorMessage: String by mutableStateOf("")

    fun getPrice(){
        try {
            var viewModelJob = Job()
            val uiScope = CoroutineScope(Dispatchers.Main + viewModelJob)

            uiScope.launch {
                withContext(Dispatchers.IO) {
                    while (true){
                        if (!IP_ADDRESS.isNullOrEmpty()){
                            PriceApp.CreateInstance().fetchPrice().enqueue(object :
                                Callback<List<PriceModel>> {
                                override fun onResponse(call: Call<List<PriceModel>>, response: Response<List<PriceModel>>) {
                                    statePrice = 0
                                    if(response.code() == 200){
                                        response.body()?.let {
                                            priceListResponse = response.body()!!
                                            statePrice = 1
                                        }
                                    }
                                }

                                override fun onFailure(call: Call<List<PriceModel>>, t: Throwable) {
                                    Log.d("debug", "Fail get Data ${t.message.toString()}")
                                    if (t.message == t.message){
                                        Log.d("debug", "Failed")
                                        statePrice = 2
//                        Toast.makeText(requireContext(), "Failed connect to server" , Toast.LENGTH_SHORT).show()
                                    }
                                }
                            })
                            break
                        }
                    }
                    delay(100)
                }
            }
        }
        catch (e : Exception){
//            Log.d("debug", "ERROR")
            errorMessage = e.message.toString()
        }
    }
}