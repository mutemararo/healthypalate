package com.example.healthypalate.ui.database

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
class ScansTable(
    @PrimaryKey val id: String,
    val date: Long,
    val food_items: String,
    val image_uri: String,
    val description: String
)