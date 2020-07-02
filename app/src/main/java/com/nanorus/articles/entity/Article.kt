package com.nanorus.articles.entity

class Article(
    val title: String? = null,
    val description: String? = null,
    val image: String? = null,
    val date: Long? = null
) {
    fun getStringDate(): String {
        return date.toString()
    }
}