package com.example.mylaundryapp.api.machine

import com.google.gson.annotations.SerializedName

data class MachineModel(

	@field:SerializedName("machine_type")
	val machineType: Boolean? = null,

	@field:SerializedName("is_packet")
	val isPacket: Boolean? = null,

	@field:SerializedName("machine_status")
	val machineStatus: Boolean? = null,

	@field:SerializedName("__powered_by")
	val poweredBy: String? = null,

	@field:SerializedName("machine_number")
	val machineNumber: Int? = null,

	@field:SerializedName("machine_store")
	val machineStore: Int? = null,

	@field:SerializedName("machine_class")
	val machineClass: Boolean? = null,

	@field:SerializedName("_id")
	val id: String? = null
)
