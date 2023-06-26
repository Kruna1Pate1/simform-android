package com.krunal.demo.githubclient.data.local

import androidx.databinding.BaseObservable
import androidx.databinding.Bindable
import com.krunal.demo.BR

data class IssueModel(
    private var _title: String,
    private var _body: String? = null,
) : BaseObservable() {

    @get:Bindable
    var title: String = _title
        set(value) {
            _title = value
            field = value
            notifyPropertyChanged(BR.title)
        }

    @get:Bindable
    var body: String? = _body
        set(value) {
            _body = value
            field = value
            notifyPropertyChanged(BR.body)
        }
}