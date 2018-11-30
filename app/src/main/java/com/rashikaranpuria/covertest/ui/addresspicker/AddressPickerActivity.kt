package com.rashikaranpuria.covertest.ui.addresspicker

import android.content.Intent
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.MenuItem
import com.jakewharton.rxbinding.view.RxView
import com.jakewharton.rxbinding.widget.RxTextView
import com.rashikaranpuria.covertest.CoverApplication
import com.rashikaranpuria.covertest.ItemClickListener
import com.rashikaranpuria.covertest.R
import com.rashikaranpuria.covertest.data.api.model.PlacesResponse.PredictionsItem
import com.rashikaranpuria.covertest.di.module.AddressPickerModule
import com.rashikaranpuria.covertest.ui.base.BaseActivity
import com.rashikaranpuria.covertest.ui.insurancepicker.InsurancePickerActivity
import kotlinx.android.synthetic.main.activity_address_picker.*
import org.jetbrains.anko.alert
import org.jetbrains.anko.yesButton
import rx.Subscription
import rx.android.schedulers.AndroidSchedulers
import java.util.concurrent.TimeUnit
import javax.inject.Inject

class AddressPickerActivity : BaseActivity(), IAddressPickerView {

    @Inject
    lateinit var addressPickerPresenter: IAddressPickerPresenter<IAddressPickerView>

    @Inject
    lateinit var addressPickerAdapter: AddressPickerAdapter

    lateinit var addressPickerTextChangeSubscription: Subscription
    lateinit var nextButtonClickSubscription: Subscription

    lateinit var addressItemClickListener: ItemClickListener<PredictionsItem>

    override var selectedItem: PredictionsItem? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_address_picker)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.title = getString(R.string.address_picker_activity_title)
        (application as CoverApplication).appComponent.addressPickerComponent(AddressPickerModule(this)).inject(this)
        addressPickerPresenter.onAttach(this)

        initClickListeners()
        initAddressPickerAdapter()
    }

    override fun initAddressPickerAdapter() {
        address_suggestion_recycler.layoutManager = LinearLayoutManager(this) as RecyclerView.LayoutManager?
        addressPickerAdapter.items = listOf()
        addressPickerAdapter.itemClickListener = addressItemClickListener
        address_suggestion_recycler.adapter = addressPickerAdapter
    }

    override fun setAddressSuggestionInAdapter(predictions: List<PredictionsItem>) {
        addressPickerAdapter.items = predictions
    }

    override fun initClickListeners() {

        addressPickerTextChangeSubscription = RxTextView.textChanges(address_autocomplete)
            .doOnEach {
                // tell presenter that text changed
                addressPickerPresenter.notifyTextChanged()
            }
            .filter { cs: CharSequence? -> !cs.isNullOrEmpty() && cs!!.length > 1 }
            .debounce(400, TimeUnit.MILLISECONDS)
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(
                {
                    // on complete
                    addressPickerPresenter.notifyDelayedDebouncedTextChange(it.toString())
                },
                {
                    // on error
                    showError("ERROR: ${it.message}")
                }
            )

        nextButtonClickSubscription = RxView.clicks(next_button).subscribe {
            addressPickerPresenter.nextButtonClicked(address_autocomplete.text.toString())
        }

        addressItemClickListener = object : ItemClickListener<PredictionsItem> {

            override fun onItemClicked(selectedItem: PredictionsItem) {
                addressPickerPresenter.addressPickerItemClicked(selectedItem)
            }
        }
    }

    override fun clearCurrentSuggestions() {
        addressPickerAdapter.items = listOf()
    }

    override fun showSelectedItem() {
        address_selection_text1.text = selectedItem?.structuredFormatting?.mainText
        address_selection_text2.text = selectedItem?.structuredFormatting?.secondaryText
    }

    override fun removeSelectedItem() {
        selectedItem = null
        address_selection_text1.text = ""
        address_selection_text2.text = ""
    }

    override fun launchInsurancePickerActivity() {
        val intent = Intent(this@AddressPickerActivity, InsurancePickerActivity::class.java)
        startActivity(intent)
    }

    override fun showNoOptionSelectedError() {
        alert(R.string.invalid_option_selected, R.string.no_option_error_title) {
            yesButton {}
        }.show()
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
        addressPickerTextChangeSubscription.unsubscribe()
        nextButtonClickSubscription.unsubscribe()
        addressPickerPresenter.onDetach()
    }
}
