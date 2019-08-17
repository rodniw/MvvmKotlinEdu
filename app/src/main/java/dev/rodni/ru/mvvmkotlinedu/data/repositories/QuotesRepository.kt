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
import org.threeten.bp.ZonedDateTime

private val MINIMUMINTERVAL: Long = 3

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

        if (lastSavedAt == null || isFetchNeeded(ZonedDateTime.parse(lastSavedAt))) {
            val response = apiRequest {
                api.getQuotes()
            }

            quotes.postValue(response.quotes)
        }
    }

    private fun isFetchNeeded(savedAt: ZonedDateTime): Boolean {
        val threeMinutesAgo = ZonedDateTime.now().minusMinutes(MINIMUMINTERVAL)
        return savedAt.isBefore(threeMinutesAgo)
    }

    private fun saveQuotes(quotes: List<Quote>) {
        Coroutines.io {
            prefs.saveLastSavedAt(ZonedDateTime.now().toString())

            db.getQuoteDao().saveAllQuotes(quotes)
        }
    }
}