package com.nanorus.articles.presentation.presenter.articles

import com.nanorus.articles.entity.Article
import com.nanorus.articles.model.domain.articles.ArticlesInteractor
import com.nanorus.articles.presentation.ui.Toaster
import com.nanorus.articles.presentation.view.articles.IArticlesView
import io.reactivex.disposables.Disposable
import moxy.InjectViewState
import moxy.MvpPresenter

@InjectViewState
class ArticlesPresenter : MvpPresenter<IArticlesView>() {
    private val interactor = ArticlesInteractor()
    private var articlesListener: Disposable? = null

    private var articles = mutableListOf<Article>()
    var isTopicsLoading = false

    init {
        getArticles()
    }

    fun onRefresh() {
        if (!isTopicsLoading) {
            isTopicsLoading = true
            viewState.showProgress(true)
            getArticles()
        }
    }

    private fun getArticles() {
        articlesListener = interactor.getArticles().subscribe({ article ->
            articles.add(article)
        }, {
            viewState.showProgress(false)
            isTopicsLoading = false
            Toaster.toast(it.message ?: "Loading articles error")
        }, {
            viewState.showProgress(false)
            isTopicsLoading = false
            viewState.showArticles(articles)
        })
    }
}