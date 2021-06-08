package com.mertfatih.bookhabit.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import com.mertfatih.bookhabit.data.BookDatabase
import com.mertfatih.bookhabit.model.Book
import com.mertfatih.bookhabit.repository.BookRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.DisposableHandle
import kotlinx.coroutines.launch

class BookViewModel(application: Application): AndroidViewModel(application) {

    val readAllData: LiveData<List<Book>>
    val getBookCount: LiveData<Int>
    val getSumPageCount: LiveData<Int>
    val getFirstDate: LiveData<String>
    private val repository: BookRepository

    init {
        val bookDao = BookDatabase.getDatabase(application).bookDao()
        repository = BookRepository(bookDao)
        getBookCount = repository.getBookCount
        getSumPageCount = repository.getSumPageCount
        getFirstDate = repository.getFirstDate
        readAllData = repository.readAllData
    }

    fun addBook(book: Book) {
        viewModelScope.launch(Dispatchers.IO) {
            repository.addBook(book)
        }
    }

    fun updateBook(book: Book) {
        viewModelScope.launch(Dispatchers.IO) {
            repository.updateBook(book)
        }
    }

    fun deleteBook(book: Book) {
        viewModelScope.launch(Dispatchers.IO) {
            repository.deleteBook(book)
        }
    }



}