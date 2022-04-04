package com.example.mylaundryapp.api.payment

import android.os.Build
import android.util.Log
import androidx.annotation.RequiresApi
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import com.example.mylaundryapp.*
import com.example.mylaundryapp.api.payment.qris.PaymentQrisModel
import com.example.mylaundryapp.api.payment.qris.PaymentQrisRawModel
import com.example.mylaundryapp.api.payment.responseqris.ResponsePaymentQrisGetModel
import com.example.mylaundryapp.api.payment.responseqris.ResponsePaymentQrisModel
import kotlinx.coroutines.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.lang.Exception
import java.util.*

class PaymentQrisViewModel: ViewModel() {

    var rawString: String by mutableStateOf("")
    var reffID: Long by mutableStateOf(0)
    var errorMessage: String by mutableStateOf("")
    var stateQR: Int by mutableStateOf(0)

    var insatanceRetrofitGetPayment: Unit? = null
    var insatanceRetrofitQR: Unit? = null

    fun getRandomString(length: Int) : String {
        val charset = "ABCDEFGHIJKLMNOPQRSTUVWXTZabcdefghiklmnopqrstuvwxyz0123456789"
        return (1..length)
            .map { charset.random() }
            .joinToString("")
    }

    @RequiresApi(Build.VERSION_CODES.O)
    fun getQR(){

        val length = 20
        val randomReff = getRandomString(length)

        val QRParameter = PaymentQrisModel(
            MERCHANT_ID,
//            10000,
            PRICE_VALUE[0].price!!.toInt(),
            0,
            randomReff,
            "https://webhook.site/73121a4e-5dd9-423d-980b-0ace6c719b90",
            5)

        val code = "$CLIENT_ID:$CLIENT_KEY:$MERCHANT_ID"

        val encodedString: String = Base64.getEncoder().encodeToString(code.toByteArray())

        try {
            insatanceRetrofitQR = PaymentQrisApp.CreateInstance().Qris("Bearer "+ encodedString,QRParameter).enqueue(object :
                Callback<PaymentQrisRawModel> {
                override fun onResponse(
                    call: Call<PaymentQrisRawModel>,
                    response: Response<PaymentQrisRawModel>
                ) {
                    stateQR = 0
                    if(!PAYMENT_SUCCESS){
                        if (response.code() == 200) {
                            if (response.body()?.status == "success"){
                                rawString = response.body()?.rawqr.toString()
//                            Log.d("debug", "raw QR View Model ${response.body()?.rawqr.toString()}")
                                Log.d("debug", "reff ID View Model ${response.body()?.refid!!}")
                                reffID = response.body()?.refid!!
                                stateQR = 1
                            }
                        }
                    }
                }

                override fun onFailure(call: Call<PaymentQrisRawModel>, t: Throwable) {
                    if (t.message == t.message){
                        Log.d("debug", "On Get Response Generate ERROR : ${t.message}")
                        stateQR = 2
//                        Toast.makeText(requireContext(), "Tidak ada koneksi Internet" , Toast.LENGTH_SHORT).show()
                    }
                }
            })
        }
        catch (e: Exception){
            Log.d("debug", e.toString())
            errorMessage = e.message.toString()
        }
    }

    @RequiresApi(Build.VERSION_CODES.O)
    fun getPaymentQR(refidPayment : Long?){
        val Parameter = ResponsePaymentQrisModel(refidPayment)

//        val code = "${LIST_VALUE_ROOM_SETTING_GET[1].valueSetting}:${LIST_VALUE_ROOM_SETTING_GET[2].valueSetting}:${LIST_VALUE_ROOM_SETTING_GET[3].valueSetting}"
        val code = "$CLIENT_ID:$CLIENT_KEY:$MERCHANT_ID"
        val encodedString: String = Base64.getEncoder().encodeToString(code.toByteArray())

        try{
            insatanceRetrofitGetPayment = PaymentQrisApp.CreateInstance().CheckPayment("Bearer "+ encodedString,Parameter).enqueue(object :
                Callback<ResponsePaymentQrisGetModel> {
                override fun onResponse(
                    call: Call<ResponsePaymentQrisGetModel>,
                    response: Response<ResponsePaymentQrisGetModel>
                ) {
                    if (response.code() == 200) {
                        if (response.body()?.status == "success"){
                            reffID = 0L
//                            STOP_GET_PAYMENT_STATUS = true
                            PAYMENT_SUCCESS = true
                            Log.d("debug", "Payment Success")
                        }
                        else{
//                            STOP_GET_PAYMENT_STATUS = false
//                            PAYMENT_SUCCESS = false
                            Log.d("debug", "Payment Active : $Parameter")
                        }
                    }
                    else {
//                        STOP_GET_PAYMENT_STATUS = false
                        PAYMENT_SUCCESS = false
                        Log.d("debug", "Respon code : "+response.code().toString())
                    }
                }

                override fun onFailure(call: Call<ResponsePaymentQrisGetModel>, t: Throwable) {
                    if (t.message == t.message){
                        Log.d("debug", "On Get Response ERROR : ${t.message}")
//                        Toast.makeText(requireContext(), "Error" , Toast.LENGTH_SHORT).show()
                    }
                }
            })
        }
        catch (e: Exception){
            Log.d("debug", e.toString())
        }
    }

    @RequiresApi(Build.VERSION_CODES.O)
    fun getResponsePayment(){

        var viewModelJob = Job()
        val uiScope = CoroutineScope(Dispatchers.Main + viewModelJob)

        uiScope.launch {
            withContext(Dispatchers.IO) {
                while (!PAYMENT_SUCCESS){
                    if (!PAYMENT_SUCCESS){
                        getPaymentQR(reffID)
                    }
                    else{
                        reffID = 0L
                        insatanceRetrofitGetPayment = null
                        insatanceRetrofitQR = null
                        PaymentQrisApp.DestroyInstance()
                        break
                    }
                    delay(1000)
                }
            }
        }
    }
}