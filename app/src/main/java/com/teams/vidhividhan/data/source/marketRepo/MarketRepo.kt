package com.teams.vidhividhan.data.source.marketRepo

import com.codenicely.bebroker.utils.network.Resource
import com.codenicely.bebroker.utils.network.ResponseHandler
import com.teams.vidhividhan.data.network.MarketApi
import com.teams.vidhividhan.model.marketplaceModel.MarketPlaceModel

class MarketRepo(private val marketApi: MarketApi, private val responseHandler: ResponseHandler) {

    suspend fun getAllProductList():Resource<MarketPlaceModel>{
        return try {
            responseHandler.handleSuccess(marketApi.getAllProductList())
        }catch (e:Exception){
            responseHandler.handleException(e)
        }
    }
}