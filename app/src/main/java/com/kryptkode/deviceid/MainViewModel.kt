package com.kryptkode.deviceid

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.kryptkode.deviceid.utils.Event

/**
 * Created by kryptkode on 4/12/2020.
 */
class MainViewModel : ViewModel() {

    val deviceId = MutableLiveData<String>()

    private val closeApp = MutableLiveData<Event<Unit>>()
    fun getCloseAppEvent(): LiveData<Event<Unit>> = closeApp

    private val contactAdmin = MutableLiveData<Event<Unit>>()
    fun getContactAdminEvent(): LiveData<Event<Unit>> = contactAdmin

    fun handleClose() {
        closeApp.postValue(Event(Unit))
    }

    fun handleContactAdmin() {
        contactAdmin.postValue(Event(Unit))
    }

    fun onInit(deviceId:String){
        this.deviceId.postValue(deviceId)
    }
}