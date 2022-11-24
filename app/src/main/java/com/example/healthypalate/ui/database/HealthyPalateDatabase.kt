package com.example.healthypalate.ui.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = arrayOf(ScansTable::class, RecipesTable::class, AllergensTable::class, NotesTable::class, IngredientsTable::class, FoodAdditivesTable::class), version = 2, exportSchema = false)
abstract class HealthyPalateDatabase: RoomDatabase(){
    abstract fun palateDao(): DatabaseDao
    companion object{
        @Volatile
        private var INSTANCE: HealthyPalateDatabase? = null

        fun getDatabase(context: Context): HealthyPalateDatabase{
            return INSTANCE?: synchronized(this){
                val instance = Room.databaseBuilder(
                    context,
                    HealthyPalateDatabase::class.java,
                    "healthy_palate_database"
                )
                    .fallbackToDestructiveMigration()
                    .build()
                INSTANCE = instance

                instance
            }
        }
    }
}