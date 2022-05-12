package com.example.kbtu_helper.model.stats

import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.android.parcel.Parcelize

@Parcelize
@Entity(tableName = "tag_stat")
data class TagStat(
    @PrimaryKey(autoGenerate = true)
    val uid: Int,
    val tag: String,
    val level: String
) : Parcelable
