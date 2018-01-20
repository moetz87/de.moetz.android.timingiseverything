package de.moetz.android.timingiseverything.view;

import android.databinding.DataBindingUtil
import android.databinding.ViewDataBinding
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import de.moetz.android.timingiseverything.ApplicationContext

class ListAdapter(
        private val rowLayoutId: Int,
        private val bindingVariableId: Int,
        private val elements: List<ListViewItem>) : BaseAdapter() {

    private val inflator: LayoutInflater = LayoutInflater.from(ApplicationContext.context)

    override fun getCount(): Int {
        return this.elements.size
    }

    override fun getItemId(position: Int): Long {
        return this.elements[position].getId()
    }

    override fun getItem(position: Int): ListViewItem {
        return this.elements[position]
    }

    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View? {
        val binding: ViewDataBinding = DataBindingUtil.inflate(this.inflator, this.rowLayoutId, parent, false)
        binding.setVariable(this.bindingVariableId, this.elements[position])
        return binding.root
    }

}