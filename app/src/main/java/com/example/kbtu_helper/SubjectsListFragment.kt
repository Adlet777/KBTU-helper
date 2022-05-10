package com.example.kbtu_helper

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.kbtu_helper.stats.TagStatViewModel
import com.example.kbtu_helper.subjects.Subject
import com.example.kbtu_helper.subjects.SubjectViewModel

class SubjectsListFragment : Fragment() {

    private lateinit var mSubjectViewModel: SubjectViewModel
    private lateinit var mTagStatsViewModel: TagStatViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_subjects_list, container, false)

        val adapter = SubjectsListAdapter()
        val recyclerView = view.findViewById<RecyclerView>(R.id.recyclerview)
        val addButton = view.findViewById<Button>(R.id.add_button)

        recyclerView.adapter = adapter
        recyclerView.layoutManager = LinearLayoutManager(requireContext())

        mSubjectViewModel = ViewModelProvider(this).get(SubjectViewModel::class.java)
        mTagStatsViewModel = ViewModelProvider(this).get(TagStatViewModel::class.java)

//        mSubjectViewModel.listSubjects.observe(viewLifecycleOwner, Observer { subject ->
//            adapter.setData(subject)
//        })

        mTagStatsViewModel.listTagStats.observe(viewLifecycleOwner, Observer { stat ->
            adapter.setData(stat)
        })

//        mTagStatsViewModel.getAllTagStats()
//            .sortedBy {
//                stat -> stat.level
//            }
//            .map {
//                    stat -> stat.tag
//            }
//            .toList()
        println("TAGS !!! = " + mTagStatsViewModel.getAllTagStats().sortedBy { stat -> Integer.parseInt(stat.level.toString()) }.map { stat -> stat.tag }.toList())

        addButton.setOnClickListener {
            findNavController().navigate(R.id.action_subjectsListFragment_to_subjectsAddFragment)
        }

        return view
    }
}