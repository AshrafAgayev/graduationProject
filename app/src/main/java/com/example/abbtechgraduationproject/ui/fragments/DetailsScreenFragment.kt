package com.example.abbtechgraduationproject.ui.fragments

import android.icu.number.IntegerWidth
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.navigation.NavArgs
import androidx.navigation.Navigation
import androidx.navigation.fragment.navArgs
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.bitmap_recycle.IntegerArrayAdapter
import com.example.abbtechgraduationproject.data.IMAGE_URL
import com.example.abbtechgraduationproject.data.USERNAME
import com.example.abbtechgraduationproject.data.entities.FoodsOnCart
import com.example.abbtechgraduationproject.databinding.FragmentDetailsScreenBinding
import com.example.abbtechgraduationproject.ui.viewmodels.DetailsScreenViewModel
import com.example.abbtechgraduationproject.utils.or
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class DetailsScreenFragment : Fragment() {


    lateinit var viewmodel: DetailsScreenViewModel
    lateinit var binding: FragmentDetailsScreenBinding
    private var orderAmount = 1

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


        val args: DetailsScreenFragmentArgs by navArgs()
        val food = args.foodDetails

        binding.orderAmount.text = orderAmount.toString()
        initButtons()

        binding.tvFoodName.text = food.name
        binding.tvFoodPrice.text = food.price.toString()

        Glide.with(binding.root).load(IMAGE_URL + food.image).into(binding.imgItem)



        binding.btnAddCart.setOnClickListener {
            var orderAmount = binding.orderAmount.text.toString().toInt()
            Log.d("Order Amount", " on detail screen: $orderAmount")

            var deleteList = arrayListOf<Int>()


            viewmodel.getFromCart(USERNAME)
            viewmodel.foodsOnCart.observe(viewLifecycleOwner) { list ->
                Log.d("List ", "$list")
                for (a in list) {
                    if (a.name == food.name) {
                        orderAmount += a.orderAmount
                        deleteList.add(a.cartId)
                        Log.d("Order Amount", " deleted: ${a.orderAmount}")
                    }
                }

                Log.d("Order Amount"," added: $orderAmount")
                viewmodel.addToCart(
                    food.name,
                    food.image,
                    food.price,
                    food.category,
                    orderAmount,
                    USERNAME
                )

                for (item in deleteList){
                    viewmodel.deleteFromCart(item, USERNAME)
                }
            }


        }
    }

    fun initButtons() {

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
    }

}