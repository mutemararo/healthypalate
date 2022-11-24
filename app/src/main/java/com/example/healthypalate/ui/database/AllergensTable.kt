package com.example.healthypalate.ui.database

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
class AllergensTable(
    @PrimaryKey val id: String,
    val name: String,
    val effect: String
)