package com.example.androidlibrary.adapter

import com.example.androidlibrary.model.Book

interface BookListActionListener {
    fun onBookDelete(book: Book)

    fun onBookDetails(book: Book)
}