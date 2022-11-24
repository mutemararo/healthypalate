package com.example.healthypalate.ui.diary

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.healthypalate.R
import com.example.healthypalate.databinding.FragmentMyDiaryBinding
import com.example.healthypalate.ui.adapters.DiaryPagerAdapter


class MyDiary : Fragment() {

    private lateinit var binding: FragmentMyDiaryBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val fragment = FragmentMyDiaryBinding.inflate(inflater, container, false)
        binding = fragment
        return fragment.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.apply {
            var pagerAdapter = DiaryPagerAdapter(requireActivity().supportFragmentManager, )
            val meals = FragmentMeals()
            val notes = NotesList()
            val metrics = Metrics()
            pagerAdapter.addFragment(meals)
            pagerAdapter.addFragment(notes)
            pagerAdapter.addFragment(metrics)
            diaryPager.adapter = pagerAdapter

            tabLayout.setupWithViewPager(diaryPager)
            tabLayout.getTabAt(0)?.setText("Meals")
            tabLayout.getTabAt(1)?.setText("Notes")
            tabLayout.getTabAt(2)?.setText("Metrics")
        }
    }


}