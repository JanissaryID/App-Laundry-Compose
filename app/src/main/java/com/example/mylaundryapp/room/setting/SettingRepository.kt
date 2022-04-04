package com.example.mylaundryapp.room.setting

import androidx.lifecycle.LiveData

class SettingRepository(private val settingDao: SettingDao) {

    val readAllData: LiveData<List<SettingItem>> = settingDao.readAllData()

    suspend fun addSettings(settings : SettingItem){
        settingDao.addSettings(settings)
    }

    fun updatevalueSettings(settings: SettingItem){
        settingDao.updateValueSettings(settings)
    }
}