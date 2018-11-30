package com.rashikaranpuria.covertest.ui.base

import android.support.v7.app.AppCompatActivity
import org.jetbrains.anko.toast

open class BaseActivity : AppCompatActivity(), IBaseView {

    override fun showError(errorStr: String) {
        toast(errorStr)
    }

    override fun showError(errorId: Int) {
        toast(getString(errorId))
    }

    override fun showMessage(messageStr: String) {
        toast(messageStr)
    }

    override fun showMessage(messageId: Int) {
        toast(getString(messageId))
    }

    override fun showError(errorId: Int, localizedMessage: String?) {
        toast(getString(errorId, localizedMessage))
    }
}