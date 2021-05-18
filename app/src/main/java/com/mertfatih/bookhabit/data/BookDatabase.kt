package com.mertfatih.bookhabit.data

import android.content.Context
import androidx.room.*
import com.mertfatih.bookhabit.model.Book

@Database(
        entities = [Book::class],
        version = 4,
        exportSchema = false,

)
abstract class BookDatabase: RoomDatabase() {

    abstract fun bookDao(): BookDao

    companion object {

        @Volatile
        private var INSTANCE: BookDatabase? = null

        fun getDatabase(context: Context): BookDatabase {
            val tempInstance = INSTANCE
            if(tempInstance != null) {
                return tempInstance
            }
            synchronized(this) {
                val instance = Room.databaseBuilder(
                        context.applicationContext,
                        BookDatabase::class.java,
                        "book_database"
                ).fallbackToDestructiveMigration()
                .build()
                INSTANCE = instance
                return instance
            }
        }


    }
}
