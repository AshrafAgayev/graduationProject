package com.example.abbtechgraduationproject.ui.fragments

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.navigation.Navigation
import androidx.navigation.Navigation.findNavController
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.abbtechgraduationproject.data.adapter.FoodsAdapter
import com.example.abbtechgraduationproject.data.entities.Food
import com.example.abbtechgraduationproject.databinding.FragmentMainScreenBinding
import com.example.abbtechgraduationproject.ui.viewmodels.MainScreenViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainScreenFragment : Fragment() {

    private lateinit var binding: FragmentMainScreenBinding
    lateinit var viewModel: MainScreenViewModel
    private val adapter: FoodsAdapter by lazy { FoodsAdapter() }


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        binding = FragmentMainScreenBinding.inflate(inflater, container, false)

        return binding.root
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initRecyclerView()
        observeFoods()

        adapter.onBtnAddClickListener = {
            navigateDetails(it)
        }


    }

    private fun navigateDetails(food:Food){
        val action =
            MainScreenFragmentDirections.actionMainScreenFragmentToDetailsScreenFragment(
                food
            )
        findNavController().navigate(action)
    }

    private fun observeFoods(){
        viewModel.foodList.observe(viewLifecycleOwner) {
            Log.d("mytag", "observe: ${it}")
            adapter.submitList(it)
        }
    }

    private fun initRecyclerView(){
        binding.rvMainCategory.layoutManager =
            LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false)
        binding.rvMainCategory.adapter = adapter
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val tempviewModel: MainScreenViewModel by viewModels()
        viewModel = tempviewModel
    }
}