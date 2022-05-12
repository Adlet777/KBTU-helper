package com.example.kbtu_helper.view

import android.os.Bundle
import android.text.Editable
import android.text.TextUtils
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.example.kbtu_helper.R
import com.example.kbtu_helper.model.stats.TagStat
import com.example.kbtu_helper.model.stats.TagStatViewModel
import com.example.kbtu_helper.model.subjects.Subject
import com.example.kbtu_helper.model.subjects.SubjectViewModel

class SubjectsAddFragment : Fragment() {

    private lateinit var mSubjectViewModel: SubjectViewModel
    private lateinit var mTagStatsViewModel: TagStatViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_subjects_add, container, false)

        mSubjectViewModel = ViewModelProvider(this).get(SubjectViewModel::class.java)
        mTagStatsViewModel = ViewModelProvider(this).get(TagStatViewModel::class.java)

        val createButton = view.findViewById<Button>(R.id.button_create_subject)
        val nameAdd = view.findViewById<EditText>(R.id.name_add).text
        val levelAdd = view.findViewById<EditText>(R.id.level_add).text
        val tagAdd = view.findViewById<EditText>(R.id.tag_add).text


        createButton.setOnClickListener {
            insertDataToDB(nameAdd, levelAdd, tagAdd)
        }

        return view
    }

    private fun insertDataToDB(nameAdd: Editable, levelAdd: Editable, tagAdd: Editable) {

        if (inputCheck(nameAdd, levelAdd, tagAdd)) {

            val tagStat: TagStat = mTagStatsViewModel.getTagStatsByTag(tagAdd.toString())

            if (tagStat == null) {
                mTagStatsViewModel.createTagStat(TagStat(0, tagAdd.toString(), levelAdd.toString()))
            } else {
                mTagStatsViewModel.updateLevel(
                    tagStat.uid,
                    ((Integer.parseInt(tagStat.level) + Integer.parseInt(levelAdd.toString())) / 2).toString()
                )
            }

            val subject = Subject(
                0,
                nameAdd.toString(),
                Integer.parseInt(levelAdd.toString()),
                tagAdd.toString()
            )
            mSubjectViewModel.createSubject(subject)
            Toast.makeText(requireContext(), "Successfully created Subject", Toast.LENGTH_LONG)
                .show()
            findNavController().navigate(R.id.action_subjectsAddFragment_to_subjectsListFragment)
        } else
            Toast.makeText(requireContext(), "Some values are not entered", Toast.LENGTH_LONG)
                .show()
    }

    private fun inputCheck(
        nameAdd: Editable, levelAdd: Editable, tagAdd: Editable
    ): Boolean {
        return !(TextUtils.isEmpty(nameAdd)
                && levelAdd.isEmpty()
                && TextUtils.isEmpty(tagAdd))
    }
}