package com.example.healthypalate.ui.diary

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.FragmentNavigator
import androidx.navigation.fragment.findNavController
import com.example.healthypalate.R
import com.example.healthypalate.databinding.FragmentMealsBinding
import com.example.healthypalate.databinding.FragmentNotesListBinding
import com.example.healthypalate.ui.HealthPalateViewModel
import com.example.healthypalate.ui.HealthyPalateApplication
import com.example.healthypalate.ui.HealthyPalateViewModelFactory
import com.example.healthypalate.ui.adapters.NotesListAdapter
import com.google.firebase.database.FirebaseDatabase

class NotesList : Fragment() {
    private lateinit var binding: FragmentNotesListBinding

    val databaseReference = FirebaseDatabase.getInstance().getReference("users/Sha/EMAILAS")

    private val viewModel: HealthPalateViewModel by activityViewModels {
        HealthyPalateViewModelFactory(
            (activity?.application as HealthyPalateApplication).database.palateDao()
        )
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val fragment = FragmentNotesListBinding.inflate(inflater, container, false)
        binding = fragment
        return fragment.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val adapter = NotesListAdapter{
            val direction = NotesListDirections.actionNotesList2ToNoteEdit(it.id)
            findNavController().navigate(direction)
        }

        binding.notesList.adapter = adapter
        viewModel.allNotes.observe(this.viewLifecycleOwner){note ->
            note.let {
                adapter.submitList(it)
            }
        }


    }
}
