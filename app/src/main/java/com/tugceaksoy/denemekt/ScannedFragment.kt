package com.tugceaksoy.denemekt

import android.content.ClipboardManager
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup


import com.tugceaksoy.denemekt.databinding.FragmentScannedBinding
import kotlinx.android.synthetic.*
import android.content.Context.CLIPBOARD_SERVICE

import android.content.ClipData

import android.content.Intent
import android.os.Build
import androidx.lifecycle.ViewModelProvider


class ScannedFragment : Fragment() {
    private lateinit var Obinding :FragmentScannedBinding
    var viewModel: ViewModelClass? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Obinding= FragmentScannedBinding.inflate(layoutInflater)

        viewModel= ViewModelProvider(this).get(ViewModelClass::class.java)
        viewModel!!.setActivityContent(activity as MainActivity)


        val displayFragment=displayFragment()


            Obinding.button3.setOnClickListener {
              //Copying stuffs
               if (Build.VERSION.SDK_INT < Build.VERSION_CODES.HONEYCOMB) {
               val clipboard =
                requireContext().getSystemService(CLIPBOARD_SERVICE) as android.text.ClipboardManager
               clipboard.setText(Obinding.textView2.text)
                } else{
                val clipboard = requireContext().getSystemService(CLIPBOARD_SERVICE) as ClipboardManager
                val clip = ClipData.newPlainText("Copied Text", Obinding.textView2.text)
                   clipboard.setPrimaryClip(clip)
               }
     }
            Obinding.button4.setOnClickListener {
                val intent = Intent (activity, MainActivity::class.java)
                startActivity(intent)
}


   fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {


        return inflater.inflate(R.layout.fragment_scanned, container, false)

    }


}}