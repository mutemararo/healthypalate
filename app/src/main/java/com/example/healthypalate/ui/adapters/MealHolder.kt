package com.example.healthypalate.ui.adapters

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.example.healthypalate.databinding.DailyMenuItemBinding
import com.example.healthypalate.ui.models.RecipesModel

abstract class MealHolder<T>(itemView: View) : RecyclerView.ViewHolder(itemView) {
    abstract fun bind(item: T)

    class DailyMenuHolder(var item: DailyMenuItemBinding): MealHolder<RecipesModel>(item.root){
        override fun bind(itemModel: RecipesModel) {
            item.apply {
                recipeModel = itemModel
                recipeImage.setImageResource(itemModel.recipe_image)
            }
        }

    }
}