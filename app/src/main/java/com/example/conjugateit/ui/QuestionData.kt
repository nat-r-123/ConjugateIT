package com.example.conjugateit.ui

import android.os.Parcel
import android.os.Parcelable

data class QuestionData(
    val infinitive: String,
    val hint: String,
    val ending: String
) : Parcelable

{
    override fun writeToParcel(dest: Parcel, flags: Int) {
        dest.writeString(infinitive)
        dest.writeString(hint)
        dest.writeString(ending)
    }

    override fun describeContents() = 0

    companion object CREATOR : Parcelable.Creator<QuestionData> {
        override fun createFromParcel(parcel: Parcel): QuestionData {
            return QuestionData(
                parcel.readString()!!,
                parcel.readString()!!,
                parcel.readString()!!
            )
        }
        override fun newArray(size: Int): Array<QuestionData?> = arrayOfNulls(size)
    }
}