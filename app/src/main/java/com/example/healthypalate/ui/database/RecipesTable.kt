package com.example.healthypalate.ui.database

import androidx.annotation.NonNull
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import org.jetbrains.annotations.NotNull

@Entity
class RecipesTable(
    @PrimaryKey val id: Int,
    @NonNull @ColumnInfo(name = "recipe_name") val recipeName: String,
    @ColumnInfo(name = "recipe_ingredients") val recipeIngredients: String,
    @ColumnInfo(name = "recipe_method") val recipeMethod: String,
    val category: String
    )

