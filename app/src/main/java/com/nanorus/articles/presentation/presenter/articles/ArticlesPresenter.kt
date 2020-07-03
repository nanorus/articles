package com.nanorus.articles.presentation.presenter.articles

import com.nanorus.articles.entity.Article
import com.nanorus.articles.entity.ErrorEntity
import com.nanorus.articles.model.domain.articles.ArticlesInteractor
import com.nanorus.articles.presentation.ui.Toaster
import com.nanorus.articles.presentation.view.articles.IArticlesView
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers
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
        getArticles()
    }

    private fun getArticles() {
        if (!isTopicsLoading) {
            isTopicsLoading = true
            viewState.showProgress(true)
            articles.clear()
            articlesListener = interactor.getArticles()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({ article ->
                    articles.add(article)
                }, {
                    viewState.showProgress(false)
                    isTopicsLoading = false
                    when (it) {
                        is ErrorEntity.NoInternetException -> {
                            viewState.showNoInternetError(true)
                        }
                        else -> Toaster.toast(it.message ?: "Loading articles error")
                    }
                }, {
                    viewState.showNoInternetError(false)
                    viewState.showProgress(false)
                    isTopicsLoading = false
                    articles.sortByDescending { it.date }
                    viewState.showArticles(articles)
                })
        }
    }
}