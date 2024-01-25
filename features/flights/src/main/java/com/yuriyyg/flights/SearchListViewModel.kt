package com.yuriyyg.flights

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.yuriyyg.common.base.BaseViewModel
import com.yuriyyg.common.flowState.Resource
import com.yuriyyg.common.flowState.State
import com.yuriyyg.domain.mapper.SearchResponseToUIStateMapper
import com.yuriyyg.domain.usecases.SearchListUseCase
import com.yuriyyg.entities.uimodel.FlightSearchUIState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.collectLatest
import javax.inject.Inject

@HiltViewModel
class SearchListViewModel @Inject constructor(val searchListUseCase: SearchListUseCase,
                                              private val mapper: SearchResponseToUIStateMapper
):BaseViewModel() {



    val data = MutableLiveData<FlightSearchUIState?>()

    suspend fun getFlights(){
        searchListUseCase.getFlight().collectLatest{
            when(it){
                is Resource.Error -> state.emit(State.error(it.message))
                is Resource.Loading -> state.emit(State.loading())
                is Resource.Success -> {
                    state.emit(State.success())
                    it.data?.data?.let {
                        val mappedData= mapper.map(it)
                        data.postValue(mappedData)
                    }

                }
            }
        }
    }

}