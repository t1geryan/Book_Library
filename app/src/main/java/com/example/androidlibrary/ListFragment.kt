package com.example.androidlibrary

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.androidlibrary.adapter.Book
import com.example.androidlibrary.adapter.BookAdapter
import com.example.androidlibrary.adapter.bookData
import com.example.androidlibrary.contract.navigator
import com.example.androidlibrary.databinding.FragmentListviewBinding

class ListFragment : Fragment() {

    private lateinit var binding: FragmentListviewBinding
    private lateinit var adapter: BookAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        adapter = BookAdapter(bookData)

    }
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentListviewBinding.inflate(inflater, container, false)
        binding.bookList.adapter = adapter

        binding.bookList.setOnItemClickListener { parent, _, position, _ ->
            navigator().launchBookFragment(parent.getItemAtPosition(position) as Book)
        }

        return binding.root
    }

}