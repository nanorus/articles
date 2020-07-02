package com.nanorus.articles.presentation.view.articles

import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import com.nanorus.articles.R
import com.nanorus.articles.entity.Article
import com.nanorus.articles.presentation.presenter.articles.ArticlesPresenter
import com.nanorus.articles.presentation.ui.articles.adapter.ArticlesAdapter
import kotlinx.android.synthetic.main.activity_articles.*
import moxy.MvpAppCompatActivity
import moxy.presenter.InjectPresenter
import org.jetbrains.anko.support.v4.onRefresh
import org.jetbrains.anko.toast
import org.jetbrains.anko.toolbar

class ArticlesActivity : MvpAppCompatActivity(), IArticlesView {

    @InjectPresenter
    lateinit var presenter: ArticlesPresenter
    lateinit var articlesAdapter: ArticlesAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_articles)
        initUi()
    }

    private fun initUi() {
        articlesAdapter = ArticlesAdapter()
        articlesList.apply {
            this.layoutManager = LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
            this.adapter = articlesAdapter
        }
        swipe.onRefresh {
            presenter.onRefresh()
        }
    }

    override fun showArticles(articles: MutableList<Article>) {
        articlesAdapter.updateData(articles)
    }

    override fun showProgress(show: Boolean) {
        toast(show.toString())
        swipe.post { swipe.isRefreshing = show }
    }
}