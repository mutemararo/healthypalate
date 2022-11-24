package com.example.healthypalate.ui.home

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.healthypalate.R
import com.example.healthypalate.databinding.FragmentFoodAdditivesBinding
import com.example.healthypalate.databinding.FragmentRecipesBinding
import com.example.healthypalate.ui.adapters.AdditiveAdapter
import com.example.healthypalate.ui.models.Additive
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener


class FoodAdditives : Fragment() {

    private val databaseRef = FirebaseDatabase.getInstance().getReference("additives")
    private val list = mutableListOf<Additive>()

    private lateinit var binding: FragmentFoodAdditivesBinding
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val frag = FragmentFoodAdditivesBinding.inflate(inflater, container, false)
        binding = frag
        return frag.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val adaptor = AdditiveAdapter(list)
        binding.apply {

            recyclerView.adapter = adaptor
        }

        databaseRef.addValueEventListener(object : ValueEventListener{
            override fun onDataChange(snapshot: DataSnapshot) {
                for (additives in snapshot.children){
                    val food = additives.getValue(Additive::class.java)

                    if (food != null) {
                        list.add(food)
                    }
                }
                adaptor.submitList(list)
            }

            override fun onCancelled(error: DatabaseError) {
                TODO("Not yet implemented")
            }

        })
    }

}