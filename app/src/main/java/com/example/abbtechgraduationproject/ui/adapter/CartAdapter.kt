package com.example.abbtechgraduationproject.ui.adapter


import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.abbtechgraduationproject.data.IMAGE_URL
import com.example.abbtechgraduationproject.data.entities.FoodsOnCart
import com.example.abbtechgraduationproject.databinding.CartItemViewBinding
import com.example.abbtechgraduationproject.ui.viewmodels.CartScreenViewModel
import com.example.abbtechgraduationproject.utils.Category

class CartAdapter() :
    RecyclerView.Adapter<CartAdapter.CartViewHolder>(){

    //    private val list = mutableListOf(cartList) todo
    lateinit var btnDeleteClickListener: (FoodsOnCart) -> Unit

    var cartList = ArrayList<FoodsOnCart>()

    inner class CartViewHolder(var binding: CartItemViewBinding) :
        RecyclerView.ViewHolder(binding.root) {
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CartViewHolder {

        val binding =
            CartItemViewBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return CartViewHolder(binding)
    }

    override fun onBindViewHolder(holder: CartViewHolder, position: Int) {
        val binding = holder.binding
        val item = cartList[position]
        val price = "Total: ${item.price} $"
        binding.tvName.text = item.name
        binding.tvAmount.text = item.orderAmount.toString()
        binding.tvPrice.text = price
        val url = IMAGE_URL + item.image
        Glide.with(holder.itemView.context).load(url).into(binding.imageView)
    }

    override fun getItemCount(): Int = cartList.size


    fun submitList(list: List<FoodsOnCart>) {
        cartList.clear()
        cartList.addAll(list)
        notifyDataSetChanged()
    }

    fun onSwipe(adapterPosition: Int) {
        btnDeleteClickListener(cartList[adapterPosition])
    }




}