package com.jxlopez.pokedex.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import com.jxlopez.pokedex.repository.LoginRepository

class LoginViewModel(application: Application) : AndroidViewModel(application) {
    var loginRepository = LoginRepository()
    var logged : MutableLiveData<Boolean> = MutableLiveData<Boolean>()

    fun loginUser(email: String, password: String) {
        logged.postValue(loginRepository.loginUser(email, password).value)
    }
}