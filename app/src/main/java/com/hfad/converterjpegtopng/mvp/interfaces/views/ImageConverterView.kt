package com.hfad.converterjpegtopng.mvp.interfaces.views

import android.net.Uri
import moxy.MvpView
import moxy.viewstate.strategy.AddToEndSingleStrategy
import moxy.viewstate.strategy.StateStrategyType

@StateStrategyType(AddToEndSingleStrategy::class)
interface ImageConverterView : ProgressView, ErrorView, MvpView {
    fun init()
    fun showOriginImage(uri: Uri?)
    fun showConvertedImage(uri: Uri?)
    fun btnStartConvertEnable()
    fun btnStartConvertDisabled()
    fun btnAbortConvertEnabled()
    fun btnAbortConvertDisabled()
}