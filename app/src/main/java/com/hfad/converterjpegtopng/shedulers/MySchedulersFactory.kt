package com.hfad.converterjpegtopng.shedulers

import com.hfad.converterjpegtopng.mvp.interfaces.IMySchedulers


object MySchedulersFactory {
    fun create(): IMySchedulers = MySchedulers()
}