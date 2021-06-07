package com.intive.training

import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.core.Single
import io.reactivex.rxjava3.schedulers.Schedulers

fun main() {
    //Należy pobrać listę id elementów na home screen
    //Dla każdego elementu pobrać dane layoutu i zwrócić jako listę
    //Wszystko zrobić jako jeden łańcuhc RxJavowy.
}

object HomeworkAPI {

    fun getHomeLayoutIds() = Single.just(listOf(1, 2, 3, 4))

    fun getLayoutData(id: Int) = Single.just(LayoutData(id))
}

data class LayoutData(val value: Int)
