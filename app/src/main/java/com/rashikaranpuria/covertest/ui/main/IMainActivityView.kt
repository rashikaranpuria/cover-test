package com.rashikaranpuria.covertest.ui.main

import com.rashikaranpuria.covertest.ui.base.IBaseView

interface IMainActivityView: IBaseView {
    fun initClickListeners()
    fun launchAddressPickerActivity()
}