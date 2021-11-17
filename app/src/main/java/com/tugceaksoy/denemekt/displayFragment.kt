package com.tugceaksoy.denemekt

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import com.tugceaksoy.denemekt.databinding.FragmentDisplayBinding
import com.googlecode.tesseract.android.TessBaseAPI;
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.widget.Toast
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentTransaction
import java.time.Duration
import android.provider.MediaStore

import android.R.attr.bitmap
import android.content.ContentValues.TAG
import android.util.Log


class displayFragment  : Fragment()  {
    private var image_view : ImageView? = null
    private lateinit var mBining:FragmentDisplayBinding
    var viewModel: ViewModelClass? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        viewModel= ViewModelProvider(this).get(ViewModelClass::class.java)
        viewModel!!.setActivityContent(activity as MainActivity)



    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        mBining = DataBindingUtil.inflate(inflater,R.layout.fragment_display,container,false)
        return mBining.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val bundle = this.arguments
        if (bundle != null) {
         val uri = bundle ["photo"]
            mBining.imageView?.setImageURI(Uri.parse(uri!! as String))

            mBining.button.setOnClickListener{

             val bitmap = MediaStore.Images.Media.getBitmap(requireContext().contentResolver, Uri.parse(uri!! as String))
                val sonuc = viewModel!!.transformToText(bitmap)
                val bundle2 = Bundle()
                bundle2.putString("text", sonuc.toString())
                Log.d(TAG, "teseract result:  " + sonuc.toString())
                val scannedFragment=ScannedFragment()
          
                scannedFragment.arguments=bundle2
                requireActivity().supportFragmentManager.beginTransaction()
                    .replace(R.id.flfragment, scannedFragment)
                    .commit()
            }


        }

        mBining.button2.setOnClickListener {
            val intent = Intent (activity, MainActivity::class.java)
            startActivity(intent)
        }



    }


}
