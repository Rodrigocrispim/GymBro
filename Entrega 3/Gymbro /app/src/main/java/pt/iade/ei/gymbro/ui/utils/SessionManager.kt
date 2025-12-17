package pt.iade.ei.gymbro.ui.utils

import android.content.Context
import android.content.SharedPreferences

object SessionManager {
    private const val PREF_NAME = "GymBroSession"
    private const val KEY_TOKEN = "auth_token"
    private const val KEY_USER_ID = "user_id"

    private var preferences: SharedPreferences? = null

    var userId: Int = 0
    var token: String? = null

    fun init(context: Context) {
        preferences = context.getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE)
        token = preferences?.getString(KEY_TOKEN, null)

        userId = preferences?.getInt(KEY_USER_ID, 0) ?: 0
    }

    fun saveSession(token: String, userId: Int) {
        this.token = token
        this.userId = userId

        preferences?.edit()?.apply {
            putString(KEY_TOKEN, token)
            putInt(KEY_USER_ID, userId)
            apply()
        }
    }

    fun clearSession() {
        token = null
        userId = 0
        preferences?.edit()?.clear()?.apply()
    }

    fun isLoggedIn(): Boolean {
        return token != null
    }
}