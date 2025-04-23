package com.example.conjugateit.data

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "conjugations")
data class Conjugation(
    @PrimaryKey
    @ColumnInfo(name = "type")
    val type: String,

    @ColumnInfo(name = "infinitive")
    val infinitive: String?,

    @ColumnInfo(name = "present")
    val present: String?,

    @ColumnInfo(name = "future")
    val future: String?,

    @ColumnInfo(name = "imperfect")
    val imperfect: String?,

    @ColumnInfo(name = "simple_past")
    val simplePast: String?,

    @ColumnInfo(name = "past_participle")
    val pastParticiple: String?,

    @ColumnInfo(name = "present_participle")
    val presentParticiple: String?,

    @ColumnInfo(name = "gerund")
    val gerund: String?,

    @ColumnInfo(name = "imperative")
    val imperative: String?,

    @ColumnInfo(name = "conditional")
    val conditional: String?,

    @ColumnInfo(name = "present_subjunctive")
    val presentSubjunctive: String?,

    @ColumnInfo(name = "imperfect_subjunctive")
    val imperfectSubjunctive: String?
)