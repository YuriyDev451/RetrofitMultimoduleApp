package com.yuriyyg.flights

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.yuriyyg.common.base.BaseFragment
import com.yuriyyg.common.flowState.Status
import com.yuriyyg.flights.databinding.FragmentSearchListBinding
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch


@AndroidEntryPoint
class SearchListFragment: BaseFragment<FragmentSearchListBinding>(FragmentSearchListBinding::inflate) {


    val viewModel: SearchListViewModel by viewModels()
    private lateinit var adapter: SearchListAdapter
    override fun fetchViewModel(): SearchListViewModel {
        return viewModel
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initRecyclerAdapter()

        lifecycleScope.launch {
            viewModel.getFlights()
        }



        viewModel.data.observe(viewLifecycleOwner){
            adapter.setData(it?.flights ?: listOf())
        }
    }

    private fun initRecyclerAdapter(){
        adapter = SearchListAdapter()
        binding.recyclerView.layoutManager = LinearLayoutManager(context)
        binding.recyclerView.adapter = adapter
    }


}