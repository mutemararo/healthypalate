package com.example.healthypalate.ui.home

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.navigation.fragment.navArgs
import com.example.healthypalate.R
import com.example.healthypalate.databinding.FragmentRecipesViewBinding
import com.example.healthypalate.ui.models.Recipe
import com.google.firebase.database.*
import com.nostra13.universalimageloader.core.ImageLoader

class RecipesView : Fragment() {


    private lateinit var binding : FragmentRecipesViewBinding

    private val navArgs : RecipesViewArgs by navArgs()
    private lateinit var databaseRef : DatabaseReference
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val frag = FragmentRecipesViewBinding.inflate(inflater, container, false)
        binding = frag
        return frag.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val recipeTitle = navArgs.recipeTitle
        displayRecipeDetails(recipeTitle)
    }


    private fun displayRecipeDetails(recipeTitle: String) {
        databaseRef = FirebaseDatabase.getInstance().getReference("recipes")

        databaseRef.child(recipeTitle).addValueEventListener(object : ValueEventListener{
            override fun onDataChange(snapshot: DataSnapshot) {
                val recipe = snapshot.getValue(Recipe::class.java)

                val recipeIngredients = StringBuilder()
                val recipeMethods = StringBuilder()

                binding.collapsingToolbar.title = recipe?.recipeTitle
                ImageLoader.getInstance().displayImage(recipe?.recipeImage, binding.recipeDetailImage)

                for (ingredient in recipe?.recipeIngredients!!){
                    recipeIngredients.append("$ingredient \n")
                }

                for (method in recipe.recipeMethod){
                    recipeMethods.append("$method \n")
                }
                binding.recipeViewCategory.text = recipe.recipeCategory
                binding.ingredientsList.text = recipeIngredients.toString()
                binding.methodList.text = recipeMethods.toString()

            }
            override fun onCancelled(error: DatabaseError) {
                TODO("Not yet implemented")
            }

        })


    }
}

