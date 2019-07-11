package dev.rodni.ru.mvvmkotlinedu.data.repositories

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import dev.rodni.ru.mvvmkotlinedu.data.db.AppDatabase
import dev.rodni.ru.mvvmkotlinedu.data.db.entity.Quote
import dev.rodni.ru.mvvmkotlinedu.data.network.MyApi
import dev.rodni.ru.mvvmkotlinedu.data.network.SafeApiRequest
import dev.rodni.ru.mvvmkotlinedu.data.preferences.PreferenceProvider
import dev.rodni.ru.mvvmkotlinedu.util.Coroutines
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import java.time.LocalDateTime
import java.time.temporal.ChronoUnit

private val MINIMUMINTERVAL = 6

class QuotesRepository(
    private val api: MyApi,
    private val db: AppDatabase,
    private val prefs: PreferenceProvider
) : SafeApiRequest() {

    private val quotes = MutableLiveData<List<Quote>>()

    init {
        quotes.observeForever {
            saveQuotes(it)
        }
    }

    suspend fun getQuotes() : LiveData<List<Quote>> {
        return withContext(Dispatchers.IO) {
            fetchQuotes()
            db.getQuoteDao().getQuotes()
        }
    }

    private suspend fun fetchQuotes() {
        val lastSavedAt = prefs.getLastSavedAt()

        if (lastSavedAt == null || isFetchNeeded(LocalDateTime.parse(lastSavedAt))) {
            val response = apiRequest {
                api.getQuotes()
            }

            quotes.postValue(response.quotes)
        }
    }

    private fun isFetchNeeded(savedAt: LocalDateTime): Boolean {
        return ChronoUnit.HOURS.between(savedAt, LocalDateTime.now()) > MINIMUMINTERVAL
    }

    private fun saveQuotes(quotes: List<Quote>) {
        Coroutines.io {
            //TODO: change to my own impl instead of using LocalDateTime
            prefs.saveLastSavedAt(LocalDateTime.now().toString())

            db.getQuoteDao().saveAllQuotes(quotes)
        }
    }
}