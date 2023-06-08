package com.krunal.demo.webservices.withoutlibrary.ui.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.krunal.demo.webservices.withoutlibrary.data.models.local.NewsItem
import com.krunal.demo.webservices.withoutlibrary.repositories.NewsRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch

class NewsViewModel : ViewModel() {

    private val _newsList: MutableStateFlow<List<NewsItem>> = MutableStateFlow(emptyList())
    val newsList: MutableStateFlow<List<NewsItem>> = MutableStateFlow(emptyList())

    init {
        loadData()
    }

    private fun loadData() {
        viewModelScope.launch(Dispatchers.IO) {
            _newsList.emit(
                NewsRepository.getNewsArticles()
                    .map { NewsItem(it.title, it.description, it.url, it.urlToImage) }
            )
        }
    }
}