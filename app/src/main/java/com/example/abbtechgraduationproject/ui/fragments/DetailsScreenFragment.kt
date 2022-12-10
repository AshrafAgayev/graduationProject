package com.example.abbtechgraduationproject.ui.fragments

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.navArgs
import com.bumptech.glide.Glide
import com.example.abbtechgraduationproject.data.IMAGE_URL
import com.example.abbtechgraduationproject.data.USERNAME
import com.example.abbtechgraduationproject.databinding.FragmentDetailsScreenBinding
import com.example.abbtechgraduationproject.ui.viewmodels.DetailsScreenViewModel
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class DetailsScreenFragment : Fragment() {


    lateinit var viewmodel: DetailsScreenViewModel
    lateinit var binding: FragmentDetailsScreenBinding
    private var orderAmount = 1
    var price = 0

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
        initButtons()
    }

    fun initButtons() {
        val args: DetailsScreenFragmentArgs by navArgs()
        val food = args.foodDetails

        binding.btnDecrease.setOnClickListener {
            if (orderAmount > 1) {
                orderAmount--
            }
            binding.orderAmount.text = orderAmount.toString()
        }

        binding.btnIncrease.setOnClickListener {
            orderAmount++
            binding.orderAmount.text = orderAmount.toString()

        }

        binding.orderAmount.text = orderAmount.toString()
        binding.tvFoodName.text = food.name
        binding.tvFoodPrice.text = "${food.price} $"
        Glide.with(binding.root).load(IMAGE_URL + food.image).into(binding.imgItem)

        binding.btnAddCart.setOnClickListener {
            var orderAmount = binding.orderAmount.text.toString().toInt()

            val deleteList = arrayListOf<Int>()

            viewmodel.getFromCart(USERNAME)
            viewmodel.foodsOnCart.observe(viewLifecycleOwner) { list ->
                Log.d("List ", "$list")
                for (a in list) {
                    if (a.name == food.name) {
                        orderAmount += a.orderAmount
                        deleteList.add(a.cartId)
                    }
                }
            }

            for (item in deleteList) {
                viewmodel.deleteFromCart(item, USERNAME)
            }

            price = (food.price * orderAmount)
            viewmodel.addToCart(
                food.name,
                food.image,
                price,
                food.category,
                orderAmount,
                USERNAME
            )
        }
    }

    override fun onResume() {
        super.onResume()
        viewmodel.getFromCart(USERNAME)
    }

}