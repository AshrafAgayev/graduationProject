package com.example.abbtechgraduationproject.ui.fragments

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.abbtechgraduationproject.data.FoodsAdapter
import com.example.abbtechgraduationproject.data.entities.Foods
import com.example.abbtechgraduationproject.databinding.FragmentMainScreenBinding
import com.example.abbtechgraduationproject.ui.viewmodels.MainScreenViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainScreenFragment : Fragment() {

    private lateinit var binding: FragmentMainScreenBinding
    lateinit var viewModel: MainScreenViewModel


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {


        binding = FragmentMainScreenBinding.inflate(inflater, container, false)

        return binding.root
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel.foodsList.observe(viewLifecycleOwner) {

            val adapter = FoodsAdapter(requireContext(), it, viewModel)

            binding.rvMainCategory.layoutManager =
                LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false)
            binding.rvMainCategory.adapter = adapter
        }



        viewModel.foodsOnCart.observe(viewLifecycleOwner) {
            println(it)
            Log.e("Foods on cart", "Couldnt call")

        }


    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val tempviewModel: MainScreenViewModel by viewModels()
        viewModel = tempviewModel
    }
}