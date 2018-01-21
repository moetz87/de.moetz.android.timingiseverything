package de.moetz.android.timingiseverything.view.model;

import android.databinding.BaseObservable

class Observable<T>() : BaseObservable() {

    private var element: T? = null
        set(value) {
            if (field != value) {
                field = value
                notifyChange()
            }
        }

    constructor(element: T) : this() {
        this.element = element
    }

    fun isSet(): Boolean {
        return this.element != null
    }

    fun get(): T? {
        return this.element
    }

    fun set(element: T) {
        this.element = element
    }

    fun unset() {
        this.element = null
    }

}
