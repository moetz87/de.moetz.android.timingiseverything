package de.moetz.android.timingiseverything.view

import android.databinding.BindingAdapter
import android.databinding.InverseBindingAdapter
import android.databinding.ObservableArrayList
import android.view.View
import android.widget.EditText
import android.widget.ListView
import android.widget.TextView
import de.moetz.android.timingiseverything.BR
import de.moetz.android.timingiseverything.view.list.ListAdapter
import de.moetz.android.timingiseverything.view.list.ListViewItem
import de.moetz.android.timingiseverything.view.model.StringHolder
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
fun setTimeRegistrationList(view: ListView, list: ObservableArrayList<ListViewItem>, rowLayoudId: Int) {
    view.adapter = ListAdapter(rowLayoudId, BR.row, list)
}

@BindingAdapter("android:visibility")
fun setVisibility(view: View, show: Boolean) {
    view.visibility = if (show) View.VISIBLE else View.GONE
}

@BindingAdapter("android:text")
fun setObjectHolderString(view: EditText, value: StringHolder) {
    view.setText(value.value)
}

@InverseBindingAdapter(attribute = "android:text")
fun getObjectHolderString(view: EditText): StringHolder {
    return StringHolder(view.text.toString())
}