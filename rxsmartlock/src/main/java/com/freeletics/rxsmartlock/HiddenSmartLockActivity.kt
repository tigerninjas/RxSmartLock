package com.freeletics.rxsmartlock

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.support.v4.app.FragmentActivity
import timber.log.Timber

class HiddenSmartLockActivity : FragmentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Timber.i(
            "SmartLock: HiddenSmartLockActivity::onCreate fresh=%b",
            savedInstanceState == null
        )
        if (savedInstanceState == null) {
            handleIntent(intent)
        }
    }

    override fun onNewIntent(intent: Intent) {
        handleIntent(intent)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        RxGoogleSmartLockManager.handleActivityResult(requestCode, resultCode, data)
    }

    private fun handleIntent(intent: Intent) {
        Timber.i("SmartLock: HiddenSmartLockActivity::handleIntent")
        RxGoogleSmartLockManager.performAction(this)
    }

    companion object {
        fun newIntent(context: Context, code: Int): Intent =
            Intent(context, HiddenSmartLockActivity::class.java)
    }
}