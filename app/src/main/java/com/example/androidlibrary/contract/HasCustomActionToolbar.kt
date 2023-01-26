package com.example.androidlibrary.contract

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes

interface HasCustomActionsToolbar {
    fun getCustomActionsList() : List<CustomAction>
}

class CustomAction(
    @DrawableRes val icon: Int,
    @StringRes val text: Int,
    val onCustomActionClick: Runnable
)