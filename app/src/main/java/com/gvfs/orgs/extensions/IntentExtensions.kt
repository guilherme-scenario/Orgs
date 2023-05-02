package com.gvfs.orgs.extensions

import android.content.Intent
import android.os.Parcelable
import java.io.Serializable

fun Intent.putExtra(key:String, value: Any?) {
    when (value) {
        is String -> putExtra(key, value)
        is Parcelable -> putExtra(key, value)
        else -> throw IllegalArgumentException("Unsupported argument type")
    }
}