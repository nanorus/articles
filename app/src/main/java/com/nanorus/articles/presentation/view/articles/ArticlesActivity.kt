package com.nanorus.articles.presentation.view.articles

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.nanorus.articles.R
import com.nanorus.articles.entity.Article
import com.nanorus.articles.presentation.presenter.articles.ArticlesPresenter
import moxy.MvpAppCompatActivity
import moxy.presenter.InjectPresenter
import org.jetbrains.anko.toast

class ArticlesActivity : MvpAppCompatActivity(), IArticlesView {

    @InjectPresenter
    lateinit var presenter: ArticlesPresenter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

    override fun showArticles(articles: MutableList<Article>) {

    }

    override fun showProgress(show: Boolean) {

    }
}