package dev.rodni.ru.mvvmkotlinedu.data.network.responses

import dev.rodni.ru.mvvmkotlinedu.data.db.entity.Quote

data class QuotesResponse(
    val isSuccessful : Boolean?,
    val quotes : List<Quote>
)