package com.example.kbtu_helper

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.kbtu_helper.subjects.Subject
import com.example.kbtu_helper.subjects.SubjectViewModel
import com.example.kbtu_helper.user.UserViewModel

class HomeFragment : Fragment() {

//    private lateinit var mSubjectViewModel: SubjectViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_home, container, false)

//        mSubjectViewModel = ViewModelProvider(this).get(SubjectViewModel::class.java)

//        val adapter = SubjectsListAdapter()

//        mSubjectViewModel.listSubjects.observe(viewLifecycleOwner, Observer { subject ->
//            adapter.setData(subject)
//        })
//
//        val dataList: List<Subject> =  adapter.getData()
//
//        Log.d("DATA RECEIVED" ,dataList.toString())

        return view
    }
}