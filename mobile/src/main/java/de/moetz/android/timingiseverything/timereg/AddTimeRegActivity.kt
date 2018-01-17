package de.moetz.android.timingiseverything.timereg

import android.app.DatePickerDialog
import android.content.Intent
import android.databinding.DataBindingUtil
import android.os.AsyncTask
import android.os.Bundle
import android.support.design.widget.FloatingActionButton
import android.support.v7.app.AppCompatActivity
import android.text.InputType
import android.util.Log
import android.widget.EditText
import de.moetz.android.timingiseverything.BR
import de.moetz.android.timingiseverything.R
import de.moetz.android.timingiseverything.database.AppDatabase
import de.moetz.android.timingiseverything.databinding.TimeregAddBinding
import org.joda.time.LocalDate


class AddTimeRegActivity : AppCompatActivity() {

    val timereg = TimeRegistration.default()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding: TimeregAddBinding = DataBindingUtil.setContentView(this, R.layout.timereg_add)
        binding.setVariable(BR.timereg, timereg)
        binding.executePendingBindings()

        initDateField(R.id.addtimereg_date)
        findViewById<FloatingActionButton>(R.id.addtimereg_savebutton).setOnClickListener { onSaveClicked() }
    }

    private fun initDateField(id: Int) {
        val dateField = findViewById<EditText>(id)
        dateField.inputType = InputType.TYPE_NULL

        val datePicker = DatePickerDialog(this, onDatePicked(id),
                LocalDate.now().year, LocalDate.now().monthOfYear - 1, LocalDate.now().dayOfMonth)

        dateField.setOnFocusChangeListener { view, b -> if (b) this.openDatePickerIfNotOpened(datePicker) }
        dateField.setOnClickListener({ this.openDatePickerIfNotOpened(datePicker) })
    }

    private fun openDatePickerIfNotOpened(datePicker: DatePickerDialog) {
        if (!datePicker.isShowing) {
            datePicker.show()
            Log.d("AddTimeregistration", "DatePicker opened")
        } else {
            Log.d("AddTimeregistration", "DatePicker already open")
        }
    }

    private fun onDatePicked(id: Int) = DatePickerDialog.OnDateSetListener { view, year, month, day ->
        Log.d("AddTimeReg", "Year: $year, Month: $month, day: $day")
        val date = LocalDate(year, month + 1, day)
        Log.d("AddTimeReg", "LocalDate: $date")
        findViewById<EditText>(id).setText(date.toString("dd.MM.yyyy"))
    }

    private fun onSaveClicked() {
        if (this.timereg.validate()) {
            AsyncTask.execute({
                AppDatabase.get().timeregDao().insert(this.timereg)
                startActivity(Intent(this, TimeRegsActivity::class.java))
            })
        }
    }

}
