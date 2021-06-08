package com.mertfatih.bookhabit.fragments.update

import android.app.AlertDialog
import android.os.Bundle
import android.text.Editable
import android.text.TextUtils
import android.view.*
import androidx.fragment.app.Fragment
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.mertfatih.bookhabit.R
import com.mertfatih.bookhabit.model.Book
import com.mertfatih.bookhabit.viewmodel.BookViewModel
import kotlinx.android.synthetic.main.fragment_update.*
import kotlinx.android.synthetic.main.fragment_update.view.*
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

class UpdateFragment : Fragment() {

    private val args by navArgs<UpdateFragmentArgs>()

    private lateinit var mBookViewModel: BookViewModel

    private val currentDateTime: LocalDateTime = LocalDateTime.now()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        val view =  inflater.inflate(R.layout.fragment_update, container, false)

        mBookViewModel = ViewModelProvider(this).get(BookViewModel::class.java)

        view.date_update_txt.text = "You have been reading since: " + args.currentBook.date
        view.page_count_update_txt.text = "Page count of this book: " + args.currentBook.bookPageCount
        view.updatePage_et.setText(args.currentBook.currentPage.toString())

        view.update_btn.setOnClickListener{
            updateItem()
        }

        view.delete_btn.setOnClickListener {
            deleteBook()
        }



        return view
    }

    private fun updateItem() {
        val currentPage = Integer.parseInt(updatePage_et.text.toString())
        val updatedPage = currentPage + args.currentBook.currentPage
        if(inputCheck(updatePage_et.text, currentPage, updatedPage) && progress(currentPage) <= 100) {
            val updatedBook = Book(args.currentBook.id, args.currentBook.bookName, args.currentBook.authorName, args.currentBook.bookPageCount, currentDateTime.format(DateTimeFormatter.ofPattern("dd/MM/yyyy")), updatedPage, progress(updatedPage))

            mBookViewModel.updateBook(updatedBook)
            Toast.makeText(requireContext(), "Successfully updated", Toast.LENGTH_LONG).show()

            findNavController().navigate(R.id.action_updateFragment_to_listFragment)
        } else {
            Toast.makeText(requireContext(), "Please enter a valid page number", Toast.LENGTH_LONG).show()
        }
    }

    private fun inputCheck(page: Editable, currentPage: Int, updatedPage: Int): Boolean {
        return page.isNotEmpty() && args.currentBook.bookPageCount >= currentPage && updatedPage <= args.currentBook.bookPageCount
    }

    private fun progress(currentPage: Int): Int{
        return (100 * currentPage) / args.currentBook.bookPageCount
    }



    private fun deleteBook() {
        val builder = AlertDialog.Builder(requireContext())
        builder.setPositiveButton("Yes") { _,_ ->
            mBookViewModel.deleteBook(args.currentBook)
            Toast.makeText(
                    requireContext(),
                    "Successfully removed: ${args.currentBook.bookName}",
                    Toast.LENGTH_LONG).show()
            findNavController().navigate(R.id.action_updateFragment_to_listFragment)
        }
        builder.setNegativeButton("No") { _,_ -> }
        builder.setTitle("Delete ${args.currentBook.bookName} ?")
        builder.setMessage("Are you sure want to delete ${args.currentBook.bookName} ?")
        builder.create().show()
    }
}