package com.kryptkode.deviceid.utils

import android.annotation.SuppressLint
import android.content.Context
import android.provider.Settings
import java.math.BigInteger
import java.security.MessageDigest
import java.security.NoSuchAlgorithmException
import java.util.*

class DeviceUtils  (private val context: Context){
    @SuppressLint("HardwareIds")
    fun getDeviceID(): String {
        return md5Hash(
            Settings.Secure.getString(
                context.contentResolver,
                "android_id"
            )
        ).toUpperCase(Locale.US)
    }

    private fun md5Hash(str: String): String {
        var instance: MessageDigest?
        try {
            instance = MessageDigest.getInstance("MD5")
        } catch (e: NoSuchAlgorithmException) {
            e.printStackTrace()
            instance = null
        }

        instance?.update(str.toByteArray(), 0, str.length)
        return BigInteger(1, instance?.digest()).toString(16)
    }
}