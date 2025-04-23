package com.example.conjugateit.data

import android.content.Context
import com.google.gson.Gson

class VerbRepository(context: Context) {
    private val db: AppDatabase = AppDatabase.getDatabase(context)
    private val verbDao: VerbDao = db.verbDao()
    private val conjugationDao: ConjugationDao = db.conjugationDao()
    private val maxId by lazy { verbDao.getMaxId() }

    private val gson = Gson()

    fun getRandomVerb(): Verb? = verbDao.getVerbById((1..maxId).random())

    fun getConjugationEndings(type: String, tense: String): List<String>? {
        val json = conjugationDao.getConjugationEndings(type, tense)
        return json?.let { gson.fromJson(it, Array<String>::class.java).toList() }
    }
}