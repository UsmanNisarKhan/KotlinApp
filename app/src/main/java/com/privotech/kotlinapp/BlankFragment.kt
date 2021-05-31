package com.privotech.kotlinapp

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.privotech.kotlinapp.databinding.FragmentBlankBinding
import dagger.hilt.android.AndroidEntryPoint


class BlankFragment : Fragment() {

    lateinit var binding : FragmentBlankBinding
    val bind get() = binding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment

        binding = FragmentBlankBinding.inflate(inflater,container,false)

        val view = bind.root

        return view

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

            bind.textView.text = "Kotlin"
    }



}