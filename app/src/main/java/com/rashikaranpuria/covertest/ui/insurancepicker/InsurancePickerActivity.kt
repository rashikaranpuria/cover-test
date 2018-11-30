package com.rashikaranpuria.covertest.ui.insurancepicker

import android.content.Intent
import android.os.Bundle
import android.view.MenuItem
import android.widget.ArrayAdapter
import com.rashikaranpuria.covertest.CoverApplication
import com.rashikaranpuria.covertest.R
import com.rashikaranpuria.covertest.di.module.InsurancePickerModule
import com.rashikaranpuria.covertest.ui.base.BaseActivity
import com.rashikaranpuria.covertest.ui.main.MainActivity
import kotlinx.android.synthetic.main.activity_insurance_picker.*
import org.jetbrains.anko.alert
import org.jetbrains.anko.sdk25.coroutines.onClick
import org.jetbrains.anko.yesButton
import javax.inject.Inject

class InsurancePickerActivity : BaseActivity(), IInsurancePickerView {

    @Inject
    lateinit var insurancePickerPresenter: IInsurancePickerPresenter<IInsurancePickerView>

    @Inject
    lateinit var insurancePickerAdapter: ArrayAdapter<String>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_insurance_picker)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.title = getString(R.string.insurance_picker_activity_title)
        (application as CoverApplication).appComponent.insurancePickerComponent(InsurancePickerModule(this)).inject(this)
        insurancePickerPresenter.onAttach(this)
        initAdapter()
        initClickListeners()
    }

    override fun initAdapter() {
        insurer_autocomplete.threshold = 1
        insurer_autocomplete.setAdapter(insurancePickerAdapter)
    }

    override fun initClickListeners() {

        next_button.onClick {
            insurancePickerPresenter.nextButtonClicked(insurer_autocomplete.text.toString())
        }
    }

    override fun showNoOptionSelectedError() {
        alert(R.string.invalid_option_selected, R.string.no_option_error_title) {
            yesButton { }
        }.show()
    }

    override fun launchMainActivity() {
        val intent = Intent(this@InsurancePickerActivity, MainActivity::class.java)
        intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK
        startActivity(intent)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            // Respond to the action bar's Up/Home button
            android.R.id.home -> {
                super.onBackPressed()
                return true
            }
        }
        return super.onOptionsItemSelected(item)
    }

    override fun onDestroy() {
        super.onDestroy()
        insurancePickerPresenter.onDetach()
    }
}