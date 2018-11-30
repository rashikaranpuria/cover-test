package com.rashikaranpuria.covertest.ui.main

import com.rashikaranpuria.covertest.ui.base.IBasePresenter

interface IMainPresenter<V : IMainActivityView> : IBasePresenter<V> {
    fun launchQuestionFlowButtonClicked()
}