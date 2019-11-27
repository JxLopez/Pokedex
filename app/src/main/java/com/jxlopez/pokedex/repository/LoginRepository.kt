package com.jxlopez.pokedex.repository

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.jxlopez.pokedex.common.Constant

class LoginRepository {
    private val data = MutableLiveData<Boolean>()

    fun loginUser(email: String, password: String): LiveData<Boolean> {
        data.value = email == Constant.EMAIL && password == Constant.PASSWORD
        return data
    }
}