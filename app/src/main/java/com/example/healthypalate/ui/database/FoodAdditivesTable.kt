package com.example.healthypalate.ui.database

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
class FoodAdditivesTable(
    @PrimaryKey val id: Int,
    val name: String,
    val code: String,
    val type: String,
    val description: String,
    val effects: String,
    val alternative: String
)