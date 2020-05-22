package com.app.gastranetwork.data.preference

import com.app.gastranetwork.data.preference.repository.UserAccess
import com.orhanobut.hawk.Hawk

class AppPreference {

    companion object {
        var instance = AppPreference()

        var PREFERENCE_TOKEN_LOGIN = "PREFERENCE_TOKEN_LOGIN"
        var PREFERENCE_USER_ACCESS = "PREFERENCE_USER_ACCESS"
        var PREFERENCE_USER_LOGIN = "PREFERENCE_USER_LOGIN"
    }

    fun setToken(token: String?) {
        Hawk.put(PREFERENCE_TOKEN_LOGIN, token)
    }

    fun getToken(): String? {
        if (Hawk.get<String>(PREFERENCE_TOKEN_LOGIN) != null) {
            return Hawk.get<String>(PREFERENCE_TOKEN_LOGIN)
        }
        return ""
    }

    fun setIsLogin(isLogin: Boolean?) {
        Hawk.put(PREFERENCE_USER_LOGIN, isLogin)
    }

    fun getIsLogin(): Boolean? {
        if (Hawk.get<Boolean>(PREFERENCE_USER_LOGIN) != null) {
            return Hawk.get<Boolean>(PREFERENCE_USER_LOGIN)
        }
        return false
    }

    fun setUserAccess(access: UserAccess?) {
        Hawk.put(PREFERENCE_USER_ACCESS, access)
    }

    fun getUserAccess(): UserAccess? {
        if (Hawk.get<UserAccess>(PREFERENCE_USER_ACCESS) != null) {
            return Hawk.get<UserAccess>(PREFERENCE_USER_ACCESS)
        }
        return null
    }
}