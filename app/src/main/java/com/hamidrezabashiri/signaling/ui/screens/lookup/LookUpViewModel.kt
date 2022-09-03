package com.hamidrezabashiri.signaling.ui.screens.lookup

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.hamidrezabashiri.signaling.data.model.LookUp
import com.hamidrezabashiri.signaling.data.repository.AuthenticationRepository
import com.hamidrezabashiri.signaling.utils.NetworkResult
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class LookUpViewModel @Inject constructor( private val repository: AuthenticationRepository) : ViewModel() {


    val response: MutableState<NetworkResult<LookUp?>> =
        mutableStateOf(NetworkResult.NotInitiated())

    private val _phoneNumber = MutableStateFlow("")
    val phoneNumber = _phoneNumber.asStateFlow()
    fun setPhoneNumber(phoneNumber: String) {
        _phoneNumber.value = phoneNumber
    }

    val params: MutableMap<String, String> = HashMap()

    fun onSendButtonClick() {
        if (phoneNumber.value.trim().isNotEmpty()) {
            params["phone"] = phoneNumber.value
            response.value = NetworkResult.Loading()
        } else {
            response.value = NetworkResult.Error("enter phone number")
        }
        viewModelScope.launch {
            val res = repository.lookUp(params)
            if (res.isSuccessful) {
                response.value = NetworkResult.Success(res.body())
            } else {
                response.value =
                    NetworkResult.Error(res.message() + "  +  " + res.errorBody()?.string())
            }
        }
    }

    override fun onCleared() {
        super.onCleared()

    }

}