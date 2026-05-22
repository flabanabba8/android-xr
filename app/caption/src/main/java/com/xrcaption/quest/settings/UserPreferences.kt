package com.xrcaption.quest.settings

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.intPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

private val Context.dataStore: DataStore<Preferences> by preferencesDataStore(name = "settings")

class UserPreferences(private val context: Context) {

    companion object {
        private val FONT_SIZE = intPreferencesKey("font_size")
    }

    val fontSize: Flow<Int> = context.dataStore.data.map { prefs ->
        prefs[FONT_SIZE] ?: 24
    }

    suspend fun setFontSize(size: Int) {
        context.dataStore.edit { prefs ->
            prefs[FONT_SIZE] = size
        }
    }
}
