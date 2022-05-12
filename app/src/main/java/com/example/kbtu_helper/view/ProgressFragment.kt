package com.example.kbtu_helper.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.kbtu_helper.R
import com.example.kbtu_helper.presenter.adapter.ProgressAdapter
import com.example.kbtu_helper.model.stats.TagStatViewModel


class ProgressFragment : Fragment() {

    private lateinit var mTagStatsViewModel: TagStatViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_progress, container, false)

        val adapter = ProgressAdapter()
        val recyclerView = view.findViewById<RecyclerView>(R.id.recyclerview_progress_fragment)

        recyclerView.adapter = adapter
        recyclerView.layoutManager = LinearLayoutManager(requireContext())

        mTagStatsViewModel = ViewModelProvider(this).get(TagStatViewModel::class.java)


        mTagStatsViewModel.listTagStats.observe(viewLifecycleOwner, Observer { stat ->
            adapter.setData(stat)
        })


        return view
    }
}