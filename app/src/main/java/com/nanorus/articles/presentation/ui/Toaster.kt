package com.nanorus.articles.presentation.ui

import com.nanorus.articles.app.App
import org.jetbrains.anko.toast

class Toaster {
    companion object {
        fun toast(message: String) {
            App.instance.toast(message)
        }
    }
}