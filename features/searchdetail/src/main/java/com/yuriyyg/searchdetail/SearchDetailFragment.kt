package com.yuriyyg.searchdetail

import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.NavArgs
import androidx.navigation.fragment.navArgs
import com.yuriyyg.common.base.BaseFragment
import com.yuriyyg.common.base.BaseViewModel
import com.yuriyyg.searchdetail.databinding.FragmentSearchDetailBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class SearchDetailFragment: BaseFragment<FragmentSearchDetailBinding>(FragmentSearchDetailBinding::inflate) {

    val viewModel: SearchDetailViewModel by viewModels()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //args.itemId
    }


    val args: SearchDetailFragmentArgs by navArgs()
    override fun fetchViewModel(): BaseViewModel {
        return viewModel
    }


}