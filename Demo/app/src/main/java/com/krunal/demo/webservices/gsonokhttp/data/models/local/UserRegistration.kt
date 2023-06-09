package com.krunal.demo.webservices.gsonokhttp.data.models.local

import androidx.databinding.BaseObservable
import androidx.databinding.Bindable
import com.krunal.demo.BR
import java.util.Date

data class UserRegistration(
    private var _name: String = "", private var _email: String = "", private var _dob: Date = Date(), private var _movies: String = ""
) : BaseObservable() {

    @get:Bindable
    var name: String = _name
        set(value) {
            _name = value
            field = value
            notifyPropertyChanged(BR.name)
        }

    @get:Bindable
    var email: String = _email
        set(value) {
            _email = value
            field = value
            notifyPropertyChanged(BR.email)
        }

    @get:Bindable
    var dob: Date = _dob
        set(value) {
            _dob = value
            field = value
            notifyPropertyChanged(BR.dob)
        }

    @get:Bindable
    var movies: String = _movies
        set(value) {
            _movies = value
            field = value
            notifyPropertyChanged(BR.movies)
        }
}