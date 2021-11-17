package com.tugceaksoy.denemekt

import android.app.Application
import android.content.ContentValues.TAG
import android.graphics.Bitmap
import android.os.Bundle
import android.util.Log
import com.googlecode.tesseract.android.TessBaseAPI


class OcrManager {
    var baseAPI:TessBaseAPI?= null
    fun initAPI() {
        baseAPI = TessBaseAPI()

        val dataPath = MainApplication.instance!!.getTessDataParentDirectory()
        baseAPI!!.init(dataPath, "eng")

    }



    fun startRecognize(bitmap: Bitmap?): String? {
        if (baseAPI == null) initAPI()
        baseAPI!!.setImage(bitmap)
        val sonuc=baseAPI!!.utF8Text


        Log.d(TAG,"$sonuc")
        return baseAPI!!.utF8Text
    }
}