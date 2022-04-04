package com.example.mylaundryapp.api.payment

import com.example.mylaundryapp.api.payment.qris.PaymentQrisModel
import com.example.mylaundryapp.api.payment.qris.PaymentQrisRawModel
import com.example.mylaundryapp.api.payment.responseqris.ResponsePaymentQrisGetModel
import com.example.mylaundryapp.api.payment.responseqris.ResponsePaymentQrisModel
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.Header
import retrofit2.http.Headers
import retrofit2.http.POST

interface PaymentQrisService {

    @Headers(
        "Accept: application/json",
        "Content-Type: application/json"
    )
    @POST("/qris/generate/")
    fun Qris(@Header("Authorization") authHeader: String, @Body qrData: PaymentQrisModel) : Call<PaymentQrisRawModel>

    @Headers(
        "Accept: application/json",
        "Content-Type: application/json"
    )
    @POST("/qris/check-status/")
    fun CheckPayment(@Header("Authorization") authHeader: String, @Body statusData: ResponsePaymentQrisModel) : Call<ResponsePaymentQrisGetModel>
}