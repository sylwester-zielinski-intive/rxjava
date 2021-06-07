package com.intive.training

import io.reactivex.rxjava3.core.BackpressureStrategy
import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.schedulers.Schedulers

fun main() {
    HotObservableTest.testBackPressureError()

    Thread.sleep(100000)
}

object HotObservableTest {

    fun testBackPressureError() {
        val subject = Observable.create<Int> { emitter ->
            for (i in 0..256) {
                println("Thread: ${Thread.currentThread().id} Produce: $i")
                emitter.onNext(i)
            }
        }
            .toFlowable(BackpressureStrategy.MISSING)
            .subscribeOn(Schedulers.io())
            .publish()

        subject.connect()

        subject
            .observeOn(Schedulers.io())
            .subscribe({
                Thread.sleep(1000)
                println("Thread: ${Thread.currentThread().id} Consume: $it")
            }, {
                println("Error: $it")
            })
    }
}
