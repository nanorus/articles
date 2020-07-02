package com.nanorus.articles.presentation.view.articles

import com.nanorus.articles.entity.Article
import moxy.MvpView

interface IArticlesView : MvpView {
    fun showArticles(articles: MutableList<Article>)

    fun showProgress(show: Boolean)
}