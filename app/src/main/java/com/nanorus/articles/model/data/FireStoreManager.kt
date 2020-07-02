package com.nanorus.articles.model.data

import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.Query
import com.nanorus.articles.entity.Article
import durdinapps.rxfirebase2.RxFirebaseDatabase
import io.reactivex.Maybe
import io.reactivex.Observable
import io.reactivex.rxkotlin.toObservable
import org.jetbrains.anko.AnkoLogger

class FireStoreManager : AnkoLogger {
    private var database: DatabaseReference = FirebaseDatabase.getInstance().reference

    fun getArticles(): Observable<Article> {
        return getDatabaseEntries(articlesQuery())
            .flatMapObservable { snapshot ->
                snapshot.children.toObservable()
                    .map { tag -> tag.getValue(Article::class.java) }
            }
    }

    private fun articlesQuery(): Query {
        return database.child(DB_PATH_ARTICLES).orderByChild(DB_PATH_DATE)
    }

    private fun getDatabaseEntries(query: Query): Maybe<DataSnapshot> {
        return RxFirebaseDatabase.observeSingleValueEvent(query)
    }

    companion object {
        const val DB_PATH_ARTICLES = "articles"
        const val DB_PATH_DATE = "date"
    }
}