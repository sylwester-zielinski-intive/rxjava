package com.intive.training

import android.os.Handler
import android.os.HandlerThread
import android.os.Looper
import androidx.annotation.WorkerThread
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.core.Single
import io.reactivex.rxjava3.schedulers.Schedulers

fun main() {

}

object ObservableTest {

    fun handler() {
        val mainThread = Handler(Looper.getMainLooper())

        val ioThread = HandlerThread("io")
        val ioHandler = Handler(ioThread.looper)

        //We cannot download data on main thread, because we don't want to freeze UI.
        ioHandler.post {
            val result = API.downloadData()

            //Because of synchronisation only UI thread can modify views
            mainThread.post {
                displayResult(result)
            }
        }
    }

    private fun handlerCallbacks() {
        //In Java sealed classes aren't exist

        val mainThread = Handler(Looper.getMainLooper())

        val ioThread = HandlerThread("io")
        val ioHandler = Handler(ioThread.looper)

        class NetworkCallback {
            fun onSuccess(result: Int) {}
            fun onError(it: Throwable) {}
        }

        val callback = NetworkCallback()

        //We cannot download data on main thread, because we don't want to freeze UI.
        ioHandler.post {
            try {
                val result = API.downloadData()

                //Because of security only UI thread can modify views
                mainThread.post {
                    callback.onSuccess(result)
                }
            } catch (e: Exception) {
                //Because of security only UI thread can modify views
                mainThread.post {
                    callback.onError(e)
                }
            }
        }
    }

    private fun observableBaseFeature() {
        Single.fromCallable { API.downloadData() }
            .subscribeOn(AndroidSchedulers.mainThread())
            .observeOn(Schedulers.io())
            .subscribe(
                { /* onSuccess() */ },
                { /* onError() */ }
            )
    }

    private fun displayResult(result: Int) {}
}


object API {

    @WorkerThread
    fun downloadData(): Int = 2
}
