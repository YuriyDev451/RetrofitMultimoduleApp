package com.yuriyyg.common.base

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import androidx.viewbinding.ViewBinding
import com.yuriyyg.common.flowState.Status
import com.yuriyyg.common.util.LoadingDialog
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import java.lang.Exception

typealias Inflate<T> = (LayoutInflater, ViewGroup?, Boolean) -> T

open abstract class BaseFragment<B : ViewBinding>(private val inflate: Inflate<B>): Fragment() {


    private lateinit var loadingDialog: LoadingDialog


    private var _binding: ViewBinding? = null
    protected val binding: B
        get() = _binding as B


   abstract fun fetchViewModel(): BaseViewModel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        loadingDialog = LoadingDialog(requireContext())
    }


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = inflate.invoke(inflater, container, false)
        return _binding?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        handleState()
    }


    fun handleState() {

        lifecycleScope.launch {
            fetchViewModel().state.collectLatest { state->
                state?.let {
                    when(it.status){
                        Status.SUCCESS -> showLoadingProgress(false)
                        Status.ERROR -> showLoadingProgress(false)
                        Status.LOADING -> showLoadingProgress(true)
                    }
                }
            }
        }
    }

    private fun showLoadingProgress(isLoad : Boolean){
        if (::loadingDialog.isInitialized){
            loadingDialog.apply {
                if (isLoad){
                    if (!isShowing && isAdded)
                        show()
                }else{
                    if (isShowing)  dismiss()
                }
            }


        }
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}