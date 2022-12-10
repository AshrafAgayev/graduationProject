package com.example.abbtechgraduationproject.ui.fragments

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.SearchView
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
    var queryList = arrayListOf<Food>()


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
        setSeatchView()

        adapter.onBtnAddClickListener = {
            navigateDetails(it)
        }


    }

    private fun navigateDetails(food: Food) {
        val action =
            MainScreenFragmentDirections.actionMainScreenFragmentToDetailsScreenFragment(
                food
            )
        findNavController().navigate(action)
    }

    private fun observeFoods() {
        viewModel.foodList.observe(viewLifecycleOwner) {
            Log.d("mytag", "observe: ${it}")
            adapter.submitList(it)
        }
    }

    private fun initRecyclerView() {
        binding.rvMainCategory.layoutManager =
            LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false)
        binding.rvMainCategory.adapter = adapter
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val tempviewModel: MainScreenViewModel by viewModels()
        viewModel = tempviewModel
    }

    override fun onResume() {
        super.onResume()
        viewModel.foodList.observe(viewLifecycleOwner) { list ->
            queryList.addAll(list)

        }
    }


    fun setSeatchView() {


        binding.searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(p0: String?): Boolean {
                binding.searchView.clearFocus()

                val newList = queryList.filter {
                    it.name.contains(p0.toString())
                }
                adapter.submitList(newList)
                return false
            }

            override fun onQueryTextChange(p0: String?): Boolean {
                val newList = queryList.filter {
                    it.name.contains(p0.toString())
                }
                adapter.submitList(newList)
                return false
            }

        })


    }
}