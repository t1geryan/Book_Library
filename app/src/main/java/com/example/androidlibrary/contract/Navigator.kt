package com.example.androidlibrary.contract

import androidx.fragment.app.Fragment
import com.example.androidlibrary.adapter.Book

fun Fragment.navigator() : Navigator = requireActivity() as Navigator

interface Navigator {
    fun launchListViewFragment()

    fun launchBookFragment(book: Book)

    fun backToList()

}