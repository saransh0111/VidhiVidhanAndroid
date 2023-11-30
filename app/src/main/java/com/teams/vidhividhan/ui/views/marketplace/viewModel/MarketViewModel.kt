package com.teams.vidhividhan.ui.views.marketplace.viewModel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.codenicely.bebroker.utils.network.Resource
import com.teams.vidhividhan.data.source.marketRepo.MarketRepo
import com.teams.vidhividhan.model.marketplaceModel.MarketPlaceModel
import kotlinx.coroutines.launch

class MarketViewModel(private val marketRepo: MarketRepo):ViewModel() {

    private val _marketViewModelLiveData = MutableLiveData<Resource<MarketPlaceModel>>()
    val marketViewModelLiveData get() = _marketViewModelLiveData

    fun getProductList(){
        _marketViewModelLiveData.postValue(Resource.loading(null))
        viewModelScope.launch {
            _marketViewModelLiveData.postValue(marketRepo.getAllProductList())
        }
    }
}