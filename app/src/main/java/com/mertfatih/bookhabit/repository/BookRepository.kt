package com.mertfatih.bookhabit.repository

import androidx.lifecycle.LiveData
import com.mertfatih.bookhabit.data.BookDao
import com.mertfatih.bookhabit.model.Book

class BookRepository(private val bookDao: BookDao) {

    val readAllData: LiveData<List<Book>> = bookDao.readAllData()

    suspend fun addBook(book: Book) {
        bookDao.addBook(book)
    }

    suspend fun updateBook(book: Book) {
        bookDao.updateBook(book)
    }



}