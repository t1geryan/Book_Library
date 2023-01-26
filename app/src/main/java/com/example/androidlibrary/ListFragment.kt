package com.example.androidlibrary

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.androidlibrary.adapter.*
import com.example.androidlibrary.contract.*
import com.example.androidlibrary.databinding.FragmentListviewBinding
import com.example.androidlibrary.model.*
import com.github.javafaker.Faker

class ListFragment : Fragment(),HasCustomTitleToolbar, HasCustomActionsToolbar {

    private lateinit var binding: FragmentListviewBinding
    private lateinit var adapter: BooksAdapter

    private val booksListChangeListener : BooksServiceListener = {
        adapter.books = it
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentListviewBinding.inflate(inflater, container, false)

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        adapter = BooksAdapter(object : BookListActionListener {
            override fun onBookDelete(book: Book) {
                booksService.removeBook(book)
            }

            override fun onBookDetails(book: Book) {
                navigator().launchBookFragment(book)
            }
        })
        
        adapter.books = booksService.getBooks()
        booksService.addListener(booksListChangeListener)

        binding.recyclerView.layoutManager = LinearLayoutManager(requireContext())
        binding.recyclerView.adapter = adapter

        super.onViewCreated(view, savedInstanceState)
    }

    override fun onDestroy() {
        booksService.removeListener(booksListChangeListener)
        super.onDestroy()
    }

    override fun getCustomTitle(): Int = R.string.book_library

    override fun getCustomActionsList():List<CustomAction> {
        val addBookAction = CustomAction(
            icon = R.drawable.ic_add_book,
            text = R.string.add,
            onCustomActionClick = {
                booksService.addBook(0, generateBook(Faker.instance()))
            }
        )
        val clearBookListAction = CustomAction(
            icon = R.drawable.ic_clear,
            text = R.string.clear,
            onCustomActionClick = {
                booksService.removeAllBooks()
            }
        )

        return listOf(addBookAction, clearBookListAction)
    }

}