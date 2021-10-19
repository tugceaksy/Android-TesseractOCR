package com.tugceaksoy.denemekt

import android.app.Application
import android.graphics.Bitmap
import com.googlecode.tesseract.android.TessBaseAPI


class OcrManager {
    var baseAPI:TessBaseAPI?= null
    fun initAPI() {
        baseAPI = TessBaseAPI()
        val mainApplication=MainApplication()
        val dataPath = mainApplication.getTessDataParentDirectory()
        baseAPI!!.init(dataPath, "eng")

    }



    fun startRecognize(bitmap: Bitmap?): String? {
        if (baseAPI == null)
           initAPI()
        baseAPI!!.setImage(bitmap)
        val sonuc=baseAPI!!.utF8Text
        return baseAPI!!.utF8Text
    }
}