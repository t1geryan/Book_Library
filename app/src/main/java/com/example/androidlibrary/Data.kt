package com.example.androidlibrary

import com.example.androidlibrary.adapter.Book

val bookData = mutableListOf(
    Book("Джоан Роулинг", "Гарри Поттер и Философский Камень", "История о мальчике, который выжил"),
    Book( "Майк Омер", "Внутри Убийцы", "История о профайлере"),
    Book( "Артур Конан Дойл","Приключения Шерлока Холмса и доктора Ватсона","История о великом детективе"),
    Book( "Джеймс Оруэлл", "1984", "История о жизни без надежды"),
    Book( "Грегори Макдональд", "Флетч", "История о наглом репортёре"),
    Book("Майк Омер", "Заживо в темноте", "Продолжение истории о профайлере"),
    Book("Фрэнсис Скотт Фицджеральд", "Великий Гэстби", "Истоия о миллиардере-однолюбе"),
    Book("Джоан Роулинг", "Гарри Поттер и Тайная Комната", "История о мальчике, который выжил"),
    Book( "Лев Николаевич Толстой", "Война и Мир", "История о том, как война меняет жизни"),
    Book( "Фёдор Михайлович Достоевский", "Преступление и Наказание", "История о сильных и слабых"),
    Book("Рэй Брэдбери", "451 по Фаренгейту", "История о мире, где не ценились книги"),
    Book( "Оскар Уайльд", "Портрет Дориана Грея", "История о мужчине, познавшем бессмертие")
).sorted().also {
    for (i in it.indices)
        it[i].id = i.toLong()
}

