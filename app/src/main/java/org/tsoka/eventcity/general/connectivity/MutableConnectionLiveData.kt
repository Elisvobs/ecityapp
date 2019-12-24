package org.tsoka.eventcity.general.connectivity

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.content.IntentFilter
import android.net.ConnectivityManager
import androidx.lifecycle.MutableLiveData
import org.tsoka.eventcity.general.OpenEventGeneral
import org.tsoka.eventcity.general.utils.Utils

class MutableConnectionLiveData : MutableLiveData<Boolean>() {
    private val context by lazy {
        OpenEventGeneral.appContext
    }

    private val broadcastReceiver = object : BroadcastReceiver() {
        override fun onReceive(context: Context?, intent: Intent?) {
            postValue(Utils.isNetworkConnected(context))
        }
    }

    override fun onActive() {
        super.onActive()
        val intentFilter = IntentFilter(ConnectivityManager.CONNECTIVITY_ACTION)
        context?.registerReceiver(broadcastReceiver, intentFilter)
    }

    override fun onInactive() {
        super.onInactive()
        context?.unregisterReceiver(broadcastReceiver)
    }
}
