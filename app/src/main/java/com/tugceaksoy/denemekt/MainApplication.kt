package com.tugceaksoy.denemekt

import android.app.Application
import android.util.Log

import java.io.FileOutputStream

import java.io.File

import java.io.InputStream

import java.io.OutputStream

import android.content.res.AssetManager




class MainApplication : Application() {

    companion object{
       var instance: MainApplication? = null
    }


    override fun onCreate() {
        super.onCreate()
        // start copy file here, copy eng.trainneddata from assets to external storage ../tessdata/vie.trainneddata
        // the data path, must contain sub folder call "tessdata", if not the lib will not work.
        instance = this
        copyTessDataForTextRecognizor()
    }

    private fun tessDataPath(): String? {
        return this.getExternalFilesDir(null).toString() + "/tessdata/"
    }

    fun getTessDataParentDirectory(): String? {
        return this.getExternalFilesDir(null)!!.getAbsolutePath()
    }
    private fun copyTessDataForTextRecognizor() {
        val run = Runnable {
            val assetManager: AssetManager = this!!.getAssets()
            var out: OutputStream? = null
            try {
                val `in` = assetManager.open("eng.traineddata")
                val tesspath = this!!.tessDataPath()
                val tessFolder = File(tesspath)
                if (!tessFolder.exists()) tessFolder.mkdir()
                val tessData = "$tesspath/eng.traineddata"
                val tessFile = File(tessData)
                if (!tessFile.exists()) {
                    out = FileOutputStream(tessData)
                    val buffer = ByteArray(1024)
                    var read = `in`.read(buffer)
                    while (read != -1) {
                        out.write(buffer, 0, read)
                        read = `in`.read(buffer)
                    }
                    Log.d("MainApplication", " Did finish copy tess file  ")
                } else Log.d("MainApplication", " tess file exist  ")
            } catch (e: Exception) {
                Log.d("MainApplication", "couldn't copy with the following error : $e")
            } finally {
                try {
                    out?.close()
                } catch (exx: Exception) {
                }
            }
        }
        Thread(run).start()
    }

}