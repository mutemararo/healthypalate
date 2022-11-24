package com.example.healthypalate.ui.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.healthypalate.databinding.DailyMenuItemBinding
import com.example.healthypalate.ui.models.RecipesModel

class DailyMealAdapter(var list: List<RecipesModel>) : RecyclerView.Adapter<MealHolder.DailyMenuHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MealHolder.DailyMenuHolder {
        val item = DailyMenuItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return MealHolder.DailyMenuHolder(item)
    }

    override fun onBindViewHolder(holder: MealHolder.DailyMenuHolder, position: Int) {
        holder.bind(list[position])
    }

    override fun getItemCount(): Int = list.size
}