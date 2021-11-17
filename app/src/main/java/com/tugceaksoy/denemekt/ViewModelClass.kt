package com.tugceaksoy.denemekt
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModel
import java.io.*

import android.app.ProgressDialog
import android.graphics.*
import android.os.Bundle
import com.googlecode.tesseract.android.TessBaseAPI
import android.os.Environment
import androidx.fragment.app.Fragment

class ViewModelClass() : ViewModel() {
    var activity :AppCompatActivity? = null

    var tempFile = File.createTempFile("geeks", null)





    fun transformToText(bitmap: Bitmap): String?{
    val ocrManager=OcrManager()
        val sonuc =ocrManager.startRecognize(bitmap)
        return sonuc
}




    fun setActivityContent(activity: MainActivity) {
        this.activity = activity

    }}


