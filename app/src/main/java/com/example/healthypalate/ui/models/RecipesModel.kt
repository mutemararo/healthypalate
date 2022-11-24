package com.example.healthypalate.ui.models

import android.content.Context
import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import com.example.healthypalate.R
import com.example.healthypalate.ui.home.RecipesFragment
import com.nostra13.universalimageloader.cache.memory.impl.WeakMemoryCache
import com.nostra13.universalimageloader.core.DisplayImageOptions
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration
import com.nostra13.universalimageloader.core.assist.ImageScaleType
import com.nostra13.universalimageloader.core.display.FadeInBitmapDisplayer


class RecipesModel(
    var id: Int,
    val recipe_image: Int,
    @StringRes val recipe_title: Int,
    @StringRes val recipe_category: Int)


val daily_meal_list = listOf(
    RecipesModel(
        1,
        R.drawable.avos,
        R.string.recipe1,
        R.string.category1
    ),
    RecipesModel(
        2,
        R.drawable.baked_mushroom,
        R.string.recipe2,
        R.string.category2
    ),
    RecipesModel(
        3,
        R.drawable.healthy_eating_ingredients_732x549_thumbnail,
        R.string.recipe3,
        R.string.category3
    )
)

class Recipe{
    var id : Int = 0
    var recipeTitle = ""
    var recipeCategory = ""
    var recipeIngredients = ArrayList<String>()
    var recipeMethod = ArrayList<String>()
    var recipeImage = ""
    var likedRecipe = false
}

class UniversalImageLoader(val context: Context){
    companion object{
        var defaultImage = R.drawable.icon_meals
    }

    fun getConfig(): ImageLoaderConfiguration{
        val defaultOptions = DisplayImageOptions.Builder()
            .showImageOnLoading(defaultImage)
            .showImageForEmptyUri(defaultImage)
            .showImageOnFail(defaultImage)
            .cacheOnDisk(true).cacheInMemory(true)
            .cacheOnDisk(true).resetViewBeforeLoading(true)
            .imageScaleType(ImageScaleType.EXACTLY)
            .displayer(FadeInBitmapDisplayer(300))
            .build()

        val config = ImageLoaderConfiguration.Builder(
            context as Context
        ).defaultDisplayImageOptions(defaultOptions)
            .memoryCache(WeakMemoryCache())
            .diskCacheSize(100 * 1024 * 1024)
            .build()

        return config
    }
}

