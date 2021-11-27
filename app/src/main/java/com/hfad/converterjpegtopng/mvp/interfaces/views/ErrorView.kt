package com.hfad.converterjpegtopng.mvp.interfaces.views

import moxy.viewstate.strategy.alias.SingleState

@SingleState
interface ErrorView {
    fun showError()
}