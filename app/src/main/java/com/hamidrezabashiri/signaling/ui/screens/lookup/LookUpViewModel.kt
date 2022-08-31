package com.hamidrezabashiri.signaling.ui.screens.lookup

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.hamidrezabashiri.signaling.data.repository.AuthenticationRepositoryImpl
import kotlinx.coroutines.*

class LookUpViewModel(private val repositoryImpl: AuthenticationRepositoryImpl) : ViewModel() {

    val errorMessage = MutableLiveData<String>()
    val tempToken = MutableLiveData<String?>()
    var job: Job? = null
    val exceptionHandler = CoroutineExceptionHandler { _, throwable ->
        onError("Exception handled: ${throwable.localizedMessage}")
    }
    val loading = MutableLiveData<Boolean>()

    fun lookup() {
        job = CoroutineScope(Dispatchers.IO + exceptionHandler).launch {
            val params: MutableMap<String, String> = HashMap()
            params["phone"] = "09184020027"
            withContext(Dispatchers.Main) {
                val response = repositoryImpl.lookUp(params)
                if (response.code == "200") {
                    tempToken.postValue(response.data?.temporaryToken.toString())
                    loading.value = false
                } else {
                    onError("Error : ${response.message} ")
                }
            }
        }
        Log.i("TAG", "lookup: "+tempToken.value)

    }

    private fun onError(message: String) {
//        errorMessage.value = message
        loading.value = false
    }

    override fun onCleared() {
        super.onCleared()
        job?.cancel()
    }

}