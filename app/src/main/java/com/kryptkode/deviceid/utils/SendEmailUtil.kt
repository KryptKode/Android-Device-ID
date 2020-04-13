package com.kryptkode.deviceid.utils

import android.app.Activity
import android.content.Intent
import android.net.Uri
import com.kryptkode.deviceid.R


/**
 * Created by kryptkode on 2/26/2020.
 */
class SendEmailUtil(private val activity: Activity) {

    fun sendEmail(recipientEmail: String, subject: String, body: String) {
        val intent = Intent(
            Intent.ACTION_SENDTO, Uri.fromParts(
                "mailto", recipientEmail, null
            )
        )
        intent.putExtra(Intent.EXTRA_EMAIL, arrayOf(recipientEmail))
        intent.putExtra(Intent.EXTRA_SUBJECT, subject)
        intent.putExtra(Intent.EXTRA_TEXT, body)
        activity.startActivity(
            Intent.createChooser(
                intent,
                activity.getString(R.string.send_email_hint)
            )
        )
    }
}