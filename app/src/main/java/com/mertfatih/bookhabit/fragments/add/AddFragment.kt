package com.mertfatih.bookhabit.fragments.add

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
import com.mertfatih.bookhabit.R
import com.mertfatih.bookhabit.model.Book
import com.mertfatih.bookhabit.viewmodel.BookViewModel
import kotlinx.android.synthetic.main.fragment_add.*
import kotlinx.android.synthetic.main.fragment_add.view.*
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter
import java.util.*


class AddFragment : Fragment() {

    private lateinit var mBookViewModel: BookViewModel
    private val currentDateTime: LocalDateTime = LocalDateTime.now()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        val view =  inflater.inflate(R.layout.fragment_add, container, false)

        mBookViewModel = ViewModelProvider(this).get(BookViewModel::class.java)

        view.add_btn.setOnClickListener{
            insertDataToDatabase()
        }

        return view
    }

    private fun insertDataToDatabase() {
        val bookName = bookName_et.text.toString()
        val authorName = authorName_et.text.toString()
        val bookPage = pageCount_et.text

        if(inputCheck(bookName, authorName, bookPage)) {
            val book = Book(0, bookName, authorName, Integer.parseInt(bookPage.toString()), currentDateTime.format(DateTimeFormatter.ofPattern("dd/MM/yyyy")), 0, 0)

            mBookViewModel.addBook(book)
            Toast.makeText(requireContext(), "Successfully Added!!!", Toast.LENGTH_LONG).show()
            findNavController().navigate(R.id.action_addFragment_to_listFragment)
        } else {
            Toast.makeText(requireContext(), "Please fill out all fields", Toast.LENGTH_LONG).show()
        }
    }

    private fun inputCheck(bookName: String, authorName: String, page: Editable): Boolean {
        return !(TextUtils.isEmpty(bookName) && TextUtils.isEmpty(authorName) && page.isEmpty())
    }


}