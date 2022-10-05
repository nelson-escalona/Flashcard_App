package com.example.codepathflashcardapp

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.example.codepathflashcardapp.Flashcard

@Dao
interface FlashcardDao {
    @Query("SELECT * FROM flashcard")
    fun getAll(): List<Flashcard>

    @Insert
    fun insertAll(vararg flashcards: Flashcard)

    @Delete
    fun delete(flashcard: Flashcard)

    @Update(onConflict = OnConflictStrategy.REPLACE)
    fun update(flashcard: Flashcard)
}
