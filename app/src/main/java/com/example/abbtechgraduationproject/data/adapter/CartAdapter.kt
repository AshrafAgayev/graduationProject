package com.example.abbtechgraduationproject.data.adapter


import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.abbtechgraduationproject.data.IMAGE_URL
import com.example.abbtechgraduationproject.data.USERNAME
import com.example.abbtechgraduationproject.data.entities.FoodsOnCart
import com.example.abbtechgraduationproject.databinding.CartItemViewBinding
import com.example.abbtechgraduationproject.ui.viewmodels.CartScreenViewModel

class CartAdapter(var cartList: List<FoodsOnCart>, var viewModel: CartScreenViewModel) :
    RecyclerView.Adapter<CartAdapter.CartViewHolder>() {

//    private val list = mutableListOf(cartList) todo

    class CartViewHolder(var binding: CartItemViewBinding) : RecyclerView.ViewHolder(binding.root) {

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CartViewHolder {

        val binding =
            CartItemViewBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return CartViewHolder(binding)
    }

    override fun onBindViewHolder(holder: CartViewHolder, position: Int) {
        val binding = holder.binding

        val item = cartList[position]



            binding.tvName.text = item.name

            binding.tvPrice.text = item.price.toString()

            val url = IMAGE_URL + item.image
            Glide.with(holder.itemView.context).load(url).into(binding.imageView)

            binding.btnDelete.setOnClickListener {
                viewModel.deleteFromCart(item.cartId, item.userName)
                viewModel.getFromCart(USERNAME)
            }

    }

    override fun getItemCount(): Int = cartList.size



}