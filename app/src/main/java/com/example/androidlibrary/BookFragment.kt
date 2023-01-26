package com.example.androidlibrary

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.androidlibrary.contract.HasCustomTitleToolbar
import com.example.androidlibrary.model.Book
import com.example.androidlibrary.contract.navigator
import com.example.androidlibrary.databinding.FragmentBookBinding

class BookFragment : Fragment(), HasCustomTitleToolbar {

    private lateinit var binding: FragmentBookBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentBookBinding.inflate(inflater, container, false)

        binding.fragmentBookName.text = requireArguments().getString(ARG_TITLE)
        binding.fragmentBookAuthor.text = requireArguments().getString(ARG_AUTHOR)
        binding.fragmentBookDescription.text = requireArguments().getString(ARG_DESCRIPTION)

        binding.closeButton.setOnClickListener { navigator().closeBookFragment() }
        binding.menuButton.setOnClickListener { navigator().backToList() }
        binding.nextBookButton.setOnClickListener { navigator().launchNextBook(
            requireArguments().getString(ARG_AUTHOR)!!,
            requireArguments().getString(ARG_TITLE)!!
        ) }
        return binding.root
    }

    override fun getCustomTitle(): Int = R.string.book

    companion object {
        @JvmStatic
        private val ARG_AUTHOR = "ARG_AUTHOR"
        @JvmStatic
        private val ARG_TITLE = "ARG_NAME"
        @JvmStatic
        private val ARG_DESCRIPTION = "ARG_DESCRIPTION"

        @JvmStatic
        fun newInstance(book: Book) : BookFragment {
            val args = Bundle().apply {
                putString(ARG_AUTHOR, book.author)
                putString(ARG_TITLE, book.title)
                putString(ARG_DESCRIPTION, book.description)
            }
            val fragment = BookFragment()
            fragment.arguments = args
            return fragment
        }

    }
}