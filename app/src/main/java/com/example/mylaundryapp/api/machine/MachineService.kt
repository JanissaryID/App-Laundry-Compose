package com.example.mylaundryapp.api.machine

import retrofit2.Call
import retrofit2.http.*

interface MachineService {
    @GET("Machine")
    fun fetchMachine(): Call<List<MachineModel>>

    @GET("Machine")
    fun fetchFilterMachine(
        @Query(value="machine_class", encoded=true) classes: Boolean?,
        @Query(value="machine_type", encoded=true) type: Boolean?
    ): Call<List<MachineModel>>

    @PATCH("Machine/{id}")
    fun putMachine(
        @Path("id") id: Int?, @Body updateData : MachineModelUpdate
    ): Call<MachineModelUpdate>
}