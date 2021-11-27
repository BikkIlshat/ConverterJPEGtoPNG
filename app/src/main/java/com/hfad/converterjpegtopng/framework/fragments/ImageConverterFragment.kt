package com.hfad.converterjpegtopng.framework.fragments

import android.app.Activity.RESULT_OK
import android.content.Intent
import android.net.Uri
import android.view.View
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import by.kirich1409.viewbindingdelegate.viewBinding
import com.hfad.converterjpegtopng.R
import com.hfad.converterjpegtopng.databinding.FragmentImageConverterBinding
import com.hfad.converterjpegtopng.framework.App
import com.hfad.converterjpegtopng.mvp.interfaces.BackButtonListener
import com.hfad.converterjpegtopng.mvp.interfaces.views.ImageConverterView
import com.hfad.converterjpegtopng.mvp.model.ConverterJpgToPng
import com.hfad.converterjpegtopng.mvp.presenters.ImageConverterPresenter
import com.hfad.converterjpegtopng.shedulers.MySchedulersFactory
import moxy.MvpAppCompatFragment
import moxy.ktx.moxyPresenter


class ImageConverterFragment : MvpAppCompatFragment(R.layout.fragment_image_converter),
    ImageConverterView, BackButtonListener {

    private val viewBinding: FragmentImageConverterBinding by viewBinding()
    private var imageUri: Uri? = null
    private val presenter: ImageConverterPresenter by moxyPresenter {
        ImageConverterPresenter(
            ConverterJpgToPng(requireContext()),
            MySchedulersFactory.create(),
            App.instance.router
        )
    }


    private var resultLauncher =
        registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result ->
            if (result.resultCode == RESULT_OK) {
                imageUri = result.data?.data
                imageUri?.let { presenter.originalImageSelected(it) }
            }
        }


    // методы интерфейсов
    override fun backPressed(): Boolean = presenter.backPressed()

    override fun init() = with(viewBinding) {
        hideProgressBar()
        btnStartConvertDisabled()
        btnAbortConvertDisabled()
        btnImageSelection.setOnClickListener {
            val intent = Intent(Intent.ACTION_GET_CONTENT)
            intent.type = "image/*"
            resultLauncher.launch(intent)
        }
        btnStartConverting.setOnClickListener {
            imageUri?.let(presenter::startConvertingPressed)
        }
        btnAbort.setOnClickListener {
            presenter.abortConvertImagePressed()
        }

    }

    override fun showOriginImage(uri: Uri?) =
        with(viewBinding) { imgViewOriginalImg.setImageURI(uri) }

    override fun showConvertedImage(uri: Uri?) =
        with(viewBinding) { imgViewConvertedImg.setImageURI(uri) }

    override fun showProgressBar() =
        with(viewBinding) { progressBar2.visibility = View.VISIBLE }

    override fun hideProgressBar() =
        with(viewBinding) { progressBar2.visibility = View.GONE }

    override fun showError() {
        Toast.makeText(
            requireContext(),
            getString(R.string.conversion_error), Toast.LENGTH_SHORT
        ).show()
    }

    override fun btnStartConvertEnable() =
        with(viewBinding) { btnStartConverting.isEnabled = true }

    override fun btnStartConvertDisabled() =
        with(viewBinding) { btnStartConverting.isEnabled = false }

    override fun btnAbortConvertEnabled() =
        with(viewBinding) { btnAbort.isEnabled = true }

    override fun btnAbortConvertDisabled() =
        with(viewBinding) { btnAbort.isEnabled = false }


}