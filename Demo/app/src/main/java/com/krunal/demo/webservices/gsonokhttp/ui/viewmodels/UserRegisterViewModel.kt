package com.krunal.demo.webservices.gsonokhttp.ui.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.krunal.demo.webservices.gsonokhttp.data.models.api.Movie
import com.krunal.demo.webservices.gsonokhttp.data.models.api.UserDetail
import com.krunal.demo.webservices.gsonokhttp.data.models.local.UserRegistration
import com.krunal.demo.webservices.gsonokhttp.repositories.UserRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch

class UserRegisterViewModel : ViewModel() {

    val registerDetail: MutableStateFlow<UserRegistration> = MutableStateFlow(UserRegistration())

    fun submitDetails() {
        viewModelScope.launch(Dispatchers.IO) {
            registerDetail.value.let { details ->
                val movies = details.movies.split(",").map {
                    Movie(it.trim())
                }

                val userDetail = UserDetail(
                    name = details.name,
                    email = details.email,
                    dob = details.dob,
                    favoriteMovies = movies
                )
                UserRepository.registerUser(userDetail)
            }
        }
    }
}