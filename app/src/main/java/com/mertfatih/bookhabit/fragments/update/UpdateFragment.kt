package com.mertfatih.bookhabit.fragments.update

import android.os.Bundle
import android.text.Editable
import android.text.TextUtils
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.mertfatih.bookhabit.R
import com.mertfatih.bookhabit.model.Book
import com.mertfatih.bookhabit.viewmodel.BookViewModel
import kotlinx.android.synthetic.main.fragment_update.*
import kotlinx.android.synthetic.main.fragment_update.view.*

class UpdateFragment : Fragment() {

    private val args by navArgs<UpdateFragmentArgs>()

    private lateinit var mBookViewModel: BookViewModel

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        val view =  inflater.inflate(R.layout.fragment_update, container, false)

        mBookViewModel = ViewModelProvider(this).get(BookViewModel::class.java)

        view.updateBookName_et.setText(args.currentBook.bookName)
        view.updateAuthorName_et.setText(args.currentBook.authorName)
        view.updatePage_et.setText(args.currentBook.bookPageCount.toString())

        view.update_btn.setOnClickListener{
            updateItem()
        }


        return view
    }

    private fun updateItem() {
        val bookName = updateBookName_et.text.toString()
        val authorName = updateAuthorName_et.text.toString()
        val page = Integer.parseInt(updatePage_et.text.toString())

        if(inputCheck(bookName, authorName, updatePage_et.text)) {
            val updatedBook = Book(args.currentBook.id, bookName, authorName, page)

            mBookViewModel.updateBook(updatedBook)
            Toast.makeText(requireContext(), "Successfully updated", Toast.LENGTH_LONG).show()

            findNavController().navigate(R.id.action_updateFragment_to_listFragment)
        } else {
            Toast.makeText(requireContext(), "Please fill out all fields ", Toast.LENGTH_LONG).show()
        }
    }

    private fun inputCheck(bookName: String, authorName: String, page: Editable): Boolean {
        return !(TextUtils.isEmpty(bookName) && TextUtils.isEmpty(authorName) && page.isEmpty())
    }

}