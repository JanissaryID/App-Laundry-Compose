package com.example.mylaundryapp.room.setting

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [SettingItem::class], version = 1, exportSchema = false)
abstract class SettingDatabase : RoomDatabase() {

    abstract fun settingsDao(): SettingDao

    companion object{
        @Volatile
        private var INSTANCE: SettingDatabase? = null

        fun getDatabase(context: Context) : SettingDatabase {
            val tempInstance = INSTANCE
            if (tempInstance != null){
                return tempInstance
            }
            synchronized(this){
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    SettingDatabase::class.java,
                    "LaundryDatabase"
                ).build()
                INSTANCE = instance
                return instance
            }
        }
        fun destroyInstance(){
            INSTANCE = null
        }
    }
}