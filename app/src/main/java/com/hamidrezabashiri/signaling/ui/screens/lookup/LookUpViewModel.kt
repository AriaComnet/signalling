package com.hamidrezabashiri.signaling.ui.screens.lookup

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.hamidrezabashiri.signaling.data.model.LookUp
import com.hamidrezabashiri.signaling.data.repository.AuthenticationRepositoryImpl
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class LookUpViewModel(private val repositoryImpl: AuthenticationRepositoryImpl) : ViewModel() {

    val errorMessage = MutableLiveData<String>()

    private val _tempToken = MutableLiveData<LookUp?>()
    val tempToken: MutableLiveData<LookUp?> = _tempToken

    private val _phoneNumber = MutableStateFlow("")
    val phoneNumber = _phoneNumber.asStateFlow()
    fun setPhoneNumber(phoneNumber: String) {
        _phoneNumber.value = phoneNumber
    }

    val loading = MutableLiveData<Boolean>()

    fun lookup() {
        val params: MutableMap<String, String> = HashMap()
        params["phone"] = phoneNumber.value

        viewModelScope.launch {
            val response = repositoryImpl.lookUp(params)

            if (response.isSuccessful) {
                _tempToken.postValue(response.body())
                loading.value = false

            } else {
                onError("Error : ${response.body()?.message} ")

            }
        }
    }


    private fun onError(message: String) {
        errorMessage.value = message
        loading.value = false
    }

    override fun onCleared() {
        super.onCleared()

    }

}