package com.example.codepathflashcardapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.activity.result.contract.ActivityResultContracts

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

        val resultLauncher = registerForActivityResult(ActivityResultContracts.StartActivityForResult()) {
                result ->

                val data: Intent? = result.data

                if (data != null) {
                    val questionString = data.getStringExtra("Question_Key")
                    val answerString = data.getStringExtra("Answer_Key")

                    Question.text = questionString
                    Answer.text = answerString
                }

        }
        val addquestionbutton = findViewById<ImageView>(R.id.add_question_button).setOnClickListener {
            val intent = Intent(this, Activity_AddCard::class.java)
            resultLauncher.launch(intent)
        }
    }
}