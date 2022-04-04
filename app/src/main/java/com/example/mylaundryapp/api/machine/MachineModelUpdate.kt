package com.example.mylaundryapp.api.machine

import com.google.gson.annotations.SerializedName
import retrofit2.Call
import retrofit2.http.GET

data class MachineModelUpdate(
    @field:SerializedName("machine_status")
    val machineStatus: Boolean? = null
)