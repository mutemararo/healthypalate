package com.example.healthypalate.ui.home

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.healthypalate.R
import com.example.healthypalate.databinding.FragmentGenderBindingImpl
import com.example.healthypalate.databinding.FragmentProfileBinding
import com.example.healthypalate.ui.models.User
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.*


class Profile : Fragment() {
    private lateinit var binding : FragmentProfileBinding

    private val user = FirebaseAuth.getInstance().currentUser?.uid
    private val database = FirebaseDatabase.getInstance().getReference("users/$user")
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val frag = FragmentProfileBinding.inflate(inflater, container, false)
        binding = frag
        return frag.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.apply {

        }

        database.addValueEventListener(object: ValueEventListener{
            override fun onDataChange(snapshot: DataSnapshot) {
                val user = snapshot.getValue(User::class.java)

                binding.profileWeight.text = user?.weight ?: "0.0"
                binding.profileHeight.text = user?.height
                binding.profileDob.text = user?.date_of_birth
                binding.profileBmi.text = user?.bmi
            }

            override fun onCancelled(error: DatabaseError) {
                TODO("Not yet implemented")
            }

        })
    }
}