package com.example.mylaundryapp.room.setting

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class SettingViewModel(application: Application): AndroidViewModel(application) {

    val readAllData: LiveData<List<SettingItem>>
    private val repository: SettingRepository

    init {
        val settingDao = SettingDatabase.getDatabase(application).settingsDao()
        repository = SettingRepository(settingDao)
        readAllData = repository.readAllData
    }

    fun addSettings(settings: SettingItem){
        viewModelScope.launch(Dispatchers.IO) {
            repository.addSettings(settings)
        }
    }

    fun updateSettings(settings: SettingItem){
        viewModelScope.launch(Dispatchers.IO) {
            repository.updatevalueSettings(settings)
        }
    }
}