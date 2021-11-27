package com.hfad.converterjpegtopng.mvp.interfaces

import io.reactivex.rxjava3.core.Scheduler

interface IMySchedulers {

    fun main(): Scheduler
    fun computation(): Scheduler
    fun start()
    fun shutdown()

}