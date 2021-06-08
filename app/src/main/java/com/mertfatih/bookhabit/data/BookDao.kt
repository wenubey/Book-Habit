package com.mertfatih.bookhabit.data

import androidx.lifecycle.LiveData
import androidx.room.*
import com.mertfatih.bookhabit.model.Book

@Dao
interface BookDao {

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun addBook(book: Book)

    @Update
    suspend fun updateBook(book: Book)

    @Delete
    suspend fun deleteBook(book: Book)

    @Query("SELECT * FROM book_table ORDER BY book_id ASC")
    fun readAllData(): LiveData<List<Book>>

    @Query("SELECT COUNT(*) FROM book_table")
    fun getBookCount(): LiveData<Int>

    @Query("SELECT SUM(current_page) FROM book_table")
    fun getSumPageCount(): LiveData<Int>

    @Query("SELECT date FROM book_table ORDER BY date ASC")
    fun getFirstDate(): LiveData<String>
}