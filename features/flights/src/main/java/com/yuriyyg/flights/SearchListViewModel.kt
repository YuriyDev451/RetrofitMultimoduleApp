package com.yuriyyg.flights

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.yuriyyg.common.Resource
import com.yuriyyg.common.State
import com.yuriyyg.domain.usecases.SearchListUseCase
import com.yuriyyg.entities.model.SearchResponse
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.collectLatest
import javax.inject.Inject

@HiltViewModel
class SearchListViewModel @Inject constructor(val searchListUseCase: SearchListUseCase):ViewModel() {

    val state: MutableStateFlow<State?> = MutableStateFlow(null)

    val data = MutableLiveData<SearchResponse?>()

    suspend fun getFlights(){
        searchListUseCase.getFlight().collectLatest{
            when(it){
                is Resource.Error -> state.emit(State.error(it.message))
                is Resource.Loading -> state.emit(State.loading())
                is Resource.Success -> {
                    state.emit(State.success())
                    data.postValue(it.data)
                }
            }
        }
    }

}