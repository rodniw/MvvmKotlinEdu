package dev.rodni.ru.mvvmkotlinedu.ui.home.quotes

import com.xwray.groupie.databinding.BindableItem
import dev.rodni.ru.mvvmkotlinedu.R
import dev.rodni.ru.mvvmkotlinedu.data.db.entity.Quote
import dev.rodni.ru.mvvmkotlinedu.databinding.ItemQuoteBinding

class QuoteItem(
    private val quote: Quote
) : BindableItem<ItemQuoteBinding>() {

    override fun getLayout() = R.layout.item_quote

    override fun bind(viewBinding: ItemQuoteBinding, position: Int) {
        viewBinding.setQuote(quote)
    }

}