package com.experion.assignment.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.experion.assignment.ui.adapters.CaRecyclerViewAdapter
import com.experion.assignment.R
import com.experion.assignment.models.CaModel
import com.experion.assignment.models.ResponseModel
import com.experion.assignment.utils.Constants.RESPONSE_DATA_KEY
import kotlinx.android.synthetic.main.fragment_home.*


class CaFragment : Fragment() {

    private var newsFragmentViewModel: CaFragmentViewModel? = null
    private val list = ArrayList<CaModel>()
    private var responseModel: ResponseModel? = null


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val rootView = inflater.inflate(R.layout.fragment_home, container, false)
        activity?.application?.let {
            newsFragmentViewModel = ViewModelProvider.AndroidViewModelFactory.getInstance(it)
                .create(CaFragmentViewModel::class.java)
        }

        if (savedInstanceState != null) {
            responseModel = savedInstanceState.getParcelable(RESPONSE_DATA_KEY)
        }
        return rootView
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {

        super.onViewCreated(view, savedInstanceState)

        initializeSwipeRefreshLayout()

        initializeNewsRecyclerView()

        if (responseModel == null) {
            fetchNewsFeeds()
        } else {
            populateData(responseModel)
        }
    }

    private fun initializeSwipeRefreshLayout() {
        swipeRefreshLayout.setOnRefreshListener {
            if (swipeRefreshLayout.isRefreshing) {
                list.clear()
                newsRecyclerView.adapter = CaRecyclerViewAdapter(list)
                fetchNewsFeeds()
            }
        }
    }

    private fun initializeNewsRecyclerView() {
        newsRecyclerView.adapter = CaRecyclerViewAdapter(list)
        newsRecyclerView.layoutManager = LinearLayoutManager(activity, RecyclerView.VERTICAL, false)
    }

    private fun enableNewsFeedView(isEnabled: Boolean) {
        newsRecyclerView.visibility = if (isEnabled) View.VISIBLE else View.GONE
        listEmptyTextView.visibility = if (!isEnabled) View.VISIBLE else View.GONE
    }

    private fun fetchNewsFeeds() {
        swipeRefreshLayout.isRefreshing = true
        newsFragmentViewModel?.responseModel?.observe(this, Observer {
            responseModel = it
            swipeRefreshLayout.isRefreshing = false
            populateData(it)
        })
    }

    private fun populateData(it: ResponseModel?) {
        list.clear()
        activity?.title = it?.title
        if (it?.rows?.size ?: 0 > 0) {
            list.addAll(it?.rows!!)
            newsRecyclerView.adapter?.notifyDataSetChanged()
            enableNewsFeedView(true)
        } else {
            enableNewsFeedView(false)
        }
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        outState.putParcelable(RESPONSE_DATA_KEY, responseModel)
    }


    override fun onDestroy() {
        super.onDestroy()
        newsFragmentViewModel?.cancelJobs()
    }
}