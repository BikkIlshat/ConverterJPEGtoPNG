package com.hfad.converterjpegtopng.mvp.presenters

import android.net.Uri
import android.os.Build
import androidx.annotation.RequiresApi
import com.github.terrakok.cicerone.Router
import com.hfad.converterjpegtopng.mvp.interfaces.IMySchedulers
import com.hfad.converterjpegtopng.mvp.interfaces.views.ImageConverterView
import com.hfad.converterjpegtopng.mvp.model.ConverterJpgToPng
import io.reactivex.rxjava3.core.SingleObserver
import io.reactivex.rxjava3.disposables.CompositeDisposable
import io.reactivex.rxjava3.disposables.Disposable
import moxy.MvpPresenter

class ImageConverterPresenter(
    private val converter: ConverterJpgToPng,
    private val schedulers: IMySchedulers,
    private val router: Router
) : MvpPresenter<ImageConverterView>() {

    var disposables = CompositeDisposable()

    fun backPressed(): Boolean {
        router.exit()
        return true
    }

    override fun onFirstViewAttach() {
        super.onFirstViewAttach()
        viewState.init()
    }

    override fun onDestroy() {
        disposables.clear()
    }

    fun startConvertingPressed(imageUri: Uri) {
        viewState.showProgressBar()
        viewState.btnAbortConvertEnabled()
        converter
            .convertRx(imageUri)
            .subscribeOn(schedulers.computation())
            .observeOn(schedulers.main())
            .subscribe(object : SingleObserver<Uri> {
                override fun onSubscribe(d: Disposable?) {
                    disposables.add(d)
                }

                override fun onSuccess(t: Uri?) {
                    if (t != null) {
                        onConvertingSuccess(t)
                    }
                }

                override fun onError(error: Throwable) {
                    onConvertingError(error)
                }
            })
    }

    private fun onConvertingSuccess(uri: Uri) {
        viewState.showConvertedImage(uri)
        viewState.hideProgressBar()
        viewState.btnAbortConvertDisabled()

    }

    private fun onConvertingError(error: Throwable) {
        viewState.showError()
        viewState.hideProgressBar()
        viewState.btnAbortConvertDisabled()

    }

    fun abortConvertImagePressed() {
        schedulers.shutdown()
        viewState.hideProgressBar()
        viewState.btnAbortConvertDisabled()
        schedulers.start()
    }

    fun originalImageSelected(uri: Uri?) {
        viewState.showOriginImage(uri)
        viewState.btnStartConvertEnable()
    }
}