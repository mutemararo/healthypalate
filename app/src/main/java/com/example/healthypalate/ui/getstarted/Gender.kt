package com.example.healthypalate.ui.getstarted

import android.graphics.drawable.GradientDrawable
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.navigation.fragment.findNavController
import com.example.healthypalate.R
import com.example.healthypalate.databinding.FragmentGenderBinding
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.FirebaseDatabase


class Gender : Fragment() {


    private lateinit var binding: FragmentGenderBinding

    var isFemale: Boolean = true

    private val userId = FirebaseAuth.getInstance().currentUser?.uid
    private val genderRef = FirebaseDatabase.getInstance().getReference("users/$userId/gender")

    override fun onCreateView(
        inflater: LayoutInflater,
         container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val fragment = FragmentGenderBinding.inflate(inflater, container, false)
        binding = fragment
        return fragment.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.apply {
            btnMale.setOnClickListener {
                isFemale = false
                genderRef.setValue("Male")
                val back = it.background as GradientDrawable
                back.setStroke(5, resources.getColor(R.color.brand_bright))

                val femaleBack = binding.btnFemale.background as GradientDrawable
                femaleBack.setStroke(5, resources.getColor(R.color.grey))

                Toast.makeText(requireActivity(), "Male", Toast.LENGTH_LONG).show()
            }
            btnFemale.setOnClickListener {
                isFemale = true
                genderRef.setValue("Female")
                val back = it.background as GradientDrawable
                back.setStroke(5, resources.getColor(R.color.brand_bright))
                val maleBack = binding.btnMale.background as GradientDrawable
                maleBack.setStroke(5, resources.getColor(R.color.grey))

                Toast.makeText(requireActivity(), "Female", Toast.LENGTH_SHORT).show()

            }
            nextDob.setOnClickListener {

            }
            genderFrag = this@Gender
        }
    }

    fun gotoDob(){
        findNavController().navigate(R.id.action_gender_to_dateOfBirth)
    }

    fun backGender(){
        requireActivity().finish()
    }

}