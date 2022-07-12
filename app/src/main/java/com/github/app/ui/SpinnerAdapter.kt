package com.github.app.ui

import android.content.Context
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import com.github.app.ui.common.BaseFragment

class SpinnerAdapter(
    context: Context,
    val id: Int = android.R.layout.simple_spinner_dropdown_item,
    private val items: List<FragmentItem>
): ArrayAdapter<SpinnerAdapter.FragmentItem>(context, id, items),
    AdapterView.OnItemSelectedListener {
    var onItemSelected: ((Pair<String, BaseFragment>) -> Unit)? = null

    override fun onItemSelected(
        parent: AdapterView<*>?,
        view: View?,
        position: Int,
        id: Long
    ) {
        with(items[position]) {
            onItemSelected?.invoke(Pair(name, fragment))
        }
    }

    override fun onNothingSelected(parent: AdapterView<*>?) {
    }

    data class FragmentItem (
        val fragment: BaseFragment,
        val name: String
    ) {
        override fun toString(): String {
            return name
        }
    }
}