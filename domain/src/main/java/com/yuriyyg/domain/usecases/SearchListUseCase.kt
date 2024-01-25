package com.yuriyyg.domain.usecases

import com.yuriyyg.common.flowState.Resource
import com.yuriyyg.data.FlightRepositoryInterface
import com.yuriyyg.domain.mapper.SearchResponseToUIStateMapper
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow
import javax.inject.Inject


class SearchListUseCase @Inject constructor(
    private val repositoryInterface: FlightRepositoryInterface,
    private val mapper: SearchResponseToUIStateMapper
) {

    //invoke()  ola biler

    suspend fun getFlight() = flow{
        emit(Resource.Loading())
        repositoryInterface.getFlights()?.let {
            emit(Resource.Success(it))
        } ?: emit(Resource.Error("Empty message error"))
    }.catch { exception->
        emit(Resource.Error(exception.localizedMessage))
    }

}