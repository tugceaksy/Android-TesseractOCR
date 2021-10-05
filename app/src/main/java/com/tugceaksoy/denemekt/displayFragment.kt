package com.tugceaksoy.denemekt

import android.net.Uri
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.core.net.toUri
import kotlinx.android.synthetic.*
import kotlinx.android.synthetic.main.fragment_display.view.*
import androidx.appcompat.app.AppCompatActivity
import android.R
import com.tugceaksoy.denemekt.databinding.FragmentDisplayBinding


class displayFragment  : Fragment()  {
    private var image_view : ImageView? = null
    var mBining : FragmentDisplayBinding? = null


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mBining = dataBinding as FragmentDisplayBinding


    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        return view;
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


        val bundle = this.arguments
        if (bundle != null) {
            val gelenuri = bundle!!.getString("photo","uri gelmedi")
            val uri =gelenuri.toUri()
            image_view?.setImageURI(uri)
        }

    }

}
