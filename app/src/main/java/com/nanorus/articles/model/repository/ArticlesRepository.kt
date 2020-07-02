package com.nanorus.articles.model.repository

import com.nanorus.articles.entity.Article
import com.nanorus.articles.model.data.FireStoreManager
import io.reactivex.Observable

class ArticlesRepository {
    private val fireStoreManager = FireStoreManager()

    fun getArticles() = fireStoreManager.getArticles()
}
