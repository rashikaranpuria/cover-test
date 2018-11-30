package com.rashikaranpuria.covertest.ui.addresspicker

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.rashikaranpuria.covertest.ItemClickListener
import com.rashikaranpuria.covertest.R
import com.rashikaranpuria.covertest.data.api.model.PlacesResponse.PredictionsItem
import kotlin.properties.Delegates

class AddressPickerAdapter : RecyclerView.Adapter<AddressPickerAdapter.ViewHolder>(), AutoUpdatableAdapter {

    var items: List<PredictionsItem> by Delegates.observable(emptyList()) {
            _, oldList, newList ->
        autoNotify(oldList, newList) { o, n -> o == n }
    }

    var itemClickListener: ItemClickListener<PredictionsItem>? = null

    override fun onCreateViewHolder(parent: ViewGroup, p1: Int): ViewHolder =
        ViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.address_selector, parent, false))

    override fun onBindViewHolder(holder: ViewHolder, p1: Int) {
        holder.bind(items.get(holder.adapterPosition))
    }

    override fun getItemCount(): Int = items.size

    inner class ViewHolder(var v: View) : RecyclerView.ViewHolder(v) {
        var parentView: View = v.findViewById(R.id.parent)
        var primaryTextView: TextView = v.findViewById(R.id.primary_text)
        var secondaryTextView: TextView = v.findViewById(R.id.secondary_text)

        fun bind(predictionsItem: PredictionsItem?) {
            primaryTextView.text = predictionsItem?.structuredFormatting?.mainText
            secondaryTextView.text = predictionsItem?.structuredFormatting?.secondaryText
//            parentView.backgroundColor = R.color.white
            parentView.setOnClickListener {
                if (predictionsItem != null) {
                    itemClickListener?.onItemClicked(predictionsItem)
                }
            }
        }
    }
}