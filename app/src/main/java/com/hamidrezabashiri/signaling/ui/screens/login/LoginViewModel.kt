package com.hamidrezabashiri.signaling.ui.screens.login

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.hamidrezabashiri.signaling.data.model.Login
import com.hamidrezabashiri.signaling.data.repository.AuthenticationRepositoryImpl
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class LoginViewModel(private val repository: AuthenticationRepositoryImpl) : ViewModel() {


    val errorMessage = MutableLiveData<String>()

    private val _tempToken = MutableLiveData<Login?>()
    val tempToken: MutableLiveData<Login?> = _tempToken

    private val _code = MutableStateFlow("")
    val code = _code.asStateFlow()
    fun setCode(phoneNumber: String) {
        _code.value = phoneNumber
    }

    val loading = MutableLiveData<Boolean>()

    fun login(phoneNumber: String, tempToken: String) {
        val params: MutableMap<String, String> = HashMap()
        params["phone"] = phoneNumber
        params["code"] = code.value
        params["temp_user_token"] = tempToken

        viewModelScope.launch {
            val response = repository.login(params)

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