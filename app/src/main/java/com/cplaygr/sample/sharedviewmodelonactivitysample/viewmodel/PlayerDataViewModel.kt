package com.cplaygr.sample.sharedviewmodelonactivitysample.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.cplaygr.sample.sharedviewmodelonactivitysample.model.PlayerData

class PlayerDataViewModel : ViewModel() {
    val playerDataLiveData: MutableLiveData<PlayerData> = MutableLiveData()
}