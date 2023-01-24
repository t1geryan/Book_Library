package com.example.androidlibrary

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.FragmentManager
import com.example.androidlibrary.contract.Navigator
import com.example.androidlibrary.databinding.ActivityMainBinding
import com.example.androidlibrary.model.Book
import com.example.androidlibrary.model.booksService

class MainActivity : AppCompatActivity(), Navigator {
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater).also {setContentView(it.root)}

        if (savedInstanceState == null)
            launchListViewFragment()
    }

    override fun launchListViewFragment() {
        supportFragmentManager
            .beginTransaction()
            .add(R.id.fragmentContainer,ListFragment())
            .commit()
    }

    override fun launchBookFragment(book: Book) {
        supportFragmentManager
            .beginTransaction()
            .addToBackStack(null)
            .replace(R.id.fragmentContainer, BookFragment.newInstance(book))
            .commit()
    }

    override fun backToList() {
        supportFragmentManager.popBackStack(null, FragmentManager.POP_BACK_STACK_INCLUSIVE)
    }

    override fun launchNextBook(author: String, title: String) {
        val currentID = booksService.findBookPosition(author,title)
        if (currentID != -1) {
            val nextIndex =
                if (currentID < booksService.size - 1)
                    currentID + 1
                else
                    0
            launchBookFragment(booksService.getBook(nextIndex))
        }
    }

    override fun closeBookFragment() {
        onBackPressed()
    }

}