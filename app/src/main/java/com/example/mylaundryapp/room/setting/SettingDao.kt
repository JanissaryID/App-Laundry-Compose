package com.example.mylaundryapp.room.setting

import androidx.lifecycle.LiveData
import androidx.room.*

@Dao
interface SettingDao {

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun addSettings(settings : SettingItem)

    @Query("SELECT * FROM SettingDB ORDER BY idSettings ASC")
    fun readAllData(): LiveData<List<SettingItem>>

    @Delete
    suspend fun deleteSettings(settings: SettingItem)

    @Update
    fun updateValueSettings(settings: SettingItem)

    @Query("SELECT * FROM SettingDB")
    fun getAllData():List<SettingItem>

}