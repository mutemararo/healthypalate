package com.example.healthypalate.ui.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.ImageButton
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.healthypalate.R
import com.example.healthypalate.databinding.RecipeItemBinding
import com.example.healthypalate.ui.models.Recipe
import com.nostra13.universalimageloader.core.ImageLoader

class RecipesAdapter(private var list: MutableList<Recipe>, val itemClicked: (Recipe) -> Unit) : ListAdapter<Recipe, RecipesAdapter.RecipeViewHolder>(DiffUtil){

    class RecipeViewHolder(val binding: RecipeItemBinding): RecyclerView.ViewHolder(binding.root){

        fun bind(recipe: Recipe){
            binding.apply {

                recipeLike.setImageResource(if (recipe.likedRecipe){R.drawable.like_filled} else {R.drawable.like_outlined})
                recipeShare.setImageResource(R.drawable.icon_share)
                recipreListTitle.text = recipe.recipeTitle

                ImageLoader.getInstance().displayImage(recipe.recipeImage, recipeListImage)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecipeViewHolder {
        val view = RecipeItemBinding.inflate(LayoutInflater.from(parent.context))
        return RecipeViewHolder(view)
    }

    override fun onBindViewHolder(holder: RecipeViewHolder, position: Int) {
        val currentRecipe = list[position]
        holder.itemView.setOnClickListener {
            itemClicked(currentRecipe)
        }
        holder.bind(currentRecipe)
    }

    companion object{

        val DiffUtil = object : DiffUtil.ItemCallback<Recipe>(){
            override fun areItemsTheSame(oldItem: Recipe, newItem: Recipe): Boolean {
                return oldItem == newItem
            }

            override fun areContentsTheSame(oldItem: Recipe, newItem: Recipe): Boolean {
                return oldItem.id == newItem.id &&
                        oldItem.recipeTitle == newItem.recipeTitle
            }

        }
    }
}