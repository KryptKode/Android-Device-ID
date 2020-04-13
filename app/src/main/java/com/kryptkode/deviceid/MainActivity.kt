package com.kryptkode.deviceid

import android.os.Bundle
import com.kryptkode.deviceid.base.BaseActivity
import com.kryptkode.deviceid.databinding.ActivityMainBinding
import com.kryptkode.deviceid.utils.DeviceUtils
import com.kryptkode.deviceid.utils.SendEmailUtil
import com.kryptkode.deviceid.utils.extensions.observe

class MainActivity : BaseActivity<ActivityMainBinding, MainViewModel>(MainViewModel::class) {

    private val sendEmailUtil by lazy {
        SendEmailUtil(this)
    }

    private val deviceId by lazy {
        DeviceUtils(this).getDeviceID()
    }

    override fun getLayoutResourceId() = R.layout.activity_main

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        initObservers()
        viewModel.onInit(deviceId)
    }

    private fun initObservers() {

        viewModel.getCloseAppEvent().observe(this) { event ->
            event?.getContentIfNotHandled()?.let {
                onBackPressed()
            }
        }

        viewModel.getContactAdminEvent().observe(this) { event ->
            event?.getContentIfNotHandled()?.let {
                sendDeviceIdByEmail()
            }
        }

    }

    private fun sendDeviceIdByEmail() {
        sendEmailUtil.sendEmail(
                getString(R.string.send_email_recipient),
                getString(R.string.send_email_subject),
                getString(R.string.send_email_body, deviceId)
        )
    }
}
