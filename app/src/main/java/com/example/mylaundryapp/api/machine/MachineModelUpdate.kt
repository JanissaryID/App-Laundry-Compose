package com.example.mylaundryapp.api.machine

import com.google.gson.annotations.SerializedName

data class MachineModelUpdate(

	@field:SerializedName("is_packet")
	val isPacket: Boolean? = null,

	@field:SerializedName("machine_status")
	val machineStatus: Boolean? = null
)
