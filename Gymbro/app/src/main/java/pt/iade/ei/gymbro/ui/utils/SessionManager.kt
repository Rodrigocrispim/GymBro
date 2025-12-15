package pt.iade.ei.gymbro.ui.utils

import android.content.Context
import android.content.SharedPreferences

object SessionManager {
    private const val PREF_NAME = "GymBroSession"
    private const val KEY_TOKEN = "auth_token"
    private const val KEY_USER_ID = "user_id"

    private lateinit var prefs: SharedPreferences

    fun init(context: Context) {
        prefs = context.getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE)
    }

    // ✅ ISTO RESOLVE O SEGUNDO ERRO (Aceita o userId como argumento)
    fun saveSession(token: String?, userId: Int?) {
        val editor = prefs.edit()
        editor.putString(KEY_TOKEN, token)
        if (userId != null) {
            editor.putInt(KEY_USER_ID, userId)
        }
        editor.apply()
    }

    val token: String?
        get() = prefs.getString(KEY_TOKEN, null)

    val userId: Int
        get() = prefs.getInt(KEY_USER_ID, 0)

    fun clearSession() {
        prefs.edit().clear().apply()
    }

    fun isLoggedIn(): Boolean {
        return !token.isNullOrEmpty() && userId != 0
    }
}