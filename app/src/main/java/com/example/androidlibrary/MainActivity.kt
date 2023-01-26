package com.example.androidlibrary

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import android.view.View
import androidx.core.content.ContextCompat
import androidx.core.graphics.drawable.DrawableCompat
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import com.example.androidlibrary.contract.*
import com.example.androidlibrary.databinding.ActivityMainBinding
import com.example.androidlibrary.model.*


class MainActivity : AppCompatActivity(), Navigator {
    private lateinit var binding: ActivityMainBinding

    private val currentFragment: Fragment
        get() = supportFragmentManager.findFragmentById(R.id.fragmentContainer)!!

    private val fragmentLifecycleListener = object: FragmentManager.FragmentLifecycleCallbacks() {
        override fun onFragmentViewCreated(fm: FragmentManager, f: Fragment, v: View, savedInstanceState: Bundle?) {
            super.onFragmentViewCreated(fm, f, v, savedInstanceState)
            updateTaskbar()
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater).also {setContentView(it.root)}

        if (savedInstanceState == null)
            launchListViewFragment()

        supportFragmentManager.registerFragmentLifecycleCallbacks(fragmentLifecycleListener, false)
    }

    override fun onDestroy() {
        super.onDestroy()
        supportFragmentManager.unregisterFragmentLifecycleCallbacks(fragmentLifecycleListener)
    }
    // Custom Taskbar Support

    private fun updateTaskbar() {
        val fragment = currentFragment
        binding.toolbar.setTitle((fragment as? HasCustomTitleToolbar)?.getTitle() ?: R.string.app_name)
        if (fragment is HasCustomActionsToolbar) {
            fragment.getCustomActionsList().forEach {
                createCustomToolbarAction(it)
            }
        }
        else
            binding.toolbar.menu.clear()
    }

    private fun createCustomToolbarAction(action: CustomAction) {
        val iconDrawable = DrawableCompat.wrap(ContextCompat.getDrawable(this, action.icon)!!)
        iconDrawable.setTint(resources.getColor(R.color.icon_gray, theme))

        binding.toolbar.menu.add(action.text)
        val menuItem = binding.toolbar.menu.add(action.text)
        menuItem.setShowAsAction(MenuItem.SHOW_AS_ACTION_ALWAYS)
        menuItem.icon = iconDrawable
        menuItem.setOnMenuItemClickListener {
            action.onCustomActionClick.run()
            true
        }
    }
    // Navigation
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