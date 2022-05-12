package com.example.kbtu_helper.model.user

import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.android.parcel.Parcelize

@Parcelize
@Entity(tableName = "user")
data class User(
    @PrimaryKey
    val uid: String,
    val email: String,
    val name: String,
    val phone: String,
    val image: String
) : Parcelable
