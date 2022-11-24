package com.example.healthypalate.ui.database

import androidx.annotation.NonNull
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
class IngredientsTable(
    @PrimaryKey val id: Int,
    @NonNull val name: String,
    val description: String,
    val nutrients: String
)