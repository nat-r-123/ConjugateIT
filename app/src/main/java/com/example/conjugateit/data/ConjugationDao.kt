package com.example.conjugateit.data

import androidx.room.Dao
import androidx.room.Query

@Dao
interface ConjugationDao {
    @Query("SELECT * FROM conjugations WHERE type = :type")
    fun getConjugationByType(type: String): Conjugation?

    fun getConjugationEndings(type: String, tense: String): String? {
        return when (tense) {
            "infinitive" -> getInfinitive(type)
            "present" -> getPresent(type)
            "future" -> getFuture(type)
            "imperfect" -> getImperfect(type)
            "simple_past" -> getSimplePast(type)
            "past_participle" -> getPastParticiple(type)
            "present_participle" -> getPresentParticiple(type)
            "gerund" -> getGerund(type)
            "imperative" -> getImperative(type)
            "conditional" -> getConditional(type)
            "present_subjunctive" -> getPresentSubjunctive(type)
            "imperfect_subjunctive" -> getImperfectSubjunctive(type)
            else -> throw IllegalArgumentException("Invalid column name: $tense")
        }
    }

    @Query("SELECT infinitive FROM conjugations WHERE type = :type")
    fun getInfinitive(type: String): String?

    @Query("SELECT present FROM conjugations WHERE type = :type")
    fun getPresent(type: String): String?

    @Query("SELECT future FROM conjugations WHERE type = :type")
    fun getFuture(type: String): String?

    @Query("SELECT imperfect FROM conjugations WHERE type = :type")
    fun getImperfect(type: String): String?

    @Query("SELECT simple_past FROM conjugations WHERE type = :type")
    fun getSimplePast(type: String): String?

    @Query("SELECT past_participle FROM conjugations WHERE type = :type")
    fun getPastParticiple(type: String): String?

    @Query("SELECT present_participle FROM conjugations WHERE type = :type")
    fun getPresentParticiple(type: String): String?

    @Query("SELECT gerund FROM conjugations WHERE type = :type")
    fun getGerund(type: String): String?

    @Query("SELECT imperative FROM conjugations WHERE type = :type")
    fun getImperative(type: String): String?

    @Query("SELECT conditional FROM conjugations WHERE type = :type")
    fun getConditional(type: String): String?

    @Query("SELECT present_subjunctive FROM conjugations WHERE type = :type")
    fun getPresentSubjunctive(type: String): String?

    @Query("SELECT imperfect_subjunctive FROM conjugations WHERE type = :type")
    fun getImperfectSubjunctive(type: String): String?
}
