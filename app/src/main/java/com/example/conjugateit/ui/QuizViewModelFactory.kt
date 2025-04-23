package com.example.conjugateit.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.conjugateit.data.VerbRepository

class QuizViewModelFactory(
    private val repository: VerbRepository,
    private val tense: String,
) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return QuizViewModel(repository, tense) as T
    }
}