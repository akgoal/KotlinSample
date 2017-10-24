package com.deakishin.hotelsapp.utils.extensions

import android.util.Log

/** Logs error message. */
fun <T : Any> T.logError(error: Throwable?, message: String? = null) {
    Log.e(javaClass.simpleName,
            message?.let { it + ": " } ?: ""
                    + error?.toString())
}

/** Logs info message. */
fun <T : Any> T.logInfo(message: String) {
    Log.i(javaClass.simpleName, message)
}

/** Logs debug message. */
fun <T : Any> T.logDebug(message: String) {
    Log.d(javaClass.simpleName, message)
}
