package com.example.onyxdelivery

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.lifecycle.ProcessLifecycleOwner
import com.example.onyxdelivery.core.session.SessionManager
import com.example.onyxdelivery.presentation.navigation.OnyxNavHost
import com.example.onyxdelivery.presentation.session.SessionViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    private lateinit var sessionManager: SessionManager

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        val sessionViewModel: SessionViewModel by viewModels()

        sessionManager = SessionManager {
            sessionViewModel.expireSession()
        }

        sessionManager.startTracking(ProcessLifecycleOwner.get().lifecycle)

        setContent {
            OnyxNavHost(sessionViewModel)
        }
    }
}