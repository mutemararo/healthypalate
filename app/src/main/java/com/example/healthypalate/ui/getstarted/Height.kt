package com.example.healthypalate.ui.getstarted

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.navigation.fragment.findNavController
import com.example.healthypalate.R
import com.example.healthypalate.databinding.FragmentHeightBinding
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.ktx.Firebase


class Height : Fragment() {

    private lateinit var binding: FragmentHeightBinding

    private val user = FirebaseAuth.getInstance().currentUser?.uid
    private val databaseRef = FirebaseDatabase.getInstance().getReference("users/$user/height")
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val fragment = FragmentHeightBinding.inflate(inflater, container, false)
        binding = fragment
        return fragment.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.apply {
            heightFrag = this@Height
        }
    }

    fun gotoWeight(){

        if (binding.heightInput.text?.toString()!!.isNotBlank()){
            databaseRef.setValue(binding.heightInput.text.toString().trim())
            findNavController().navigate(R.id.action_height_to_weight)
        }else{
            Toast.makeText(requireActivity(), "Enter height", Toast.LENGTH_SHORT).show()
        }

    }

    fun backDOB(){
        findNavController().navigate(R.id.action_height_to_dateOfBirth)
    }

    companion object{
        var height = 0.0f
    }
}

