package com.example.conjugateit.ui

import android.os.Bundle
import android.view.KeyEvent
import android.view.inputmethod.EditorInfo
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.example.conjugateit.R
import com.example.conjugateit.data.VerbRepository


class QuizActivity : AppCompatActivity() {

    private lateinit var viewModel: QuizViewModel

    private lateinit var textViewVerbName: TextView
    private lateinit var editTextVerbHint: EditText
    private lateinit var buttonNext: Button

    private lateinit var resultPopup: ResultPopup

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_quiz)

        val tense = intent.getStringExtra("TENSE") ?: "infinitive"
        val title = intent.getStringExtra("NAME") ?: ""
        setTitle(title)

        textViewVerbName = findViewById(R.id.verbName)
        editTextVerbHint = findViewById(R.id.verbHint)
        buttonNext = findViewById(R.id.buttonNext)

        editTextVerbHint.requestFocus()
        resultPopup = ResultPopup(this)

        val repository = VerbRepository(applicationContext)
        viewModel = ViewModelProvider(
            this,
            QuizViewModelFactory(repository, tense)
        )[QuizViewModel::class.java]

        observeViewModel()
        if (savedInstanceState == null) {
            viewModel.loadNewQuestion()
        }

        buttonNext.setOnClickListener {
            checkAnswer()
            viewModel.loadNewQuestion()
        }

        editTextVerbHint.setOnEditorActionListener { v, actionId, event ->
            val isSoftKeyboardDone = actionId == EditorInfo.IME_ACTION_DONE && event == null
            if (isSoftKeyboardDone) {
                checkAnswer()
                viewModel.loadNewQuestion()
                true
            } else {
                false
            }
        }
        editTextVerbHint.setOnKeyListener { v, keyCode, event ->
            val isPhysicalEnterKey = event.action == KeyEvent.ACTION_UP && keyCode == KeyEvent.KEYCODE_ENTER
            if (isPhysicalEnterKey) {
                checkAnswer()
                viewModel.loadNewQuestion()
                true
            } else {
                false
            }
        }
    }

    private fun observeViewModel() {
        viewModel.currentQuestion.observe(this) { question ->
            textViewVerbName.text = question.infinitive
            editTextVerbHint.setText(question.hint)
            editTextVerbHint.setSelection(question.hint.length)
        }
    }

    private fun checkAnswer() {
        val question = viewModel.currentQuestion.value ?: return
        val userAnswer = editTextVerbHint.text.toString()
        val correctAnswer = "${question.hint}${question.ending}"

        resultPopup.show(findViewById(R.id.linearLayout), userAnswer != question.hint, userAnswer == correctAnswer, correctAnswer)
    }
}