package com.tugceaksoy.denemekt

import android.content.ContentResolver
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.content.pm.PackageManager
import android.net.Uri
import android.provider.MediaStore
import android.util.Log
import android.widget.ImageView
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import java.util.concurrent.Executors
import androidx.camera.core.*
import androidx.camera.lifecycle.ProcessCameraProvider
import androidx.core.view.isVisible
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.ViewModelProvider
import kotlinx.android.synthetic.main.activity_main.*
import java.io.File
import java.util.concurrent.ExecutorService
typealias LumaListener = (luma: Double) -> Unit

open class MainActivity : AppCompatActivity(),LifecycleOwner{
    private var imageCapture: ImageCapture? = null

    //created my viewmodel object to have a referance and use its methods and propertys
    var viewModel: ViewModelClass? = null

    private lateinit var outputDirectory: File
    private lateinit var cameraExecutor: ExecutorService

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        viewModel=ViewModelProvider(this).get(ViewModelClass::class.java)
        viewModel!!.setActivityContent(this)


        // Request camera permissions
        if (allPermissionsGranted()) {
            startCamera()
        } else {
            ActivityCompat.requestPermissions(
                this, REQUIRED_PERMISSIONS, REQUEST_CODE_PERMISSIONS)
        }

        // Set up the listener for take photo button
        camera_capture_button.setOnClickListener { takePhoto()
            camera_capture_button.isVisible=false
        }
        outputDirectory = getOutputDirectory()
        cameraExecutor = Executors.newSingleThreadExecutor()
    }

    private fun takePhoto() {
        // Get a stable reference of the modifiable image capture use case
        val imageCapture = imageCapture ?: return


        // Create output options object which contains file + metadata
        val outputOptions = ImageCapture.OutputFileOptions.Builder(viewModel!!.tempFile).build()


        imageCapture.takePicture(
            outputOptions, ContextCompat.getMainExecutor(this), object : ImageCapture.OnImageSavedCallback {
                override fun onError(exc: ImageCaptureException) {
                    Log.e(TAG, "Photo capture failed: ${exc.message}", exc)
                }


                override fun onImageSaved(output: ImageCapture.OutputFileResults) {
                    val savedUri = Uri.fromFile(viewModel!!.tempFile)


                    val pathString=savedUri.toString()
                    val bundle = Bundle()
                    bundle.putString("photo",pathString)
                    val displayFragment=displayFragment()
                    displayFragment.arguments=bundle
                    val msg = "KAYDEDİLDİ: $viewModel.tempFile"
                    Log.d(TAG, msg)

                    supportFragmentManager.beginTransaction().apply {
                     replace(R.id.flfragment,displayFragment)
                        commit()
                    }
                }
            })
    }

    private fun startCamera() {
        val cameraProviderFuture = ProcessCameraProvider.getInstance(this)

        cameraProviderFuture.addListener(Runnable {
            // Used to bind the lifecycle of cameras to the lifecycle owner
            val cameraProvider: ProcessCameraProvider = cameraProviderFuture.get()

            // Preview
            val preview = Preview.Builder()
                .build()
                .also {
                    it.setSurfaceProvider(viewFinder.surfaceProvider)
                }

            imageCapture = ImageCapture.Builder()
                .build()

            // Select back camera as a default
            val cameraSelector = CameraSelector.DEFAULT_BACK_CAMERA

            try {
                // Unbind use cases before rebinding
                cameraProvider.unbindAll()

                // Bind use cases to camera
                cameraProvider.bindToLifecycle(
                    this, cameraSelector, preview, imageCapture)

            } catch(exc: Exception) {
                Log.e(TAG, "Use case binding failed", exc)
            }

        }, ContextCompat.getMainExecutor(this))
    }

    private fun allPermissionsGranted() = REQUIRED_PERMISSIONS.all {
        ContextCompat.checkSelfPermission(
            baseContext, it) == PackageManager.PERMISSION_GRANTED
    }

    private fun getOutputDirectory(): File {
        val mediaDir = externalMediaDirs.firstOrNull()?.let {
            File(it, resources.getString(R.string.app_name)).apply { mkdirs() } }
        return if (mediaDir != null && mediaDir.exists())
            mediaDir else filesDir
    }

    override fun onDestroy() {
        super.onDestroy()
        cameraExecutor.shutdown()
    }

    companion object {
        private const val TAG = "denemekt"
        private const val FILENAME_FORMAT = "yyyy-MM-dd-HH-mm-ss-SSS"
        private const val REQUEST_CODE_PERMISSIONS = 10
        private val REQUIRED_PERMISSIONS = arrayOf("android.permission.CAMERA","android.permission.WRITE_EXTERNAL_STORAGE")
    }
}