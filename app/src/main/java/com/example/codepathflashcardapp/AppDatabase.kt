package com.example.codepathflashcardapp

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.codepathflashcardapp.Flashcard
import com.example.codepathflashcardapp.FlashcardDao

@Database(entities = [Flashcard::class], version = 1)

abstract class AppDatabase : RoomDatabase() {
    abstract fun flashcardDao(): FlashcardDao
}
