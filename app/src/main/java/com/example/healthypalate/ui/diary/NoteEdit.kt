package com.example.healthypalate.ui.diary

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.example.healthypalate.R
import com.example.healthypalate.databinding.FragmentNoteEditBinding
import com.example.healthypalate.ui.HealthPalateViewModel
import com.example.healthypalate.ui.HealthyPalateApplication
import com.example.healthypalate.ui.HealthyPalateViewModelFactory
import com.example.healthypalate.ui.database.NotesTable

class NoteEdit : Fragment() {
private lateinit var binding : FragmentNoteEditBinding

private val navigationArgs: NoteEditArgs by navArgs()

lateinit var note: NotesTable
private val viewMode: HealthPalateViewModel by activityViewModels{
    HealthyPalateViewModelFactory(
        (activity?.application as HealthyPalateApplication).database.palateDao()
    )

}
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View?{
        val frag = FragmentNoteEditBinding.inflate(inflater, container, false)
        binding = frag
        return frag.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.viewModel = viewMode

        val noteId = navigationArgs.noteId
        if (noteId > 0) {
            viewMode.retrieveNote(noteId).observe(this.viewLifecycleOwner) { selectedNote ->
                note = selectedNote
                bind(note)
            }
        }
        else{
            binding.noteSave.setOnClickListener { addNewNote() }
        }

    }

    private fun isEntryValid(): Boolean{
        return viewMode.isNoteFieldsEmpty(
            binding.noteTitleEditText.text.toString(),
            binding.noteTextEditText.text.toString()
        )
    }

    private fun addNewNote(){
        if (isEntryValid()){
            viewMode.addNewNote(
                binding.noteTitleEditText.text.toString(),
                binding.noteTextEditText.text.toString(),
                binding.noteDateTime.text.toString()
            )
            val direction = NoteEditDirections.actionNoteEdit2ToHome2()
            findNavController().navigate(direction)
        }else{
            Toast.makeText(requireContext(), "enter all fields", Toast.LENGTH_SHORT).show()
        }
    }

    private fun bind(noteItem: NotesTable){
        binding.apply {
            noteTitleEditText.setText(noteItem.noteTitle, TextView.BufferType.EDITABLE)
            noteTextEditText.setText(noteItem.noteText, TextView.BufferType.EDITABLE)
            noteDateTime.text = noteItem.dateEdited
        }
    }
}

