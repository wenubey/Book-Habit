package com.mertfatih.bookhabit.model

import android.os.Parcelable
import androidx.room.*
import kotlinx.android.parcel.Parcelize
import java.time.LocalDateTime
import java.util.*

@Parcelize
@Entity(tableName = "book_table")
data class Book(
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "book_id")
    val id: Int,
    @ColumnInfo(name = "book_name")
    val bookName: String,
    @ColumnInfo(name = "author_name")
    val authorName: String,
    @ColumnInfo(name = "book_page")
    val bookPageCount: Int,

): Parcelable


