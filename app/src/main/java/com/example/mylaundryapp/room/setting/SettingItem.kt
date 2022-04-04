package com.example.mylaundryapp.room.setting

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "SettingDB")
data class SettingItem(

    @PrimaryKey(autoGenerate = true)

    var idSettings: Int? = null,

    var nameSetting: String? = null,

    var valueSetting: String? = null

)