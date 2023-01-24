package com.example.androidlibrary.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.androidlibrary.R
import com.example.androidlibrary.databinding.ItemBookBinding
import com.example.androidlibrary.model.Book

class BooksAdapter(private val actionListener: BookListActionListener) : RecyclerView.Adapter<BooksAdapter.BooksViewHolder>(), View.OnClickListener {

    var books: List<Book> = emptyList()
        set(value) {
            field = value
            notifyDataSetChanged()
        }

    class BooksViewHolder (val binding: ItemBookBinding) : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BooksViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = ItemBookBinding.inflate(inflater, parent, false)

        binding.root.setOnClickListener(this)
        binding.deleteButton.setOnClickListener(this)

        return BooksViewHolder(binding)
    }

    override fun getItemCount(): Int = books.size

    override fun onBindViewHolder(holder: BooksViewHolder, position: Int) {
        val book = books[position]

        with (holder.binding) {
            holder.itemView.tag = book
            deleteButton.tag = book

            itemBookTitle.text = book.title
            itemBookAuthor.text = book.author
            Glide.with(bookImageView.context)
                .load(book.image)
                .circleCrop()
                .placeholder(R.drawable.ic_book_photo)
                .error(R.drawable.ic_book_photo)
                .into(bookImageView)
        }
    }

    override fun onClick(view: View) {
        val book = view.tag as Book
        when (view.id) {
            R.id.deleteButton -> actionListener.onBookDelete(book)
            else -> actionListener.onBookDetails(book)
        }
    }

}