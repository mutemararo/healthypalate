package com.example.healthypalate.ui.home

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.example.healthypalate.R
import com.example.healthypalate.databinding.FragmentRecipesBinding
import com.example.healthypalate.ui.adapters.RecipesAdapter
import com.example.healthypalate.ui.models.Recipe
import com.example.healthypalate.ui.models.UniversalImageLoader
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener


class RecipesFragment : Fragment() {

    private lateinit var binding: FragmentRecipesBinding

    private var recipeList = mutableListOf<Recipe>()

    private val dataRef = FirebaseDatabase.getInstance().getReference("recipes")


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val fragment = FragmentRecipesBinding.inflate(inflater, container, false)
        binding = fragment
        return fragment.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val recipeAdapter = RecipesAdapter(recipeList){
            val action = RecipesFragmentDirections.actionRecipesFragmentToRecipesView(it.recipeTitle)
            findNavController().navigate(action)
        }
        binding.apply {
            recipeList.adapter = recipeAdapter
        }

        dataRef.addValueEventListener(object : ValueEventListener{
            override fun onDataChange(snapshot: DataSnapshot) {
                for (recipes in snapshot.children){
                    val recipe = recipes.getValue(Recipe::class.java)

                    recipe?.let { recipeList.add(it) }
                }

                recipeAdapter.submitList(recipeList)
            }

            override fun onCancelled(error: DatabaseError) {
                TODO("Not yet implemented")
            }

        })
    }

}