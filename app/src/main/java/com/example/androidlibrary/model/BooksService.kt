package com.example.androidlibrary.model

import com.github.javafaker.Faker
import kotlin.random.Random

fun generateBook(faker: Faker) : Book
{
    return Book(
        author = faker.book().author(),
        title = faker.book().title(),
        description = faker.lorem().sentence(15).toString(),
        image = "https://source.unsplash.com/random/800x600&?${Random.nextInt(50)}"
    )
}

typealias BooksServiceListener = (books: List<Book>) -> Unit

class BookService {

    private var books = mutableListOf<Book>()
    val size: Int
        get() = books.size
    private val listeners = mutableSetOf<BooksServiceListener>()

    init {
        val faker = Faker.instance()
        books = (1..25).map { generateBook(faker) }.sorted().toMutableList()
    }

    fun getBooks(): List<Book> = books

    fun getBook(position: Int) : Book = books[position]

    fun addBook(index: Int, book: Book) {
        books.add(index, book)
        notifyChanges()
    }

    fun removeBook(book: Book) {
        val indexToDelete = books.indexOfFirst { it.compareTo(book) == 0 }
        if (indexToDelete != -1)
            books.removeAt(indexToDelete)
        notifyChanges()
    }

    fun removeBook(author: String, name: String) {
        removeBook(Book(author, name, "", ""))
    }

    fun removeAllBooks() {
        books.clear()
        notifyChanges()
    }

    fun findBookPosition(book: Book) : Int {
        return books.indexOfFirst { it.compareTo(book) == 0}
    }

    fun findBookPosition(author: String, name: String) : Int {
        return findBookPosition(Book(author, name, "", ""))
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