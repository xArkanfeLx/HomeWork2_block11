package com.example.radiobutton

import android.annotation.SuppressLint
import android.os.Bundle
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class ResultActivity : AppCompatActivity() {

    private lateinit var resultScoreTV:TextView
    private lateinit var resultTV:TextView

    private lateinit var resultTexts :List<String>

    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_result)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        resultTexts = listOf(getString(R.string.result_1),getString(R.string.result_2),getString(R.string.result_3),getString(R.string.result_4),getString(R.string.result_5))

        resultScoreTV = findViewById(R.id.resultScoreTV)
        resultTV = findViewById(R.id.resultTV)

        var score = intent.getStringExtra("score").toString().toInt()

        resultScoreTV.text = "Набрано $score баллов"
        score = score/100-1
        if(score<0) score=0
        resultTV.text = resultTexts[score]
    }
}