package com.example.androidlibrary.contract

import androidx.annotation.StringRes

interface HasCustomTitleToolbar {
    @StringRes
    fun getTitle() : Int
}