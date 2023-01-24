package com.example.androidlibrary

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.androidlibrary.adapter.*
import com.example.androidlibrary.contract.navigator
import com.example.androidlibrary.databinding.FragmentListviewBinding
import com.example.androidlibrary.model.*

class ListFragment : Fragment() {

    private lateinit var binding: FragmentListviewBinding
    private lateinit var adapter: BooksAdapter

    private val booksListChangeListener : BooksServiceListener = {
        adapter.books = it
        Toast.makeText(requireContext(), "List is changed", Toast.LENGTH_SHORT).show()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

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
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentListviewBinding.inflate(inflater, container, false)

        binding.recyclerView.layoutManager = LinearLayoutManager(requireContext())
        binding.recyclerView.adapter = adapter

        return binding.root
    }

    override fun onDestroy() {
        booksService.removeListener(booksListChangeListener)
        super.onDestroy()
    }

}