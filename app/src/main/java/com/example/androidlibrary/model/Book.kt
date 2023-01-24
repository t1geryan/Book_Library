package com.example.androidlibrary.model

data class Book(
    val author: String,
    val title: String,
    val description: String,
    val image: String
    ) : Comparable<Book>
{
    override fun compareTo(other: Book): Int {
        return if ( (author < other.author) or ( (author == other.author) and (title < other.title)) )
                -1
            else if (author == other.author && title == other.title)
                0
            else
                1
    }

}