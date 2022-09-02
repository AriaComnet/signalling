package com.hamidrezabashiri.signaling.ui.screens.login

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.hamidrezabashiri.signaling.data.model.Login
import com.hamidrezabashiri.signaling.data.repository.AuthenticationRepositoryImpl
import com.hamidrezabashiri.signaling.utils.NetworkResult
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class LoginViewModel(private val repository: AuthenticationRepositoryImpl) : ViewModel() {


    val response: MutableState<NetworkResult<Login?>> = mutableStateOf(NetworkResult.NotInitiated())

    private val _code = MutableStateFlow("")
    val code = _code.asStateFlow()
    fun setCode(phoneNumber: String) {
        _code.value = phoneNumber
    }


    fun onLoginButtonClick(phoneNumber: String, tempToken: String) {
        val params: MutableMap<String, String> = HashMap()
        params["phone"] = phoneNumber
        params["code"] = code.value
        params["temp_user_token"] = tempToken

        viewModelScope.launch {
            val res = repository.login(params)
            if (res.isSuccessful) {
                response.value = NetworkResult.Success(res.body())
            } else {
                response.value =
                    NetworkResult.Error(res.message() + "  +  " + res.errorBody().toString())
            }
        }
    }

    override fun onCleared() {
        super.onCleared()

    }


}