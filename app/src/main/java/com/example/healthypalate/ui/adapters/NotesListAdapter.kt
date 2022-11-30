package com.example.healthypalate.ui.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.healthypalate.databinding.NoteItemBinding
import com.example.healthypalate.ui.database.NotesTable

class NotesListAdapter(val onItemClick: (NotesTable) -> Unit) : ListAdapter<NotesTable, NotesListAdapter.NotesListViewHolder>(DiffCallBack){

    class NotesListViewHolder(private val binding: NoteItemBinding): RecyclerView.ViewHolder(binding.root){
        fun bind(note: NotesTable){
            binding.noteTitle.text = note.noteTitle
            binding.noteTextPreview.text = note.noteText
            binding.noteDateCreation.text = note.dateEdited
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NotesListViewHolder {
        val view = NoteItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return NotesListViewHolder(view)
    }

    override fun onBindViewHolder(holder: NotesListViewHolder, position: Int) {
        val currentNote = getItem(position) as NotesTable
        holder.itemView.setOnClickListener {
            onItemClick(currentNote)
        }
        holder.bind(currentNote)
    }

    companion object{
        private val DiffCallBack = object : DiffUtil.ItemCallback<NotesTable>(){
            override fun areItemsTheSame(oldItem: NotesTable, newItem: NotesTable): Boolean {
                return oldItem == newItem
            }

            override fun areContentsTheSame(oldItem: NotesTable, newItem: NotesTable): Boolean {
                return oldItem.id == newItem.id
                        && oldItem.noteTitle == newItem.noteTitle
                        && oldItem.noteText == newItem.noteText
            }

        }
    }
}