package com.example.conjugateit.ui

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.conjugateit.data.Verb
import com.example.conjugateit.data.VerbRepository

class QuizViewModel(
    private val repository: VerbRepository,
    private val tense: String,
) : ViewModel() {

    private val pronouns = listOf("io", "tu", "lui/lei", "noi", "voi", "loro")
    private val pronounsImp = listOf("(tu)", "(Lei)", "(noi)", "(voi)", "(Loro)")

    private val _currentQuestion = MutableLiveData<QuestionData>()
    val currentQuestion: LiveData<QuestionData> = _currentQuestion

    fun loadNewQuestion() {
        var question: QuestionData? = null
        while (question == null) {
            val verb = repository.getRandomVerb() ?: continue
            val endings = repository.getConjugationEndings(verb.type, tense) ?: continue
            question = generateQuestion(verb, endings)
        }
        _currentQuestion.value = question
    }
    private fun generateQuestion(verb: Verb, endings: List<String>): QuestionData? {
        val root = verb.verb.dropLast(verb.type.split(":")[1].length)
        val randomIndex = (0 until endings.size).random()

        return when (endings.size) {
            1, 4 -> QuestionData(verb.verb, root, endings[0])
            5 -> QuestionData(verb.verb, "${pronounsImp[randomIndex]} $root", endings[randomIndex])
            6 -> QuestionData(verb.verb, "${pronouns[randomIndex]} $root", endings[randomIndex])
            else -> null
        }
    }
}