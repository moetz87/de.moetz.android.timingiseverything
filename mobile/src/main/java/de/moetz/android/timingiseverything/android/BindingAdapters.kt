package de.moetz.android.timingiseverything.android

import android.databinding.BindingAdapter
import android.databinding.InverseBindingAdapter
import android.support.annotation.NonNull
import android.widget.EditText
import org.joda.time.LocalDate
import org.joda.time.format.DateTimeFormat

@BindingAdapter("android:text")
fun setLocalDate(editText: EditText, value: LocalDate) {
    editText.setText(value.toString("dd.MM.yyyy"))
}

@InverseBindingAdapter(attribute = "android:text")
fun getLocalDate(editText: EditText): LocalDate {
    val date = editText.text.toString()
    return DateTimeFormat.forPattern("dd.MM.yyyy").parseLocalDate(date)
}

@BindingAdapter("android:text")
fun setDouble(@NonNull editText: EditText, value: Double) {
    editText.setText(value.toString())
}

@InverseBindingAdapter(attribute = "android:text")
fun getDouble(editText: EditText): Double {
    val date = editText.text.toString()
    return if (date.isBlank()) 0.0 else date.toDouble()
}
