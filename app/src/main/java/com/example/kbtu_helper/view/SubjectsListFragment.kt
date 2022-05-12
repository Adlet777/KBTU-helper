package com.example.kbtu_helper.view

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
import com.example.kbtu_helper.R
import com.example.kbtu_helper.presenter.adapter.SubjectsListAdapter
import com.example.kbtu_helper.model.subjects.SubjectViewModel

class SubjectsListFragment : Fragment() {

    private lateinit var mSubjectViewModel: SubjectViewModel

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

        mSubjectViewModel.listSubjects.observe(viewLifecycleOwner, Observer { subject ->
            adapter.setData(subject)
        })

        addButton.setOnClickListener {
            findNavController().navigate(R.id.action_subjectsListFragment_to_subjectsAddFragment)
        }

        return view
    }
}