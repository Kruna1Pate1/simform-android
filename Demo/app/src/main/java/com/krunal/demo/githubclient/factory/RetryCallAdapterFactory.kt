package com.krunal.demo.githubclient.factory

import android.util.Log
import com.krunal.demo.githubclient.annotation.Retry
import okhttp3.Request
import okio.Timeout
import retrofit2.Call
import retrofit2.CallAdapter
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import java.lang.reflect.Type
import java.util.concurrent.TimeoutException
import java.util.concurrent.atomic.AtomicInteger

class RetryCallAdapterFactory private constructor() : CallAdapter.Factory() {

    override fun get(
        returnType: Type, annotations: Array<out Annotation>, retrofit: Retrofit
    ): CallAdapter<*, *> {
        var itShouldRetry = 0
        val retry = getRetry(annotations)
        if (retry != null) {
            itShouldRetry = retry.max
        }
        Log.d(TAG, "Starting CallAdapter with $itShouldRetry retries.")

        return RetryCallAdapter(
            retrofit.nextCallAdapter(this, returnType, annotations), itShouldRetry
        )
    }

    private fun getRetry(annotations: Array<out Annotation>): Retry? {
        return annotations.filterIsInstance(Retry::class.java).firstOrNull()
    }

    class RetryCallAdapter<R, T>(
        private val delegated: CallAdapter<R, T>, private val maxRetries: Int
    ) : CallAdapter<R, T> {

        override fun responseType(): Type = delegated.responseType()

        override fun adapt(call: Call<R>): T =
            delegated.adapt(if (maxRetries > 0) RetryCall(call, maxRetries) else call)
    }

    class RetryCall<T>(
        private val delegated: Call<T>, private val maxRetries: Int
    ) : Call<T> {

        override fun clone(): Call<T> = RetryCall(delegated.clone(), maxRetries)

        override fun execute(): Response<T> = delegated.execute()

        override fun isExecuted(): Boolean = delegated.isExecuted

        override fun cancel() = delegated.cancel()

        override fun isCanceled(): Boolean = delegated.isCanceled

        override fun request(): Request = delegated.request()

        override fun timeout(): Timeout = delegated.timeout()

        override fun enqueue(callback: Callback<T>) {
            delegated.enqueue(RetryCallback(delegated, callback, maxRetries))
        }
    }

    class RetryCallback<T>(
        private val call: Call<T>, private val callback: Callback<T>, private val maxRetries: Int
    ) : Callback<T> {

        private val retryCount: AtomicInteger = AtomicInteger(0)

        override fun onResponse(call: Call<T>, response: Response<T>) {
            if (!response.isSuccessful && retryCount.incrementAndGet() <= maxRetries) {
                retryCall()
            } else {
                callback.onResponse(call, response)
            }
        }

        override fun onFailure(call: Call<T>, t: Throwable) {
            if (retryCount.incrementAndGet() <= maxRetries) {
                retryCall()
            } else if (maxRetries > 0) {
                callback.onFailure(
                    call, TimeoutException("No retries left after $maxRetries attempts.")
                )
            } else {
                callback.onFailure(call, t)
            }
        }

        private fun retryCall() {
            call.clone().enqueue(this)
        }
    }

    companion object {

        private const val TAG = "RetryCallAdapterFactory"

        fun create(): RetryCallAdapterFactory = RetryCallAdapterFactory()
    }
}

