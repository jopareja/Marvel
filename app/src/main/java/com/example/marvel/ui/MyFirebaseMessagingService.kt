package com.example.marvel.ui

import android.util.Log
import com.google.firebase.messaging.FirebaseMessagingService

/**
 * Al iniciar la APP, el FCM genera un token de la instancia de la app.
 * Si queremos enviar notificaciones a dispositivos o grupos particulares,
 * vamos a necesitar acceder a dichos tokens con "onNewToken"
 */
class MyFirebaseMessagingService : FirebaseMessagingService() {

    override fun onNewToken(token: String) {
        Log.d("JOSE", "Latest token: $token")
        super.onNewToken(token)
        // Insertar método para enviar token a servidor
    }

}