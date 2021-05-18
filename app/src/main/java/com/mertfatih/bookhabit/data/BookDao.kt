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

    @Query("SELECT * FROM book_table ORDER BY book_id ASC")
    fun readAllData(): LiveData<List<Book>>

}