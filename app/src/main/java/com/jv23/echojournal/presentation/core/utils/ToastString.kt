package com.jv23.echojournal.presentation.core.utils

import android.content.Context
import android.widget.Toast

fun Context.showToastStr(message: String) {
    Toast.makeText(this, message, Toast.LENGTH_LONG).show()
}