package com.example.androidlibrary

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.FragmentManager
import com.example.androidlibrary.adapter.*
import com.example.androidlibrary.contract.Navigator
import com.example.androidlibrary.databinding.ActivityMainBinding

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
}