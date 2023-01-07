package com.example.androidlibrary.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import com.example.androidlibrary.databinding.ItemCustomBinding

class BookAdapter(
    private val books: List<Book>
) : BaseAdapter() {

    override fun getItem(position: Int): Book = books[position]

    override fun getItemId(position: Int): Long = getItem(position).id

    override fun getCount(): Int = books.size

    // 2 параметр специальный для оптимизации отображения элементов списка
    override fun getView(position: Int, converView: View?, parent: ViewGroup): View {
        val binding =
            converView?.tag as ItemCustomBinding? ?:
            createBinding(parent.context)
        val book = getItem(position)
        binding.itemBookAuthor.text = book.author
        binding.itemBookName.text = book.name

        return binding.root
    }

    private fun createBinding(context: Context): ItemCustomBinding {
        val binding = ItemCustomBinding.inflate(LayoutInflater.from(context))
        binding.root.tag = binding
        return binding
    }
}