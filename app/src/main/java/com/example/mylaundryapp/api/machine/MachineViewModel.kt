package com.example.mylaundryapp.api.machine

import android.util.Log
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import com.example.mylaundryapp.INDEX_CLASS_MACHINE
import com.example.mylaundryapp.MENU_VALUE
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.lang.Exception

class MachineViewModel: ViewModel() {

    var machineListResponse:List<MachineModel> by mutableStateOf(listOf())
    var stateMachine: Int by mutableStateOf(0)
    var errorMessage: String by mutableStateOf("")

    fun getMachine(){
        try {
            MachineApp.CreateInstance().fetchFilterMachine(
                classes = if(INDEX_CLASS_MACHINE == 0) false else true,
                type = if (MENU_VALUE != "Dryer") false else true
            ).enqueue(object :
                Callback<List<MachineModel>> {
                override fun onResponse(call: Call<List<MachineModel>>, response: Response<List<MachineModel>>) {
//                    Log.d("debug", "Code : ${response.code().toString()}")
                    stateMachine = 0
                    if(response.code() == 200){
                        response.body()?.let {
                            machineListResponse = response.body()!!
//                            MACHINE_DATA = machineListResponse
//                            Log.d("debug", "Code : ${response.code().toString()}")
//                            Log.d("debug", "Code : ${machineListResponse}")
                            stateMachine = 1
                        }
                    }
                }

                override fun onFailure(call: Call<List<MachineModel>>, t: Throwable) {
                    Log.d("debug", "Fail get Data ${t.message.toString()}")
                    if (t.message == t.message){
                        Log.d("debug", "Failed")
                        stateMachine = 2
//                        Toast.makeText(requireContext(), "Failed connect to server" , Toast.LENGTH_SHORT).show()
                    }
                }
            })
        }
        catch (e : Exception){
            errorMessage = e.message.toString()
            Log.d("debug", "ERROR $errorMessage")
//            Toast.makeText(requireContext(), "Error $e" , Toast.LENGTH_SHORT).show()
        }
    }

    fun updateMachine(idMachine: Int, isPacket: Boolean){
        val bodyUpdate = MachineModelUpdate(machineStatus = true, isPacket = isPacket)

        try {
            MachineApp.CreateInstance().putMachine(id = idMachine, bodyUpdate).enqueue(object : Callback<MachineModelUpdate> {
                override fun onResponse(call: Call<MachineModelUpdate>, response: Response<MachineModelUpdate>) {
//                    Log.d("debug", "Code ${response.code()}")
                    if(response.code() == 200){
//                        Log.d("debug", "Code ${response.code()}")
                    }
                }

                override fun onFailure(call: Call<MachineModelUpdate>, t: Throwable) {
                    Log.d("error", t.message.toString())
                    if (t.message == t.message){
//                        Toast.makeText(requireContext(), "Tidak ada koneksi Internet" , Toast.LENGTH_SHORT).show()
                    }
                }
            })
        }
        catch (e : Exception){
            errorMessage = e.message.toString()
            Log.d("debug", "ERROR $errorMessage")
//            Toast.makeText(requireContext(), "Error $e" , Toast.LENGTH_SHORT).show()
        }
    }
}