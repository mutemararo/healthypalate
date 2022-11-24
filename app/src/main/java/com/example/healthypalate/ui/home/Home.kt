package com.example.healthypalate.ui.home

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.example.healthypalate.R
import com.example.healthypalate.databinding.FragmentHomeBinding
import com.example.healthypalate.ui.adapters.DailyMealAdapter
import com.example.healthypalate.ui.models.daily_meal_list


class Home : Fragment() {
    private  var binding: FragmentHomeBinding? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {

        }
    }
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val homeFrag = FragmentHomeBinding.inflate(inflater, container, false)
        binding = homeFrag
        return homeFrag.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding?.apply {

            var adapter = DailyMealAdapter(daily_meal_list)
            todayMenuList.adapter = adapter
            addNote.setOnClickListener{ this@Home.gotoNotes() }
            gotoMetrics.setOnClickListener { this@Home.gotoMetrics()  }
        }
    }

    private fun gotoNotes() {
        findNavController().navigate(R.id.action_home2_to_noteEdit2)
    }

    private fun gotoMetrics() {
        findNavController().navigate(R.id.action_home2_to_metrics)

    }

}