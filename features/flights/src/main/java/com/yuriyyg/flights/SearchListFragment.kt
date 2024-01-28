package com.yuriyyg.flights

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.constraintlayout.widget.ConstraintSet
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.yuriyyg.common.base.BaseFragment
import com.yuriyyg.common.flowState.Status
import com.yuriyyg.common.searchListToDetail
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


    //создание вью через код
    fun addButton(){
        val button = Button(context)
        val buttonId = 1
        button.id = buttonId
        button.text = "Button"
        context?.let {
            button.setBackgroundColor(it.getColor(android.R.color.background_dark))
        }

        val set = ConstraintSet()
        set.clone(binding.root)

        set.connect(button.id, ConstraintSet.START, ConstraintSet.PARENT_ID, ConstraintSet.START, 50)
        set.connect(button.id, ConstraintSet.TOP, ConstraintSet.PARENT_ID, ConstraintSet.TOP, 50)
        set.connect(button.id, ConstraintSet.END, ConstraintSet.PARENT_ID, ConstraintSet.END, 50)
        set.applyTo(binding.root)


        binding.root.addView(button)

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

        addButton()
    }

    private fun initRecyclerAdapter(){
        adapter = SearchListAdapter{
            findNavController().searchListToDetail(it.enuid)
        }
        binding.recyclerView.layoutManager = LinearLayoutManager(context)
        binding.recyclerView.adapter = adapter
    }


}