package com.example.czystepowietrze.viewModel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.czystepowietrze.api.AirStationJasonItem
import kotlinx.coroutines.*
import retrofit2.Response
import androidx.lifecycle.viewModelScope
import com.example.czystepowietrze.Repository.RetrofitRepository
import com.example.czystepowietrze.api.AirStationDetailsJsonItem
import com.example.czystepowietrze.api.ParamDetailsJson


class FragmentViewModel(private val retrofitrepo: RetrofitRepository): ViewModel() {

    var myResponse: MutableLiveData<Response<List<AirStationJasonItem>>> = MutableLiveData()
    var myResponseDetails: MutableLiveData<Response<List<AirStationDetailsJsonItem>>> = MutableLiveData()
    var myResponseParam: MutableLiveData<Response<ParamDetailsJson>> = MutableLiveData()


    fun getAllStations(){
        viewModelScope.launch {
            val response = retrofitrepo.getAllStations()
            myResponse.value = response
        }
    }

    fun getStationDetails(id: String){
        viewModelScope.launch {
            val response = retrofitrepo.getStationDetails(id)
            myResponseDetails.value = response
        }
    }

    fun getParamDetails(paramId:String){
        viewModelScope.launch {
            val response = retrofitrepo.getParamDetails(paramId)
            myResponseParam.value = response
        }
    }

}