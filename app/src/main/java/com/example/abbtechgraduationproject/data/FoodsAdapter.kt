package com.example.abbtechgraduationproject.data

import android.content.Context
import android.text.Layout
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.abbtechgraduationproject.data.entities.Foods
import com.example.abbtechgraduationproject.databinding.SingleViewFoodBinding
import com.example.abbtechgraduationproject.ui.viewmodels.MainScreenViewModel

class FoodsAdapter(var context: Context, var foodsList: List<Foods>, var viewModel: MainScreenViewModel) :
    RecyclerView.Adapter<FoodsAdapter.FoodsViewHolder>() {


    inner class FoodsViewHolder(var binding: SingleViewFoodBinding) :
        RecyclerView.ViewHolder(binding.root) {

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FoodsViewHolder {

        val binding = SingleViewFoodBinding.inflate(LayoutInflater.from(context), parent, false)
        return FoodsViewHolder(binding)
    }

    override fun onBindViewHolder(holder: FoodsViewHolder, position: Int) {

        val food = foodsList[position]

        val binding = holder.binding

        binding.foodName.text = food.name
        binding.foodPrice.text = food.price.toString()

//
//        try {
//            val url =viewModel.getImage(food.image)
//            Glide.with(context).load(url).into(binding.foodImage)
//        }catch (exception:Exception){
//            println(exception)
//        }

        
    }

    override fun getItemCount(): Int {
        return foodsList.size
    }
}