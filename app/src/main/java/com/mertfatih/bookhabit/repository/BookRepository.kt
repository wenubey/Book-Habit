package com.mertfatih.bookhabit.repository

import androidx.lifecycle.LiveData
import com.mertfatih.bookhabit.data.BookDao
import com.mertfatih.bookhabit.model.Book

class BookRepository(private val bookDao: BookDao) {

    val readAllData: LiveData<List<Book>> = bookDao.readAllData()
    val getBookCount: LiveData<Int> = bookDao.getBookCount()
    val getSumPageCount: LiveData<Int> = bookDao.getSumPageCount()
    val getFirstDate: LiveData<String> = bookDao.getFirstDate()

    suspend fun addBook(book: Book) {
        bookDao.addBook(book)
    }

    suspend fun updateBook(book: Book) {
        bookDao.updateBook(book)
    }

    suspend fun deleteBook(book: Book) {
        bookDao.deleteBook(book)
    }

    /*suspend fun getBookCount(): LiveData<Int> {
        return bookDao.getBookCount()
    }*/

}