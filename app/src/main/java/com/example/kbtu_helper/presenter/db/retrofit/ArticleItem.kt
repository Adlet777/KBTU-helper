package com.example.kbtu_helper.presenter.db.retrofit

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class ArticleItem(
    val id: Int,
    val image: String,
    val tag: String,
    val text: String,
    val title: String
) : Parcelable