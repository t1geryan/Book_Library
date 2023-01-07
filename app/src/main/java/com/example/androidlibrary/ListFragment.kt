package com.example.androidlibrary

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.androidlibrary.adapter.Book
import com.example.androidlibrary.adapter.BookAdapter
import com.example.androidlibrary.contract.navigator
import com.example.androidlibrary.databinding.FragmentListviewBinding

class ListFragment : Fragment() {

    private lateinit var binding: FragmentListviewBinding
    private lateinit var adapter: BookAdapter

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentListviewBinding.inflate(inflater, container, false)

        setupBookListWithBaseAdapter()
        return binding.root
    }


    private fun setupBookListWithBaseAdapter() {
        val data = listOf(
            Book(1,"Джоан Роулинг", "Гарри Поттер и Философский Камень", "История о мальчике, который выжил"),
            Book(2, "Майк Омер", "Внутри Убийцы", "История о профайлере"),
            Book(3, "Артур Конан Дойл","Приключения Шерлока Холмса и доктора Ватсона","История о великом детективе"),
            Book(4, "Джеймс Оруэлл", "1984", "История о жизни без надежды"),
            Book(5, "Грегори Макдональд", "Флетч", "История о наглом репортёре"),
            Book(6, "Майк Омер", "Заживо в темноте", "Продолжение истории о профайлере"),
            Book(7, "Фрэнсис Скотт Фицджеральд", "Великий Гэстби", "Истоия о миллиардере-однолюбе"),
            Book(8,"Джоан Роулинг", "Гарри Поттер и Тайная Комната", "История о мальчике, который выжил"),
            Book(9, "Лев Николаевич Толстой", "Война и Мир", "История о том, как война меняет жизни"),
            Book(10, "Фёдор Михайлович Достоевский", "Преступление и Наказание", "Я преступление. Я наказание. Ты это знал? Преступление и наказание едины. Граница между ними исчезает. Комната просыпается. Олицетворение смерти, хозяин тумана, пожирающего дары. Это чистый инстинкт. Рви и мечи, пожирай, вой и рычи! Это не потеря контроля и не сингулярность..."),
            Book(11, "Рэй Брэдбери", "451 по Фаренгейту", "История о мире, где не ценились книги"),
            Book(12, "Оскар Уайльд", "Портрет Дориана Грея", "История о мужчине, познавшем бессмертие")
        )
        adapter = BookAdapter(data)

        binding.bookList.adapter = adapter

        binding.bookList.setOnItemClickListener { parent, _, position, _ ->
            navigator().launchBookFragment(parent.getItemAtPosition(position) as Book)
        }
    }



}