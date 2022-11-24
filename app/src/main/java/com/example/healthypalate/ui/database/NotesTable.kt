package com.example.healthypalate.ui.database

import androidx.annotation.NonNull
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class NotesTable(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    @ColumnInfo(name = "note_title") val noteTitle: String,
    @ColumnInfo(name = "note_text") val noteText: String,
    @ColumnInfo(name = "date_edited") val dateEdited: String
)