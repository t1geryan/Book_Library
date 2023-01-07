package com.example.androidlibrary.adapter

class Book(
    val id: Long,
    val author: String,
    val name: String,
    val description: String
    )
{
    override fun toString(): String {
        return name;
    }
}