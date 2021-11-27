package com.hfad.converterjpegtopng.mvp.presenters

import com.github.terrakok.cicerone.Router
import com.hfad.converterjpegtopng.framework.AndroidScreens
import com.hfad.converterjpegtopng.mvp.interfaces.views.UsersView
import moxy.MvpPresenter

class UsersPresenter(
    private val router: Router
) : MvpPresenter<UsersView>() {

    override fun onFirstViewAttach() {
        super.onFirstViewAttach()
        viewState.init()
        viewState.showProgressBar()
    }

    fun goToImageConverter() {
        router.navigateTo(AndroidScreens().imageConverter())
    }

    fun backPressed(): Boolean {
        router.exit()
        return true
    }
}





