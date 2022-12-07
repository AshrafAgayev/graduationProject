package com.example.abbtechgraduationproject.ui.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import com.example.abbtechgraduationproject.data.entities.FoodsOnCart
import com.example.abbtechgraduationproject.databinding.FragmentDetailsScreenBinding
import com.example.abbtechgraduationproject.ui.viewmodels.DetailsScreenViewModel

class DetailsScreenFragment : Fragment() {


    lateinit var viewmodel: DetailsScreenViewModel
    lateinit var binding: FragmentDetailsScreenBinding


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val tempviewmodel: DetailsScreenViewModel by viewModels()
        viewmodel = tempviewmodel
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        binding = FragmentDetailsScreenBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


    }

}