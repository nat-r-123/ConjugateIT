package com.example.conjugateit.data

import androidx.room.Dao
import androidx.room.Query

@Dao
interface VerbDao {
    @Query("SELECT * FROM verbs")
    fun getAllVerbs(): List<Verb>

    @Query("SELECT MAX(id) FROM verbs")
    fun getMaxId(): Int

    @Query("SELECT * FROM verbs WHERE id = :id")
    fun getVerbById(id: Int): Verb

    @Query("SELECT * FROM verbs WHERE verb = :verb")
    fun getVerbByName(verb: String): Verb
}