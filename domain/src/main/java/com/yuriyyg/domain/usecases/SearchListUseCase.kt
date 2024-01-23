package com.yuriyyg.domain.usecases

import com.yuriyyg.common.Resource
import com.yuriyyg.data.FlightRepositoryInterface
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow
import javax.inject.Inject


class SearchListUseCase @Inject constructor(val repositoryInterface: FlightRepositoryInterface) {

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