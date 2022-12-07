package com.example.abbtechgraduationproject.ui.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.abbtechgraduationproject.data.adapter.CartAdapter
import com.example.abbtechgraduationproject.data.entities.FoodsOnCart
import com.example.abbtechgraduationproject.databinding.FragmentCartBinding
import com.example.abbtechgraduationproject.ui.viewmodels.CartScreenViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class CartFragment : Fragment() {

    lateinit var viewmodel: CartScreenViewModel
    private lateinit var binding: FragmentCartBinding


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val tempviewmodel: CartScreenViewModel by viewModels()
        viewmodel = tempviewmodel
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        binding = FragmentCartBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


        viewmodel.foodsOnCart.observe(viewLifecycleOwner) { list->

            val adapter = CartAdapter(requireContext(), list )
            binding.recyclerCart.layoutManager =  LinearLayoutManager(requireContext(), LinearLayoutManager.VERTICAL, false)
            binding.recyclerCart.adapter = adapter

            println(list)

        }




    }

}