package de.moetz.android.timingiseverything.view

import android.databinding.BindingAdapter
import android.databinding.InverseBindingAdapter
import android.databinding.InverseBindingListener
import android.databinding.ObservableArrayList
import android.util.Log
import android.view.View
import android.widget.*
import de.moetz.android.timingiseverything.BR
import de.moetz.android.timingiseverything.project.Project
import de.moetz.android.timingiseverything.timereg.TimeRegistration
import de.moetz.android.timingiseverything.view.list.ListAdapter
import de.moetz.android.timingiseverything.view.list.ListViewItem
import org.joda.time.LocalDate
import org.joda.time.LocalDateTime
import org.joda.time.format.DateTimeFormat


@BindingAdapter("android:text")
fun setLocalDate(view: EditText, value: LocalDate) {
    view.setText(value.toString("dd.MM.yyyy"))
}

@InverseBindingAdapter(attribute = "android:text")
fun getLocalDate(view: EditText): LocalDate {
    val date = view.text.toString()
    return DateTimeFormat.forPattern("dd.MM.yyyy").parseLocalDate(date)
}

@BindingAdapter("android:text")
fun setLocalDate(view: TextView, value: LocalDate) {
    view.setText(value.toString("dd.MM.yyyy"))
}

@InverseBindingAdapter(attribute = "android:text")
fun getLocalDate(view: TextView): LocalDate {
    val date = view.text.toString()
    return DateTimeFormat.forPattern("dd.MM.yyyy").parseLocalDate(date)
}

@BindingAdapter("android:text")
fun setLocalDateTime(view: TextView, value: LocalDateTime?) {
    view.text = value?.toString("dd.MM.yyyy, hh:mm")
}

@InverseBindingAdapter(attribute = "android:text")
fun getLocalDateTime(view: TextView): LocalDateTime {
    val date = view.text.toString()
    return DateTimeFormat.forPattern("dd.MM.yyyy, hh:mm").parseLocalDateTime(date)
}

@BindingAdapter("android:text")
fun setDouble(view: EditText, value: Double) {
    view.setText(value.toString())
}

@InverseBindingAdapter(attribute = "android:text")
fun getDouble(view: EditText): Double {
    val date = view.text.toString()
    return if (date.isBlank()) 0.0 else date.toDouble()
}

@BindingAdapter("android:text")
fun setDouble(view: TextView, value: Double) {
    view.setText(value.toString())
}

@InverseBindingAdapter(attribute = "android:text")
fun getDouble(view: TextView): Double {
    val date = view.text.toString()
    return if (date.isBlank()) 0.0 else date.toDouble()
}

@BindingAdapter("entries", "layout")
fun setListToListView(view: ListView, list: ObservableArrayList<TimeRegistration>, rowLayoudId: Int) {
    view.adapter = ListAdapter(rowLayoudId, BR.row, list)
}

@BindingAdapter("entries", "layout")
fun setProjectListToListView(view: ListView, list: ObservableArrayList<Project>, rowLayoudId: Int) {
    view.adapter = ListAdapter(rowLayoudId, BR.row, list)
}

@BindingAdapter("entries", "layout")
fun setListToSpinner(view: Spinner, list: ObservableArrayList<Project>, rowLayoudId: Int) {
    view.adapter = ListAdapter(rowLayoudId, BR.row, list)
}

@BindingAdapter("android:visibility")
fun setVisibility(view: View, show: Boolean) {
    view.visibility = if (show) View.VISIBLE else View.GONE
}

@BindingAdapter(value = *arrayOf("selected", "selectedAttrChanged"), requireAll = false)
fun bindSpinnerData(view: Spinner, newSelected: Project?, selectedAttrChanged: InverseBindingListener) {
    view.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
        override fun onItemSelected(parent: AdapterView<*>, view: View, position: Int, id: Long) {
            if (newSelected != null && newSelected == parent.selectedItem) {
                return
            }
            selectedAttrChanged.onChange()
        }

        override fun onNothingSelected(parent: AdapterView<*>) {}
    }
    Log.d("BindingAdapters", "bindSpinnerData called. newSelected: $newSelected")
    if (newSelected != null) {
        val position = (view.adapter as ListAdapter).elements.indexOf(newSelected)
        view.setSelection(position)
    }
}

@InverseBindingAdapter(attribute = "selected", event = "selectedAttrChanged")
fun captureSelectedValue(view: Spinner): Project {
    Log.d("BindingAdapters", "captureSelectedValue called.")
    return view.selectedItem as Project
}
