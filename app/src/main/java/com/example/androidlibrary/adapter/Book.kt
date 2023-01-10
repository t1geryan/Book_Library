package com.example.androidlibrary.adapter

class Book(
    val author: String,
    val name: String,
    val description: String,
    var id: Long = 0
    ) : Comparable<Book>
{
    override fun compareTo(other: Book): Int {
        return if ( (author < other.author) or ( (author == other.author) and (name < other.name)) )
                -1
            else if (author == other.author && name == other.name)
                0
            else
                1
    }

}