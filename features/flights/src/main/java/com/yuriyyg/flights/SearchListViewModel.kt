package com.yuriyyg.flights

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.yuriyyg.data.FlightRepository
import com.yuriyyg.data.FlightRepositoryInterface
import com.yuriyyg.entities.SearchResponse
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SearchListViewModel @Inject constructor(val repositoryInterface: FlightRepositoryInterface):ViewModel() {

    val data = MutableLiveData<SearchResponse?>()

    fun getFlights(){
        viewModelScope.launch {
            repositoryInterface.getFlights()?.let {
                data.postValue(it)
            }
        }
    }
}