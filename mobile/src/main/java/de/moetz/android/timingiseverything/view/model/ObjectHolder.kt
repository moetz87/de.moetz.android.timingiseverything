package de.moetz.android.timingiseverything.view.model

import android.databinding.BaseObservable
import android.databinding.Bindable

open class ObjectHolder<T>(initValue: T) : BaseObservable() {
    @Bindable
    var value: T = initValue
}

class StringHolder(initValue: String) : ObjectHolder<String>(initValue)
