package com.tugceaksoy.denemekt
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModel
import java.io.*

import android.app.ProgressDialog
import android.graphics.*
import com.googlecode.tesseract.android.TessBaseAPI
import android.os.Environment

class ViewModelClass() : ViewModel() {
    var activity :AppCompatActivity? = null

    var tempFile = File.createTempFile("geeks", null)





    fun transformToText(bitmap: Bitmap){
    val ocrManager=OcrManager()
        ocrManager.initAPI()
        ocrManager.startRecognize(bitmap)

}




    fun setActivityContent(activity: MainActivity) {
        this.activity = activity

    }}


