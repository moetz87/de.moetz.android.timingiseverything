package de.moetz.android.timingiseverything.view.list;

import android.databinding.DataBindingUtil
import android.databinding.ViewDataBinding
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter


class ListAdapter(
        private val rowLayoutId: Int,
        private val bindingVariableId: Int,
        val elements: List<ListViewItem>) : BaseAdapter() {

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
        val inflater = LayoutInflater.from(parent.context)
        val binding: ViewDataBinding = DataBindingUtil.inflate(inflater, this.rowLayoutId, parent, false)
        binding.setVariable(this.bindingVariableId, this.elements[position])
        return binding.root
    }

}