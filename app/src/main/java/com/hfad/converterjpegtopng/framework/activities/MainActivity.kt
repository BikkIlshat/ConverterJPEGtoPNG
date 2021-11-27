package com.hfad.converterjpegtopng.framework.activities

import com.github.terrakok.cicerone.androidx.AppNavigator
import com.hfad.converterjpegtopng.R
import com.hfad.converterjpegtopng.framework.AndroidScreens
import com.hfad.converterjpegtopng.framework.App
import com.hfad.converterjpegtopng.mvp.interfaces.BackButtonListener
import com.hfad.converterjpegtopng.mvp.interfaces.views.MainView
import com.hfad.converterjpegtopng.mvp.presenters.MainPresenter
import moxy.MvpAppCompatActivity
import moxy.ktx.moxyPresenter


class MainActivity : MvpAppCompatActivity(R.layout.activity_main), MainView {

    private val navigator = AppNavigator(this, R.id.container)
    private val presenter by moxyPresenter {
        MainPresenter(App.instance.router, AndroidScreens())
    }


    override fun onResumeFragments() {
        super.onResumeFragments()
        App.instance.navigatorHolder.setNavigator(navigator)
    }

    override fun onPause() {
        super.onPause()
        App.instance.navigatorHolder.removeNavigator()
    }

    override fun onBackPressed() {
        supportFragmentManager.fragments.forEach {
            if (it is BackButtonListener && it.backPressed()) {
                return
            }
        }
        presenter.backClicked()
    }
}