package com.example.healthypalate.ui

import android.app.Application
import com.example.healthypalate.ui.database.HealthyPalateDatabase

class HealthyPalateApplication : Application(){
    val database: HealthyPalateDatabase by lazy {
        HealthyPalateDatabase.getDatabase(this)
    }
}