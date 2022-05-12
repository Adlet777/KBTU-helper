package com.example.kbtu_helper.presenter.db.retrofit

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path

interface ApiRequests {

    @GET("/articles")
    fun getArticles(): Call<List<ArticleItem>>

    @GET("/articles/{tag}")
    fun getArticlesByTag(@Path("tag") tag: String): Call<List<ArticleItem>>
}