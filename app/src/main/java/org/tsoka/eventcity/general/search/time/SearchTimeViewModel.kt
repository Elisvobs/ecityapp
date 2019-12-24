package org.tsoka.eventcity.general.search.time

import androidx.lifecycle.ViewModel
import org.tsoka.eventcity.general.data.Preference

const val SAVED_TIME = "TIME"

class SearchTimeViewModel(private val preference: Preference) : ViewModel() {

    fun saveTime(query: String) {
        preference.putString(SAVED_TIME, query)
    }
}
