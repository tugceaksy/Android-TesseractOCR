package com.tugceaksoy.denemekt

import android.content.Context
import android.graphics.Bitmap
import com.googlecode.tesseract.android.TessBaseAPI


class TessOCr(context: Context, language: String?) {
    private val mTess: TessBaseAPI?
    fun getOCRResult(bitmap: Bitmap?): String {
        mTess!!.setImage(bitmap)
        return mTess.utF8Text
    }

    fun onDestroy() {
        mTess?.end()
    }

    init {
        mTess = TessBaseAPI()
        val datapath = context.filesDir.toString() + "/tesseract/"
        mTess.init(datapath, language)
    }
}