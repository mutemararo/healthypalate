package com.example.healthypalate.ui

import androidx.lifecycle.*
import com.example.healthypalate.ui.database.DatabaseDao
import com.example.healthypalate.ui.database.NotesTable
import kotlinx.coroutines.launch
import java.lang.IllegalArgumentException
import java.text.SimpleDateFormat
import java.util.*

class HealthPalateViewModel(private val dao: DatabaseDao) : ViewModel(){

    val allNotes: LiveData<List<NotesTable>> = dao.retrieveAllNotes().asLiveData()
    var formattedDate = getDate()

    private fun insertNote(note: NotesTable){
        viewModelScope.launch {
            dao.addNote(note)
        }
    }

    fun getNoteDetails(
        title: String,
        text: String,
        time: String
    ): NotesTable{
        return NotesTable(
            noteTitle = title,
            noteText = text,
            dateEdited = time
        )
    }

    fun addNewNote(noteTitle: String, noteText: String, time: String){
        val newNote = getNoteDetails(noteTitle, noteText, time)
        insertNote(newNote)
    }

    fun getDate(): String{
        return Calendar.getInstance().time.toString()
    }

    fun isNoteFieldsEmpty(title: String, text: String): Boolean{
        if (title.isBlank() || text.isBlank()){
            return false
        }
        return true
    }

    fun retrieveNote(id:Int): LiveData<NotesTable>{
        return dao.retrieveNote(id).asLiveData()
    }
}

class HealthyPalateViewModelFactory(private val dao: DatabaseDao): ViewModelProvider.Factory{
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        @Suppress("UNCHECKED EXCEPTION")
        if (modelClass.isAssignableFrom(HealthPalateViewModel::class.java)){
            return HealthPalateViewModel(dao) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }

}