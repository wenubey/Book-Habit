package com.mertfatih.bookhabit.fragments.list

import android.app.Activity
import android.content.Context
import android.os.Bundle
import android.view.*
import androidx.fragment.app.Fragment
import android.view.inputmethod.InputMethodManager
import androidx.core.content.ContextCompat.getSystemService
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.mertfatih.bookhabit.R
import com.mertfatih.bookhabit.viewmodel.BookViewModel
import kotlinx.android.synthetic.main.fragment_list.view.*

class ListFragment : Fragment() {

    private lateinit var mBookViewModel: BookViewModel


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        val view =  inflater.inflate(R.layout.fragment_list, container, false)

        val adapter = ListAdapter()
        val recyclerView = view.recyclerview
        recyclerView.adapter = adapter
        recyclerView.layoutManager = LinearLayoutManager(requireContext())

        mBookViewModel = ViewModelProvider(this).get(BookViewModel::class.java)
        mBookViewModel.readAllData.observe(viewLifecycleOwner, Observer { book ->
            adapter.setData(book)
        })
        return view
    }

    override fun onResume() {
        super.onResume()
        activity?.let { activity ->
            activity.currentFocus?.let {
                val inputMethodManager = getSystemService(activity.applicationContext, InputMethodManager::class.java)
                inputMethodManager?.hideSoftInputFromWindow(view?.windowToken, 0)
            }
        }
    }



}