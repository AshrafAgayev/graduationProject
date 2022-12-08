package com.example.abbtechgraduationproject.data.adapter

import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.navigation.Navigation
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.abbtechgraduationproject.R
import com.example.abbtechgraduationproject.data.IMAGE_URL
import com.example.abbtechgraduationproject.data.entities.Food
import com.example.abbtechgraduationproject.databinding.SingleViewFoodBinding
import com.example.abbtechgraduationproject.ui.fragments.MainScreenFragmentDirections
import com.example.abbtechgraduationproject.ui.viewmodels.MainScreenViewModel

class FoodsAdapter :
    RecyclerView.Adapter<FoodsAdapter.FoodsViewHolder>() {

    private var foodsList = ArrayList<Food>()

    lateinit var onBtnAddClickListener: (Food) -> Unit

    inner class FoodsViewHolder(var binding: SingleViewFoodBinding) :
        RecyclerView.ViewHolder(binding.root) {
        init {
            binding.btnAddCart.setOnClickListener {
                onBtnAddClickListener(foodsList[adapterPosition])
            }
        }

        fun bindData(food: Food) {

            binding.foodName.text = food.name
            binding.foodPrice.text = food.price.toString()
            val url = IMAGE_URL + food.image

            Glide.with(binding.root.context).load(url).placeholder(R.drawable.download)
                .into(binding.foodImage)

        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FoodsViewHolder {

        val binding =
            SingleViewFoodBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return FoodsViewHolder(binding)
    }

    fun submitList(_foodList: List<Food>) {
        Log.d("mytag", "submitList: ${_foodList}")
        this.foodsList.clear()
        this.foodsList.addAll(_foodList)
        notifyDataSetChanged()
        println(foodsList)
    }

    override fun onBindViewHolder(holder: FoodsViewHolder, position: Int) {
        val food = foodsList[position]
        holder.bindData(food)
    }

    override fun getItemCount(): Int {
        return foodsList.size
    }
}