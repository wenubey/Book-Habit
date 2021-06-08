package com.mertfatih.bookhabit.fragments.list

import android.app.AlertDialog
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.compose.ui.graphics.Color
import androidx.core.graphics.toColor
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.mertfatih.bookhabit.R
import com.mertfatih.bookhabit.fragments.update.UpdateFragment
import com.mertfatih.bookhabit.model.Book
import com.mertfatih.bookhabit.viewmodel.BookViewModel
import kotlinx.android.synthetic.main.custom_row.view.*
import java.util.logging.Handler

class ListAdapter: RecyclerView.Adapter<ListAdapter.MyViewHolder>(){

    private var bookList = emptyList<Book>()
    private lateinit var mBookViewModel: BookViewModel

    class MyViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        return MyViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.custom_row, parent, false))

    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val currentBook = bookList[position]

        holder.itemView.name_txt.text = currentBook.bookName
        holder.itemView.author_txt.text = currentBook.authorName
        holder.itemView.page_txt.text = currentBook.currentPage.toString()
        holder.itemView.progress_bar_count.text = "%" + currentBook.progress.toString()

        holder.itemView.progressBar.apply {
            progressMax = 100f
            setProgressWithAnimation(currentBook.progress.toFloat(), 1000)

        }

        holder.itemView.rowLayout.setOnClickListener {
            val action = ListFragmentDirections.actionListFragmentToUpdateFragment(currentBook)
            holder.itemView.findNavController().navigate(action)
        }


    }

    override fun getItemCount(): Int {
        return bookList.size
    }

    fun setData(book: List<Book>) {
        this.bookList = book
        notifyDataSetChanged()
    }

    private fun deleteBook(book: Book) {
        mBookViewModel.deleteBook(book)
    }

}