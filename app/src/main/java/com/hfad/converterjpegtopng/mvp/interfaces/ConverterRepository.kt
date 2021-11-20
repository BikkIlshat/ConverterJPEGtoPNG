package com.hfad.converterjpegtopng.mvp.interfaces

import android.net.Uri
import io.reactivex.rxjava3.core.Single

interface ConverterRepository {
    fun convertRx(uri: Uri): Single<Uri>
}