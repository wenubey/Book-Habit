package com.mertfatih.bookhabit.fragments.statistic

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.mertfatih.bookhabit.R
import com.mertfatih.bookhabit.viewmodel.BookViewModel
import kotlinx.android.synthetic.main.fragment_statistic.*
import kotlinx.android.synthetic.main.fragment_statistic.view.*


class StatisticFragment : Fragment() {

    private lateinit var mBookViewModel: BookViewModel

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {

        val view =  inflater.inflate(R.layout.fragment_statistic, container, false)



        mBookViewModel = ViewModelProvider(this).get(BookViewModel::class.java)

        mBookViewModel.getBookCount.observe(viewLifecycleOwner, Observer<Int> { integer ->  sumPage_txt.text =  "You have read ${integer} books"})
        mBookViewModel.getSumPageCount.observe(viewLifecycleOwner, Observer<Int> {integer -> sumBook_txt.text = "You have read a total of ${integer} pages of books so far." })
        mBookViewModel.getFirstDate.observe(viewLifecycleOwner, Observer<String> { string -> firstDate_txt.text = "You have been reading since $string"})


        return view
    }




}