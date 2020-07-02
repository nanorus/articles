package com.nanorus.articles.entity

import com.nanorus.articles.model.data.utils.DateUtils


class Article(
    val title: String? = null,
    val description: String? = null,
    val image: String? = null,
    val date: Long? = null
) {
    fun getStringDate(): String? {
        return DateUtils.getDate(date, "dd MMM yyyy")
    }
}