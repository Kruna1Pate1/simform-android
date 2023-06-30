package com.krunal.demo.webservices.gsonokhttp.ui.viewmodels

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.krunal.demo.utils.AppConstants
import com.krunal.demo.utils.AppConstants.TAG
import com.krunal.demo.webservices.gsonokhttp.data.models.api.Movie
import com.krunal.demo.webservices.gsonokhttp.data.models.api.UserDetail
import com.krunal.demo.webservices.gsonokhttp.data.models.local.UserRegistration
import com.krunal.demo.webservices.gsonokhttp.repositories.UserRepository
import com.krunal.demo.webservices.utils.Resource
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import okhttp3.Call
import okhttp3.Callback
import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.Response
import java.io.IOException

class UserRegisterViewModel : ViewModel() {

    val userDetails: MutableStateFlow<UserRegistration> = MutableStateFlow(UserRegistration())

    private val _isUpdateUser: MutableStateFlow<Boolean> = MutableStateFlow(false)
    val isUpdateUser: StateFlow<Boolean> = _isUpdateUser

    private val USER_URL = "${AppConstants.MOCKAPI_BASE_URL}/users"

    fun submitDetails() {
        if (isUpdateUser.value) {
            updateUser()
        } else {
            registerUser()
        }
    }

    private fun registerUser() {
        viewModelScope.launch {
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
                UserRepository.registerUser(userDetail)
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
                    userId = details.userId,
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
                            val userRegistration = UserRegistration(userId = user.userId,
                                _name = user.name,
                                _email = user.email,
                                _dob = user.dob,
                                _movies = user.favoriteMovies.joinToString(", ") { it.name })
                            userDetails.emit(userRegistration)
                        }
                    }

                    is Resource.Error -> {}
                }
            }
        }
    }

    fun deleteUser(onDelete: (successful: Boolean) -> Unit) {
        val url = "$USER_URL/${userDetails.value.userId}"
        val client = OkHttpClient.Builder().build()
        val request = Request.Builder().url(url).delete().build()

        client.newCall(request).enqueue(object : Callback {
            override fun onFailure(call: Call, e: IOException) {
                CoroutineScope(Job() + Dispatchers.Main).launch {
                    onDelete(false)
                }
                Log.e(TAG, "onResponse: ${e.localizedMessage}")
            }

            override fun onResponse(call: Call, response: Response) {
                CoroutineScope(Job() + Dispatchers.Main).launch {
                    onDelete(response.isSuccessful)
                }
                Log.i(TAG, "onResponse: ${response.message}")
            }
        })
    }
}