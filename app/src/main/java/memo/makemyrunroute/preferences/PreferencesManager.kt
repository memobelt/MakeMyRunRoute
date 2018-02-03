package memo.makemyrunroute.preferences

import android.content.Context

object PreferencesManager {
    val SHARED_PREFERENCES = "SHARED_PREF"
    val DISTANCE_TYPE = "DISATNCE_TYPE"
    val DISTANCE_AMOUNT = "DISTANCE_AMOUNT"

    object Direction {
        val MILE = "MILE"
        val KILOMETER = "KILOMETER"
    }

    fun saveDistanceType(context: Context, direction: String) {
        val editor = context.getSharedPreferences(SHARED_PREFERENCES, Context.MODE_PRIVATE).edit()
        editor.putString(DISTANCE_TYPE, direction)
        editor.apply()
    }

    fun getDistanceType(context: Context): String {
        return context.getSharedPreferences(SHARED_PREFERENCES, Context.MODE_PRIVATE).getString(DISTANCE_TYPE, Direction.MILE)
    }

    fun saveLastDistanceAmount(context: Context, distance: String) {
        val editor = context.getSharedPreferences(SHARED_PREFERENCES, Context.MODE_PRIVATE).edit()
        editor.putString(DISTANCE_AMOUNT, distance)
        editor.apply()
    }

    fun getLastDistanceAmount(context: Context) : String {
        return context.getSharedPreferences(SHARED_PREFERENCES, Context.MODE_PRIVATE).getString(DISTANCE_AMOUNT, "")
    }
}