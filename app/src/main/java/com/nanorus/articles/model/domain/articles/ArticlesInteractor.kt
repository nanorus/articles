package com.nanorus.articles.model.domain.articles

import com.nanorus.articles.model.repository.ArticlesRepository

class ArticlesInteractor {
    private val repo = ArticlesRepository()

    fun getArticles() = repo.getArticles()
}