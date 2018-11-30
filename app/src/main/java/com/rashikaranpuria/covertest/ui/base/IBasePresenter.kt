package com.rashikaranpuria.covertest.ui.base

interface IBasePresenter<V : IBaseView> {
    fun onAttach(v: V)

    fun onDetach()
}