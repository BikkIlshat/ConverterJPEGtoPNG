package com.hfad.converterjpegtopng.mvp.interfaces.views

import moxy.viewstate.strategy.AddToEndSingleStrategy
import moxy.viewstate.strategy.StateStrategyType

@StateStrategyType(AddToEndSingleStrategy::class)
interface ProgressView {

    fun showProgressBar()
    fun hideProgressBar()

}