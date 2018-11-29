package com.rashikaranpuria.covertest.ui.main

import android.content.Intent
import android.os.Bundle
import com.rashikaranpuria.covertest.CoverApplication
import com.rashikaranpuria.covertest.R
import com.rashikaranpuria.covertest.di.module.MainActivityModule
import com.rashikaranpuria.covertest.ui.addresspicker.AddressPickerActivity
import com.rashikaranpuria.covertest.ui.base.BaseActivity
import kotlinx.android.synthetic.main.activity_main.*
import org.jetbrains.anko.sdk25.coroutines.onClick
import javax.inject.Inject

class MainActivity: BaseActivity(), IMainActivityView {

    @Inject
    lateinit var mainActivityPresenter: IMainPresenter<IMainActivityView>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        (application as CoverApplication).appComponent.mainActivityComponent(MainActivityModule()).inject(this)
        mainActivityPresenter.onAttach(this)
        initClickListeners()
    }

    override fun initClickListeners() {
        launch_question_flow_button.onClick {
            mainActivityPresenter.launchQuestionFlowButtonClicked()
        }
    }

    override fun launchAddressPickerActivity() {
        startActivity(Intent(this@MainActivity, AddressPickerActivity::class.java))
    }

    override fun onDestroy() {
        super.onDestroy()
        mainActivityPresenter.onDetach()
    }
}