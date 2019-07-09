package dev.rodni.ru.mvvmkotlinedu.ui.home.quotes

import androidx.lifecycle.ViewModel;
import dev.rodni.ru.mvvmkotlinedu.data.repositories.QuotesRepository
import dev.rodni.ru.mvvmkotlinedu.util.lazyDeffered

class QuotesViewModel(
    repository: QuotesRepository
) : ViewModel() {

    //will be initialized only when its needed
    //i also created my own lazy to process coroutine
    val quotes by lazyDeffered {
        repository.getQuotes()
    }
}
