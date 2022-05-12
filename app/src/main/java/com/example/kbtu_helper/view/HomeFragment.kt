package com.example.kbtu_helper.view

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.kbtu_helper.R
import com.example.kbtu_helper.presenter.adapter.HomeAdapter
import com.example.kbtu_helper.presenter.db.retrofit.ApiRequests
import com.example.kbtu_helper.model.stats.TagStatViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import retrofit2.Retrofit
import retrofit2.awaitResponse
import retrofit2.converter.gson.GsonConverterFactory


class HomeFragment : Fragment() {

    private val BASE_URL = "http://10.0.2.2:9020/"
    private var TAG = "MainActivity"
    private lateinit var mTagStatsViewModel: TagStatViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val view = inflater.inflate(R.layout.fragment_home, container, false)


        val adapter = HomeAdapter()
        val recyclerView = view.findViewById<RecyclerView>(R.id.recyclerview_home_fragment)

        recyclerView.adapter = adapter
        recyclerView.layoutManager = LinearLayoutManager(requireContext())

        mTagStatsViewModel = ViewModelProvider(this).get(TagStatViewModel::class.java)

        val sortedListOfTags = mTagStatsViewModel.getAllTagStats()
            .sortedBy { stat -> Integer.parseInt(stat.level) }
            .map { stat -> stat.tag }
            .toList()

        val api = Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(ApiRequests::class.java)

        GlobalScope.launch(Dispatchers.IO) {
            try {
                val response = api.getArticlesByTag(sortedListOfTags[0]).awaitResponse()
                if (response.isSuccessful) {
                    val data = response.body()!!
                    Log.d(TAG, data[0].text)

                    withContext(Dispatchers.Main) {
                        adapter.setData(data)
                    }
                }
            } catch (e: Exception) {
                withContext(Dispatchers.Main) {
                    Log.e("EXCEPTION", e.localizedMessage)
                }
            }
        }

        return view
    }
}