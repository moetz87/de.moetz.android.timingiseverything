package de.moetz.android.timingiseverything.timereg

import android.content.Context
import android.content.Intent
import android.os.AsyncTask
import android.os.Bundle
import android.support.design.widget.FloatingActionButton
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.BaseAdapter
import android.widget.ListView
import android.widget.TextView
import de.moetz.android.timingiseverything.BaseActivity
import de.moetz.android.timingiseverything.R
import de.moetz.android.timingiseverything.database.AppDatabase


class TimeRegsActivity : BaseActivity("Zeitenverwaltung") {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.timeregs)

        AsyncTask.execute({
            this.updateView(AppDatabase.get().timeregDao().get())
        })
        findViewById<FloatingActionButton>(R.id.add_timereg_button).setOnClickListener {
            startActivity(Intent(this, AddTimeRegActivity::class.java))
        }
    }

    private fun updateView(timeregs: List<TimeRegistration>) {
        runOnUiThread({
            val listview = findViewById<ListView>(R.id.timeregs_list)
            listview.adapter = TimeRegsListAdapter(this, timeregs)
            listview.onItemLongClickListener = ItemClickListener()
        })
    }

    private class TimeRegsListAdapter(context: Context, private val timeregs: List<TimeRegistration>) : BaseAdapter() {

        private val inflator: LayoutInflater = LayoutInflater.from(context)

        override fun getCount(): Int {
            return this.timeregs.size
        }

        override fun getItemId(position: Int): Long {
            return this.timeregs[position].id.toLong()
        }

        override fun getItem(position: Int): Any {
            return this.timeregs[position]
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
            this.date.text = timereg.date.toString("dd.MM.yyyy")
            this.project.text = timereg.project
            this.time.text = "${timereg.time}"
            this.remarks.text = timereg.remarks
        }

    }

    private class ItemClickListener : AdapterView.OnItemLongClickListener {

        override fun onItemLongClick(parent: AdapterView<*>?, view: View?, position: Int, id: Long): Boolean {
            Log.d("TimeRegistration Click", "Position: ${position}, ID: ${id}")
            return true;
        }

    }

}
