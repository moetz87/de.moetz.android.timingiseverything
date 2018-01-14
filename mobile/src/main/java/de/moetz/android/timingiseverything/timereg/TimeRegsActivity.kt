package de.moetz.android.timingiseverything.timereg

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.ListView
import android.widget.TextView
import de.moetz.android.timingiseverything.BaseActivity
import de.moetz.android.timingiseverything.R
import java.util.*
import java.text.SimpleDateFormat


class TimeRegsActivity : BaseActivity("Zeiten-Management") {

    private val timeregs = listOf(
            TimeRegistration("MOE", Date(), "QES", 4.0, "abc"),
            TimeRegistration("MOE", Date(), "OPB2", 4.5, "def"),
            TimeRegistration("MOE", Date(), "OPB2", 5.0, "ghi"),
            TimeRegistration("MOE", Date(), "QES", 12.3, "")
    )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.timeregs)
        val lv = findViewById<ListView>(R.id.timeregs_list)
        lv.adapter = TimeRegsListAdapter(this, this.timeregs)
    }

    private class TimeRegsListAdapter(context: Context, private val timeregs: List<TimeRegistration>) : BaseAdapter() {

        private val inflator: LayoutInflater = LayoutInflater.from(context)

        override fun getCount(): Int {
            return this.timeregs.size
        }

        override fun getItemId(position: Int): Long {
            return position.toLong()
        }

        override fun getItem(position: Int): Any {
            return this.timeregs.get(position)
        }

        override fun getView(position: Int, convertView: View?, parent: ViewGroup): View? {
            val view = this.inflator.inflate(R.layout.timeregs_row, parent, false)
            val viewHolder = ListRowHolder(view, this.timeregs[position])
            view.tag = viewHolder
            return view
        }

    }

    private class ListRowHolder(row: View, timereg: TimeRegistration) {

        val date: TextView = row.findViewById(R.id.timereglist_date)
        val project: TextView = row.findViewById(R.id.timereglist_project)
        val time: TextView = row.findViewById(R.id.timereglist_time)
        val remarks: TextView = row.findViewById(R.id.timereglist_remarks)

        init {
            this.date.text = formatDate(timereg.date)
            this.project.text = timereg.project
            this.time.text = "${timereg.time}"
            this.remarks.text = timereg.remarks
        }

        private fun formatDate(date: Date): String {
            val formatter = SimpleDateFormat("dd.MM.YYYY")
            return formatter.format(date)
        }

    }

}
