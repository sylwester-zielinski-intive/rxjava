package com.intive.training

import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.core.Single
import io.reactivex.rxjava3.schedulers.Schedulers

fun main() {
    FlatMapTest.testConcatMap()
}

object FlatMapTest {

    fun testFlatMap() {
        Observable.fromIterable(listOf(1, 2, 3, 4, 5, 6))
            .flatMap {  Observable.just(it).subscribeOn(Schedulers.io()) }
            .subscribe {
                println("value $it")
            }
    }

    fun testConcatMap() {
        Observable.fromIterable(listOf(1, 2, 3, 4, 5, 6))
            .concatMapEager { Observable.just(it).subscribeOn(Schedulers.io()) }
            .subscribe {
                println("value $it")
            }
    }

    fun testSwitchMap() {
        Observable.fromIterable(listOf(1, 2, 3, 4, 5, 6))
            .switchMap { Observable.just(it).subscribeOn(Schedulers.io()) }
            .subscribe {
                println("value $it")
            }
    }

    fun transform() {
        Observable.fromIterable(listOf(1, 2, 3, 4, 5, 6))
            .flatMapSingle { Single.just(it+2) }
            .subscribe {

            }
    }
}
