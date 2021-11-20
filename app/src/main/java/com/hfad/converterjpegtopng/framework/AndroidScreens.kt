package com.hfad.converterjpegtopng.framework

import com.github.terrakok.cicerone.Screen
import com.github.terrakok.cicerone.androidx.FragmentScreen
import com.hfad.converterjpegtopng.framework.fragments.ImageConverterFragment
import com.hfad.converterjpegtopng.framework.fragments.UsersFragment
import com.hfad.converterjpegtopng.mvp.interfaces.IScreens

class AndroidScreens : IScreens {

    override fun users(): Screen = FragmentScreen { UsersFragment.newInstance() }


    override fun imageConverter(): Screen =
        FragmentScreen { ImageConverterFragment() }
}