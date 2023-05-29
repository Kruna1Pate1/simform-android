package com.krunal.demo.navigation.data.models

import androidx.databinding.BaseObservable
import androidx.databinding.Bindable
import com.krunal.demo.BR

data class RegisterDetail(
    private var _username: String = "",
    private var _email: String = "",
    private var _password: String = ""
): BaseObservable() {

    @get:Bindable
    var username: String = _username
        set(value) {
            _username = value
            field = value
            notifyPropertyChanged(BR.username)
        }

    @get:Bindable
    var email: String = _email
        set(value) {
            _email = value
            field = value
            notifyPropertyChanged(BR.email)
        }

    @get:Bindable
    var password: String = _password
        set(value) {
            _password = value
            field = value
            notifyPropertyChanged(BR.password)
        }
}
