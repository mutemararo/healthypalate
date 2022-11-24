package com.example.healthypalate.ui.getstarted

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.example.healthypalate.R
import com.example.healthypalate.databinding.FragmentWeightBinding
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.FirebaseDatabase


class Weight : Fragment() {
    private lateinit var binding: FragmentWeightBinding

    private val user = FirebaseAuth.getInstance().currentUser?.uid
    private val databaseRef = FirebaseDatabase.getInstance().getReference("users/$user/weight")

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val fragment = FragmentWeightBinding.inflate(inflater, container, false)
        binding = fragment
        return fragment.root
    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.apply {
            weightFrag = this@Weight
        }
    }

    fun gotoBmi(){
        if (binding.weightInput.text.toString().isNotBlank()){
            databaseRef.setValue(binding.weightInput.text!!.trim().toString())
            findNavController().navigate(R.id.action_weight_to_BMI)
        }
    }
    fun backHeight(){
        findNavController().navigate(R.id.action_weight_to_height)
    }

    companion object{
        var weight = 0.0F
    }
}

