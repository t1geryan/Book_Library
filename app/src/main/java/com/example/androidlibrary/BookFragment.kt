package com.example.androidlibrary

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.androidlibrary.adapter.Book
import com.example.androidlibrary.contract.navigator
import com.example.androidlibrary.databinding.FragmentBookBinding

class BookFragment : Fragment() {

    private lateinit var binding: FragmentBookBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentBookBinding.inflate(inflater, container, false)

        binding.fragmentBookName.text = requireArguments().getString(ARG_NAME)
        binding.fragmentBookAuthor.text = requireArguments().getString(ARG_AUTHOR)
        binding.fragmentBookDescription.text = requireArguments().getString(ARG_DESCRIPTION)

        binding.closeButton.setOnClickListener { navigator().backToList() }
        return binding.root
    }

    companion object {
        @JvmStatic
        private val ARG_ID = "ARG_ID"
        @JvmStatic
        private val ARG_AUTHOR = "ARG_AUTHOR"
        @JvmStatic
        private val ARG_NAME = "ARG_NAME"
        @JvmStatic
        private val ARG_DESCRIPTION = "ARG_DESCRIPTION"

        @JvmStatic
        fun newInstance(book: Book) : BookFragment {
            val args = Bundle().apply {
                putLong(ARG_ID, book.id)
                putString(ARG_AUTHOR, book.author)
                putString(ARG_NAME, book.name)
                putString(ARG_DESCRIPTION, book.description)
            }
            val fragment = BookFragment()
            fragment.arguments = args
            return fragment
        }

    }
}