package com.example.androidlibrary.model

import com.github.javafaker.Faker

typealias BooksServiceListener = (books: List<Book>) -> Unit

class BookService {

    private var books = mutableListOf<Book>()
    val size: Int
        get() = books.size
    private val listeners = mutableSetOf<BooksServiceListener>()

    init {
        val faker = Faker.instance()
        books = (1..50).map { Book(
            author = faker.book().author(),
            title = faker.book().title(),
            description = faker.lorem().sentence(15).toString(),
            image = "https://source.unsplash.com/random/800x600&?$it"
        ) }.sorted().toMutableList()
    }

    fun getBooks(): List<Book> = books

    fun getBook(position: Int) : Book = books[position]

    fun removeBook(book: Book) {
        val indexToDelete = books.indexOfFirst { it == book }
        if (indexToDelete != -1)
            books.removeAt(indexToDelete)
        notifyChanges()
    }

    fun findBookPosition(author: String, name: String) : Int {
        val book = Book( author, name, "", "")
        return books.indexOfFirst { it.compareTo(book) == 0}
    }

    fun addListener(listener: BooksServiceListener) {
        listeners.add(listener)
    }

    fun removeListener(listener: BooksServiceListener) {
        listeners.remove(listener)
    }

    private fun notifyChanges() {
        listeners.forEach {
            it.invoke(books)
        }
    }
}

val booksService = BookService()