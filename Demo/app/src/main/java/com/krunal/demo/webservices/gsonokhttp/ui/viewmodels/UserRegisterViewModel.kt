package com.krunal.demo.webservices.gsonokhttp.ui.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.krunal.demo.webservices.gsonokhttp.data.models.api.Movie
import com.krunal.demo.webservices.gsonokhttp.data.models.api.UserDetail
import com.krunal.demo.webservices.gsonokhttp.data.models.local.UserRegistration
import com.krunal.demo.webservices.gsonokhttp.repositories.UserRepository
import com.krunal.demo.webservices.utils.Resource
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

class UserRegisterViewModel : ViewModel() {

    val userDetails: MutableStateFlow<UserRegistration> = MutableStateFlow(UserRegistration())

    private val _isUpdateUser: MutableStateFlow<Boolean> = MutableStateFlow(false)
    val isUpdateUser: MutableStateFlow<Boolean> = MutableStateFlow(false)

    fun submitDetails() {
        if (isUpdateUser.value) {
            updateUser()
        } else {
            registerUser()
        }
    }

    private fun registerUser() {
        viewModelScope.launch(Dispatchers.IO) {
            userDetails.value.let { details ->
                val movies = details.movies?.split(",")?.map {
                    Movie(it.trim())
                }

                val userDetail = UserDetail(
                    userId = details.userId,
                    name = details.name,
                    email = details.email,
                    dob = details.dob,
                    favoriteMovies = movies ?: emptyList()
                )
                UserRepository.updateUser(userDetail)
            }
        }
    }

    private fun updateUser() {
        viewModelScope.launch(Dispatchers.IO) {
            userDetails.value.let { details ->
                val movies = details.movies?.split(",")?.map {
                    Movie(it.trim())
                } ?: emptyList()

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

    fun setUpdateUser(userId: Int) {
        viewModelScope.launch {
            _isUpdateUser.emit(true)
            UserRepository.getUser(userId).collectLatest { response ->
                when (response) {
                    is Resource.Loading -> {}

                    is Resource.Success -> {
                        response.data?.let { user ->
                            val userRegistration = UserRegistration(
                                _name = user.name,
                                _email = user.email,
                                _dob = user.dob,
                                _movies = user.favoriteMovies.joinToString(", ") { it.name }
                            )
                            userDetails.emit(userRegistration)
                        }
                    }

                    is Resource.Error -> {}
                }
            }
        }
    }
}