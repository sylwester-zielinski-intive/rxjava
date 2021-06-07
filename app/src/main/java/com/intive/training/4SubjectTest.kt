package com.intive.training

import io.reactivex.rxjava3.subjects.AsyncSubject
import io.reactivex.rxjava3.subjects.BehaviorSubject
import io.reactivex.rxjava3.subjects.PublishSubject
import io.reactivex.rxjava3.subjects.ReplaySubject

fun main() {
    SubjectsTest.runPublishSubject()
}

object SubjectsTest {

    fun runPublishSubject() {
        val source = PublishSubject.create<Int>()

        // It will get 1, 2, 3, 4 and onComplete
        source.subscribe {
            println("First observer: $it")
        }

        source.onNext(1)
        source.onNext(2)
        source.onNext(3)

        // It will get 4 and onComplete for second observer also.
        source.subscribe {
            println("Second observer: $it")
        }

        source.onNext(4)
        source.onComplete()
    }

    fun runReplaySubject() {
        val source = ReplaySubject.create<Int>()

        // It will get 1, 2, 3, 4
        source.subscribe {
            println("First observer: $it")
        }

        source.onNext(1)
        source.onNext(2)
        source.onNext(3)
        source.onNext(4)
        source.onComplete()

        // It will also get 1, 2, 3, 4 as we have used replay Subject
        source.subscribe {
            println("Second observer: $it")
        }
    }

    //Works like LiveData
    fun runBehaviorSubject() {
        val source = BehaviorSubject.create<Int>()

        // It will get 1, 2, 3, 4 and onComplete
        source.subscribe {
            println("First observer: $it")
        }

        source.onNext(1)
        source.onNext(2)
        source.onNext(3)

        // It will get 3(last emitted)and 4(subsequent item) and onComplete
        source.subscribe {
            println("Second observer: $it")
        }

        source.onNext(4)
        source.onComplete()
    }

    fun runAsyncSubject() {
        val source = AsyncSubject.create<Int>()

        // It will get only 4 and onComplete
        source.subscribe {
            println("First observer: $it")
        }

        source.onNext(1)
        source.onNext(2)
        source.onNext(3)

        // It will also get only get 4 and onComplete
        source.subscribe {
            println("Second observer: $it")
        }

        source.onNext(4)
        source.onComplete()
    }
}
