package com.hfad.converterjpegtopng.shedulers

import com.hfad.converterjpegtopng.mvp.interfaces.IMySchedulers
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.core.Scheduler
import io.reactivex.rxjava3.schedulers.Schedulers

class MySchedulers : IMySchedulers {

    override fun main(): Scheduler = AndroidSchedulers.mainThread()

    override fun computation(): Scheduler = Schedulers.computation()

    override fun start() = Schedulers.start()

    override fun shutdown() = Schedulers.shutdown()

}