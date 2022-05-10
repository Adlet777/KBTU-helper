package com.example.kbtu_helper.subjects

import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.android.parcel.Parcelize

@Parcelize
@Entity(tableName = "subjects")
data class Subject(
    @PrimaryKey(autoGenerate = true)
    val id: Int,
    val name: String,
    val level: Int,
    val tag: String
): Parcelable
