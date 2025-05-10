package com.example.onyxdelivery.core.session

import androidx.lifecycle.DefaultLifecycleObserver
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleOwner

class SessionManager(
    private val onSessionExpired: () -> Unit
) : DefaultLifecycleObserver {

    private var backgroundTime: Long = 0
    private val timeoutMillis = 2 * 60 * 1000L

    override fun onStop(owner: LifecycleOwner) {
        backgroundTime = System.currentTimeMillis()
    }

    override fun onStart(owner: LifecycleOwner) {
        val now = System.currentTimeMillis()
        if (now - backgroundTime > timeoutMillis) {
            onSessionExpired()
        }
    }

    fun startTracking(lifecycle: Lifecycle) {
        lifecycle.addObserver(this)
    }
}


