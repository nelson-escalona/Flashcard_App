package com.example.codepathflashcardapp

import android.content.Intent
import android.media.Image
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.activity.result.contract.ActivityResultContracts

class MainActivity : AppCompatActivity() {

    lateinit var flashcardDatabase: FlashcardDatabase
    var allFlashcards = mutableListOf<Flashcard>()

    var currCardDisplayIndex = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        flashcardDatabase = FlashcardDatabase(this)
        allFlashcards = flashcardDatabase.getAllCards().toMutableList()

        val flashcardQuestion = findViewById<TextView>(R.id.flashcard_question)
        val flashcardAnswer = findViewById<TextView>(R.id.Answer)

        if (allFlashcards.size > 0) {
            flashcardQuestion.text = allFlashcards[0].question
            flashcardAnswer.text = allFlashcards[0].answer
        }

        flashcardQuestion.setOnClickListener{
            flashcardQuestion.visibility = View.INVISIBLE
            flashcardAnswer.visibility = View.VISIBLE
        }
        flashcardAnswer.setOnClickListener{
            flashcardQuestion.visibility = View.VISIBLE
            flashcardAnswer.visibility = View.INVISIBLE
        }

        val resultLauncher = registerForActivityResult(ActivityResultContracts.StartActivityForResult()) {
                result ->

                val data: Intent? = result.data

                if (data != null) {
                    val questionString = data.getStringExtra("QUESTION_KEY")
                    val answerString = data.getStringExtra("ANSWER_KEY")

                    flashcardQuestion.text = questionString
                    flashcardAnswer.text = answerString

                    if (!questionString.isNullOrEmpty() && !answerString.isNullOrEmpty()) {
                        flashcardDatabase.insertCard(Flashcard(questionString, answerString))
                        allFlashcards = flashcardDatabase.getAllCards().toMutableList()
                    }
                }

        }
        val addquestionbutton = findViewById<ImageView>(R.id.add_question_button).setOnClickListener {
            val intent = Intent(this, Activity_AddCard::class.java)
            resultLauncher.launch(intent)
        }

        val nextButton = findViewById<ImageView>(R.id.next_question_button)
        nextButton.setOnClickListener {
            if (allFlashcards.isEmpty()){
                return@setOnClickListener
            }
            currCardDisplayIndex++

            if (currCardDisplayIndex >= allFlashcards.size) {
                currCardDisplayIndex = 0
            }

            allFlashcards = flashcardDatabase.getAllCards().toMutableList()

            val question = allFlashcards[currCardDisplayIndex].question
            val answer = allFlashcards[currCardDisplayIndex].answer

            flashcardQuestion.text = question
            flashcardAnswer.text = answer
        }
    }
}