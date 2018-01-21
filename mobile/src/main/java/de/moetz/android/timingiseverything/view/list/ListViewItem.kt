package de.moetz.android.timingiseverything.view.list

import de.moetz.android.timingiseverything.view.model.ViewModel

abstract class ListViewItem : ViewModel() {

    abstract fun getId(): Long

}