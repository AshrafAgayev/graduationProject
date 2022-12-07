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


        try {
            val url = "http://kasimadalan.pe.hu/foods/images/"+ food.image

            Glide.with(context).load(url).into(binding.foodImage)

        }catch (exception:Exception){
            Glide.with(context).load("http://kasimadalan.pe.hu/foods/images/meatball.png").into(binding.foodImage)
        }



        binding.btnAddCart.setOnClickListener {
            viewModel.addToCart(food.name, food.image, food.price, food.category, 3, "Ashraf")
        }

        
    }

    override fun getItemCount(): Int {
        return foodsList.size
    }
}