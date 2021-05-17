package com.mertfatih.bookhabit.fragments.list

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.mertfatih.bookhabit.R
import com.mertfatih.bookhabit.model.Book
import kotlinx.android.synthetic.main.custom_row.view.*

class ListAdapter: RecyclerView.Adapter<ListAdapter.MyViewHolder>(){

    private var bookList = emptyList<Book>()

    class MyViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        return MyViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.custom_row, parent, false))

    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val currentBook = bookList[position]
        //TODO("proggres bar")
        holder.itemView.name_txt.text = currentBook.bookName
        holder.itemView.author_txt.text = currentBook.authorName
        holder.itemView.page_txt.text = currentBook.bookPageCount.toString()
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


}