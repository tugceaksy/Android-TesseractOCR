package com.tugceaksoy.denemekt

import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModel
import android.app.ProgressDialog

import android.graphics.Bitmap
import android.os.Process


class DisplayViewModel:ViewModel() {
    var activity : AppCompatActivity? = null
    fun setActivityContent(activity: MainActivity) {
        this.activity = activity

    }


}