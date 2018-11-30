package com.rashikaranpuria.covertest.ui.base

interface IBaseView {
    fun showError(errorStr: String)
    fun showError(errorId: Int)
    fun showMessage(messageStr: String)
    fun showMessage(messageId: Int)
    fun showError(errorId: Int, localizedMessage: String?)
}