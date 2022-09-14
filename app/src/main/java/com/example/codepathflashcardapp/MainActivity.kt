package com.example.codepathflashcardapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.TextView

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val Question = findViewById<TextView>(R.id.flashcard_question)
        val Answer = findViewById<TextView>(R.id.Answer)
        Question.setOnClickListener{
            Question.visibility = View.INVISIBLE
            Answer.visibility = View.VISIBLE
        }
        Answer.setOnClickListener{
            Question.visibility = View.VISIBLE
            Answer.visibility = View.INVISIBLE
        }
    }
}