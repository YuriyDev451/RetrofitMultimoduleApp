package com.yuriyyg.entities.uimodel


import com.yuriyyg.entities.BaseMapper
import com.yuriyyg.entities.model.Data

class SearchResponseToUIStateMapper: BaseMapper<com.yuriyyg.entities.model.Data, FlightSearchUIState> {
    override fun map(input: Data): FlightSearchUIState {
        /*return FlightSearchUIState(
            header = input.getSearchHeader(),
            flights = input.getFlight() ) //10MINUTE*/
        TODO()
    }


    private fun Data.getSearchHeader(): SearchHeaderUiModel{

        return SearchHeaderUiModel( origin =search_parameters.origin.city_name ,
                                    destination = search_parameters.destination.city_name)

    }

    private fun Data.getFlight(): FlightListUIModel{
        return FlightListUIModel(airlineIcon = "", airlineName = "", price = 0.0  )
    }

}