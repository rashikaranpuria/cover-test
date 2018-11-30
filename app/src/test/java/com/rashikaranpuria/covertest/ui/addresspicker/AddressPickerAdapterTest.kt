package com.rashikaranpuria.covertest.ui.addresspicker

import android.content.Context
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import com.rashikaranpuria.covertest.BuildConfig
import com.rashikaranpuria.covertest.FakeApplication
import com.rashikaranpuria.covertest.data.api.model.PlacesResponse.PredictionsItem
import org.junit.Assert.assertEquals
import org.junit.Assert.assertTrue
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.InjectMocks
import org.mockito.Mock
import org.mockito.MockitoAnnotations
import org.robolectric.RobolectricTestRunner
import org.robolectric.RuntimeEnvironment
import org.robolectric.annotation.Config

@RunWith(RobolectricTestRunner::class)
@Config(constants = BuildConfig::class, application = FakeApplication::class, sdk = [21])
class AddressPickerAdapterTest {

    lateinit var context: Context

    @Mock
    lateinit var viewHolder: AddressPickerAdapter.ViewHolder

    @Mock
    lateinit var items: List<PredictionsItem>

    @InjectMocks
    lateinit var adapter: AddressPickerAdapter

    @Before
    fun setUp() {
        MockitoAnnotations.initMocks(this)
        adapter = AddressPickerAdapter()
        adapter.items = items
        context = RuntimeEnvironment.application
    }

    @Test
    fun itemCount() {
        val predictions = listOf(PredictionsItem(id = "1"), PredictionsItem(id = "2"), PredictionsItem(id = "3"))
        adapter.items = predictions
        assertEquals(adapter.itemCount, predictions.size)
    }

    @Test
    fun onCreateViewHolder_returnsCorrectViewHolder() {
        val predictions = listOf(PredictionsItem(id = "1"))
        adapter.items = predictions
        val rv = RecyclerView(context)
        rv.layoutManager = LinearLayoutManager(context)
        val viewHolder = adapter.onCreateViewHolder(rv, 0)
        assertTrue(viewHolder is AddressPickerAdapter.ViewHolder)
    }

//    @Test
//    fun djnf() {
//
//        items = listOf(PredictionsItem(id = "1"))
//
//        `when`(items.get(anyInt())).thenReturn(PredictionsItem(id = "4"))
//        val rv = RecyclerView(context)
//        rv.layoutManager = LinearLayoutManager(context)
//        viewHolder = adapter.onCreateViewHolder(rv, 0)
//
//        adapter.bindViewHolder(viewHolder, 0)
//        verify(viewHolder).bind(items[0])
//    }
}