package de.moetz.android.timingiseverything.timereg

import android.app.DatePickerDialog
import android.content.Intent
import android.os.AsyncTask
import android.os.Bundle
import android.support.design.widget.FloatingActionButton
import android.text.Editable
import android.text.InputType
import android.util.Log
import android.widget.EditText
import android.widget.Toast
import de.moetz.android.timingiseverything.BaseActivity
import de.moetz.android.timingiseverything.R
import de.moetz.android.timingiseverything.database.AppDatabase
import java.text.SimpleDateFormat
import java.util.*


class AddTimeRegActivity : BaseActivity("Zeiterfassung") {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.timereg_add)

        initDateField(R.id.addtimereg_date)
        findViewById<FloatingActionButton>(R.id.addtimereg_savebutton).setOnClickListener { onSaveClicked() }
    }

    private fun initDateField(id: Int) {
        val dateField = findViewById<EditText>(id)
        dateField.inputType = InputType.TYPE_NULL

        val now = Calendar.getInstance()
        val datePicker = DatePickerDialog(this, onDatePicked(id), now.get(Calendar.YEAR), now.get(Calendar.MONTH), now.get(Calendar.DAY_OF_MONTH))

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

    private fun onDatePicked(id: Int) =  DatePickerDialog.OnDateSetListener { view, year, month, day ->
        val cal = Calendar.getInstance()
        cal.set(year, month, day)
        val time = SimpleDateFormat("dd.MM.yyyy").format(cal.time)
        findViewById<EditText>(id).setText(time)
    }

    private fun onSaveClicked() {
        val dateText = (findViewById<EditText>(R.id.addtimereg_date) as EditText).text.toString()
        if (dateText.isNullOrBlank()) {
            Toast.makeText(this, "Datum nicht valide", Toast.LENGTH_SHORT).show()
            return
        }
        val date = stringToDate(dateText)

        val project = (findViewById<EditText>(R.id.addtimereg_project) as EditText).text.toString()
        if (project.isNullOrBlank()) {
            Toast.makeText(this, "Projekt nicht valide", Toast.LENGTH_SHORT).show()
            return
        }

        val timeText = (findViewById<EditText>(R.id.addtimereg_time) as EditText).text.toString()
        if (timeText.isNullOrBlank()) {
            Toast.makeText(this, "Zeit nicht valide", Toast.LENGTH_SHORT).show()
            return
        }
        val time = timeText.toDouble()

        var remarks = (findViewById<EditText>(R.id.addtimereg_remarks) as EditText).text.toString()
        if (remarks.isNullOrBlank()) remarks = ""

        val timereg = TimeRegistration(date,  project,  time, remarks)
        AsyncTask.execute({
            AppDatabase.get().timeregDao().insert(timereg)
            startActivity(Intent(this, TimeRegsActivity::class.java))
        })
    }

    private fun stringToDate(date: String): Date {
        val format = SimpleDateFormat("dd.MM.yyyy", Locale.GERMANY)
        return format.parse(date)
    }

}
