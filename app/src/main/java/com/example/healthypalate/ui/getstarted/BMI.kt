package com.example.healthypalate.ui.getstarted

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.example.healthypalate.R
import com.example.healthypalate.databinding.FragmentBMIBinding
import com.example.healthypalate.ui.models.User
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import kotlin.math.roundToInt

class BMI : Fragment() {
    private lateinit var binding : FragmentBMIBinding

    private val user = FirebaseAuth.getInstance().currentUser?.uid
    private val databaseRef = FirebaseDatabase.getInstance().getReference("users/$user")

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentBMIBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.apply {
          bmifragment = this@BMI

        }

        databaseRef.addValueEventListener(object : ValueEventListener{
            override fun onDataChange(snapshot: DataSnapshot) {
                val user = snapshot.getValue(User::class.java)!!

                var tempBmi : String
                var weight = user.weight.toDouble()
                var height = user.height.toDouble()

                var bmi = weight / (height * height)
                databaseRef.child("bmi").setValue(bmi.toString())
                tempBmi = "$bmi"

                binding.tvBmi.text = tempBmi
                showBMI(bmi)
            }

            override fun onCancelled(error: DatabaseError) {

            }

        })
    }

    private fun showBMI(bmi: Double) {
        when(bmi){
            bmi.coerceAtMost(18.5) -> {
                binding.underweight.setBackgroundColor(resources.getColor(R.color.purple_200))
            }
            bmi.coerceIn(18.5, 24.9) -> {
                binding.normal.setBackgroundColor(resources.getColor(R.color.purple_200))
            }
            25.0->{
                binding.overweight.setBackgroundColor(resources.getColor(R.color.purple_200))
            }
            bmi.coerceIn(25.0, 29.9) -> {
                binding.preobese.setBackgroundColor(resources.getColor(R.color.purple_200))
            }
            30.0 -> {
                binding.obese.setBackgroundColor(resources.getColor(R.color.purple_200))
            }
            bmi.coerceIn(30.0, 34.9) -> {
                binding.obeseclass1.setBackgroundColor(resources.getColor(R.color.purple_200))
            }
            bmi.coerceIn(35.0, 39.9) -> {
                binding.obeseclass2.setBackgroundColor(resources.getColor(R.color.purple_200))
            }
            bmi.coerceAtLeast(40.0) -> {
                binding.obeseclass3.setBackgroundColor(resources.getColor(R.color.purple_200))
            }
        }
    }

    fun gotomain(){
        findNavController().navigate(R.id.action_BMI_to_mainActivity)
    }
}
