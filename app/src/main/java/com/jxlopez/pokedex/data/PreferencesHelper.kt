package com.jxlopez.pokedex.data

import android.content.Context
import com.jxlopez.pokedex.common.Constant

object PreferencesHelper {
    private var PRIVATE_MODE = 0
    private val PREF_NAME = "pokedex-pref"

    fun setLogged(context: Context, logged: Boolean ) {
        val editor = context.getSharedPreferences(PREF_NAME, PRIVATE_MODE).edit()
        editor.putBoolean(Constant.Preferences.LOGGED, logged)
        editor.apply()
    }

    fun getLogged(context: Context): Boolean {
        val preferences = context.getSharedPreferences(PREF_NAME, PRIVATE_MODE)
        return preferences.getBoolean(
            Constant.Preferences.LOGGED,
            false
        )
    }
}