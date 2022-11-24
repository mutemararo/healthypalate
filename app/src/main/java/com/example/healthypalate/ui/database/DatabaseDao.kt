package com.example.healthypalate.ui.database

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import kotlinx.coroutines.flow.Flow


@Dao
interface DatabaseDao {

    @Query("SELECT * FROM NotesTable")
    fun retrieveAllNotes(): Flow<List<NotesTable>>

    @Query("SELECT * FROM NotesTable WHERE id = :id")
    fun retrieveNote(id: Int): Flow<NotesTable>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun addNote(note: NotesTable)
}