package com.example.healthypalate.ui.diary

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.healthypalate.R
import com.example.healthypalate.databinding.FragmentMealsBinding


class FragmentMeals : Fragment() {
private lateinit var binding : FragmentMealsBinding
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val frag = FragmentMealsBinding.inflate(inflater, container, false)
        binding = frag
        return frag.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.apply {

        }
    }
}