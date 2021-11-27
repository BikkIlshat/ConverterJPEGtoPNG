package com.hfad.converterjpegtopng.framework.fragments

import androidx.fragment.app.Fragment
import by.kirich1409.viewbindingdelegate.viewBinding
import com.hfad.converterjpegtopng.R
import com.hfad.converterjpegtopng.databinding.FragmentUsersBinding
import com.hfad.converterjpegtopng.framework.App
import com.hfad.converterjpegtopng.mvp.interfaces.BackButtonListener
import com.hfad.converterjpegtopng.mvp.interfaces.views.UsersView
import com.hfad.converterjpegtopng.mvp.presenters.UsersPresenter
import moxy.MvpAppCompatFragment
import moxy.ktx.moxyPresenter

class UsersFragment : MvpAppCompatFragment(R.layout.fragment_users), UsersView, BackButtonListener {

    private val viewBinding: FragmentUsersBinding by viewBinding()

    private val presenter: UsersPresenter by moxyPresenter {
        UsersPresenter(App.instance.router)
    }

    override fun init() = with(viewBinding) {
        btnGoToImgConverter.setOnClickListener { presenter.goToImageConverter() }
    }

    override fun backPressed(): Boolean = presenter.backPressed()


    override fun showProgressBar() {}
    override fun hideProgressBar() {}

    companion object {
        fun newInstance(): Fragment = UsersFragment()
    }
}