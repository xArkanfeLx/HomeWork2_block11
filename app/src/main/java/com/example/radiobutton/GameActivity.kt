package com.example.radiobutton

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.RadioButton
import android.widget.TextView
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import org.w3c.dom.Text
import kotlin.properties.Delegates

class GameActivity : AppCompatActivity() {

    private lateinit var questionTV: TextView
    private lateinit var anser1BTN: RadioButton
    private lateinit var anser2BTN: RadioButton
    private lateinit var anser3BTN: RadioButton

    private lateinit var btns: List<RadioButton>

    private var numQuestion: Int = 1
    private var score: Int = 0
    private var correctAnswer: Int = 0

    private lateinit var questions: List<String>
    private lateinit var answers1: List<String>
    private lateinit var answers2: List<String>
    private lateinit var answers3: List<String>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_game)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        numQuestion = intent.getStringExtra("question").toString().toInt()
        score = intent.getStringExtra("score").toString().toInt()

        questionTV = findViewById(R.id.questionTV)
        anser1BTN = findViewById(R.id.anser1BTN)
        anser2BTN = findViewById(R.id.anser2BTN)
        anser3BTN = findViewById(R.id.anser3BTN)

        initAllValues()

        anser1BTN.setOnClickListener(radioButtonClickListener)
        anser2BTN.setOnClickListener(radioButtonClickListener)
        anser3BTN.setOnClickListener(radioButtonClickListener)
    }

    private val radioButtonClickListener = View.OnClickListener { view ->
        val radioButton = view as RadioButton
        if (radioButton == btns[correctAnswer - 1]) score += 100
        if (numQuestion < 5) {
            numQuestion++
            intent = Intent(this, GameActivity::class.java)
        } else intent = Intent(this, ResultActivity::class.java)
        intent.putExtra("question", numQuestion.toString())
        intent.putExtra("score", score.toString())
        startActivity(intent)
    }

    fun checkCorrectAnswer(rb: RadioButton, text: String, num: Int) {
        val txt = text.split("-Правильный")
        if (txt.size == 2) correctAnswer = num
        rb.text = txt[0]
    }

    fun initAllValues() {
        btns = listOf(anser1BTN, anser2BTN, anser3BTN)
        questions = listOf(
            getString(R.string.question_1),
            getString(R.string.question_2),
            getString(R.string.question_3),
            getString(R.string.question_4),
            getString(R.string.question_5)
        )
        answers1 = listOf(
            getString(R.string.answer_11),
            getString(R.string.answer_21),
            getString(R.string.answer_31),
            getString(R.string.answer_41),
            getString(R.string.answer_51)
        )
        answers2 = listOf(
            getString(R.string.answer_12),
            getString(R.string.answer_22),
            getString(R.string.answer_32),
            getString(R.string.answer_42),
            getString(R.string.answer_52)
        )
        answers3 = listOf(
            getString(R.string.answer_13),
            getString(R.string.answer_23),
            getString(R.string.answer_33),
            getString(R.string.answer_43),
            getString(R.string.answer_53)
        )
        questionTV.text = questions[numQuestion - 1]
        checkCorrectAnswer(anser1BTN, answers1[numQuestion - 1], 1)
        checkCorrectAnswer(anser2BTN, answers2[numQuestion - 1], 2)
        checkCorrectAnswer(anser3BTN, answers3[numQuestion - 1], 3)
    }
}