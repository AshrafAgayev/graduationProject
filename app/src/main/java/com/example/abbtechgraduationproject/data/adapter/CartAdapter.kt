package com.example.abbtechgraduationproject.data.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.abbtechgraduationproject.data.IMAGE_URL
import com.example.abbtechgraduationproject.data.entities.FoodsOnCart
import com.example.abbtechgraduationproject.databinding.CartItemViewBinding

class CartAdapter(var context: Context, var cartList: List<FoodsOnCart>) :
    RecyclerView.Adapter<CartAdapter.CartViewHolder>() {


    class CartViewHolder(var binding: CartItemViewBinding) : RecyclerView.ViewHolder(binding.root) {

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CartViewHolder {

        var binding = CartItemViewBinding.inflate(LayoutInflater.from(context), parent, false)
        return CartViewHolder(binding)
    }

    override fun onBindViewHolder(holder: CartViewHolder, position: Int) {
       val item = cartList[position]
        val binding = holder.binding

        binding.tvName.text = item.name

        binding.tvPrice.text = item.price.toString()

        val url = IMAGE_URL + item.image
        Glide.with(context).load(url).into(binding.imageView)

    }

    override fun getItemCount(): Int {
        return cartList.size
    }
}