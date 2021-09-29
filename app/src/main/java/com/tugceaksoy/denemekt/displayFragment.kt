package com.tugceaksoy.denemekt

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView


class displayFragment  : Fragment()  {
    private var imageView : ImageView? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val args = arguments
        val index = args!!.getString("index", "aa")
        imageView.setImageURI(index)

        // Inflate the layout for this fragment
        val view=inflater.inflate(R.layout.fragment_display, container, false)

        return view;
    }

}

private fun ImageView?.setImageURI(index: String?) {

}
