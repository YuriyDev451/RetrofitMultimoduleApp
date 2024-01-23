package com.yuriyyg.flights

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.yuriyyg.domain.usecases.SearchListUseCase
import com.yuriyyg.entities.SearchResponse
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SearchListViewModel @Inject constructor(val searchListUseCase: SearchListUseCase):ViewModel() {

    val data = MutableLiveData<SearchResponse?>()

    suspend fun getFlights(){
        searchListUseCase.getFlight().collect{

        }
    }
}