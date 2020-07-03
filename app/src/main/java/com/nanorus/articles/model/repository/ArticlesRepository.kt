package com.nanorus.articles.model.repository

import com.nanorus.articles.entity.Article
import com.nanorus.articles.entity.ErrorEntity
import com.nanorus.articles.model.data.FireStoreManager
import com.nanorus.articles.model.data.utils.InternetChecker
import io.reactivex.Observable
import io.reactivex.Single

class ArticlesRepository {
    private val fireStoreManager = FireStoreManager()

    fun getArticles(): Observable<Article> {
        return getInternetCheckingSingle().flatMapObservable { fireStoreManager.getArticles() }
    }

    private fun getInternetCheckingSingle(): Single<Boolean> {
        return Single.create<Boolean> { emitter ->
            if (InternetChecker.hasInternetAccess()) {
                emitter.onSuccess(true)
            } else {
                emitter.onError(ErrorEntity.NoInternetException())
            }
        }
    }
}
