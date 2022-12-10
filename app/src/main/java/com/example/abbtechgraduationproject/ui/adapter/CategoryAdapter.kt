package com.example.abbtechgraduationproject.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.abbtechgraduationproject.data.entities.Food
import com.example.abbtechgraduationproject.databinding.CategoryItemBinding
import com.example.abbtechgraduationproject.utils.Category

class CategoryAdapter() : RecyclerView.Adapter<CategoryAdapter.CategoryViewHolder>() {

    lateinit var clickListener : (category:String)-> Unit

    private var list = ArrayList<Category>()
    inner class CategoryViewHolder(var binding: CategoryItemBinding):RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CategoryViewHolder {
        var binding = CategoryItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return CategoryViewHolder(binding)
    }

    override fun onBindViewHolder(holder: CategoryViewHolder, position: Int) {
        var item  = list[position]
        var binding = holder.binding
        binding.categoryName.text = item.name
        binding.icon.setImageResource(item.icon)

        binding.root.setOnClickListener {
            clickListener(item.name)
        }
    }

    fun submitList(list:List<Category>){
        this.list.clear()
        this.list.addAll(list)
        notifyDataSetChanged()
    }

    override fun getItemCount(): Int {
    return list.size
    }
}