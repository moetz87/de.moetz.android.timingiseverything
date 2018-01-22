package de.moetz.android.timingiseverything.timereg

import android.app.DatePickerDialog
import android.content.Intent
import android.databinding.ViewDataBinding
import android.os.AsyncTask
import android.os.Bundle
import android.text.InputType
import android.util.Log
import android.view.View
import android.widget.EditText
import de.moetz.android.timingiseverything.BR
import de.moetz.android.timingiseverything.BaseActivity
import de.moetz.android.timingiseverything.R
import de.moetz.android.timingiseverything.database.AppDatabase
import org.joda.time.LocalDate


class AddTimeRegActivity : BaseActivity("Zeiterfassung") {

    var model = AddTimeRegActivityModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        this.setContentView(R.layout.timereg_add)

        initDateField(R.id.addtimereg_date)
    }

    override fun bindData(binding: ViewDataBinding) {
        binding.setVariable(BR.model, this.model)
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

    fun onSaveClicked(view: View) {
        if (this.model.validateTimereg()) {
            AsyncTask.execute({
                AppDatabase.get().timeregDao().insert(this.model.timereg!!)
                startActivity(Intent(this, TimeRegsActivity::class.java))
            })
        }
    }

}
