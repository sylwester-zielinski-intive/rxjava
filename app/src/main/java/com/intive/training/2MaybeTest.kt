package com.intive.training

import io.reactivex.rxjava3.core.Maybe
import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.core.Single

fun main() {
    MaybeTest.testObservable()
}

object MaybeTest {

    fun testEmptyMaybe() {
        val a = Maybe.empty<Int>().subscribe(
            { println("on success") },
            { println("on error") },
            { println("on complete") }
        )

        a.dispose()
    }

    fun testMaybeWithValue() {
        Maybe.just<Int>(1).subscribe(
            { println("on success") },
            { println("on error") },
            { println("on complete") }
        )
    }

    fun testSingle() {
        Single.just(1).subscribe(
            { println("on success") },
            { println("on error") }
        )
    }

    fun testObservable() {
        Observable.just(1).subscribe(
            { println("on success") },
            { println("on error") },
            { println("on complete") }
        )
    }

    fun transformers() {
        val maybe = Maybe.empty<Int>()

        maybe.toObservable()
        maybe.toSingle()

        val single = Single.just(1)
        single.toObservable()
        single.toMaybe()

        val observable = Observable.just(1)
        observable.single(2) //single
        observable.firstElement() //maybe
        observable.ignoreElements() //completable
    }
}
