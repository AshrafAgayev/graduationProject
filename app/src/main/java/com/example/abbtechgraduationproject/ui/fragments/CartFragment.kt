package com.example.abbtechgraduationproject.ui.fragments

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.abbtechgraduationproject.data.USERNAME
import com.example.abbtechgraduationproject.data.adapter.CartAdapter
import com.example.abbtechgraduationproject.data.entities.FoodsOnCart
import com.example.abbtechgraduationproject.databinding.FragmentCartBinding
import com.example.abbtechgraduationproject.ui.viewmodels.CartScreenViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class CartFragment : Fragment() {

    private val viewmodel: CartScreenViewModel by viewModels()
    private lateinit var binding: FragmentCartBinding

    //    lateinit var itemTouchHelper: ItemTouchHelper
    lateinit var cartList: ArrayList<FoodsOnCart>
    val adapter by lazy { CartAdapter() }


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {


        binding = FragmentCartBinding.inflate(inflater, container, false)

        return binding.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
//        viewmodel.getCart(USERNAME)

        initRecycler()
        observeFoods()
//        observe()
        adapter.btnDeleteClickListener = {

            println("${it.cartId}, ${it.name}")
            viewmodel.deleteFromCart(it.cartId, it.userName)
        }

    }

    override fun onResume() {
        super.onResume()
        observeFoods()
    }


    private fun observeFoods() {
        viewmodel.getFromCart(USERNAME)
        viewmodel.foodsOnCart.observe(viewLifecycleOwner){
            if (it != null && it.isNotEmpty()) {
                binding.recyclerCart.visibility = View.VISIBLE
                binding.plate.visibility = View.GONE
                binding.tvCartIsEmpty.visibility = View.GONE

                adapter.submitList(it)
            } else {

                binding.recyclerCart.visibility = View.GONE
                binding.plate.visibility = View.VISIBLE
                binding.tvCartIsEmpty.visibility = View.VISIBLE
            }
        }

    }


    fun initRecycler() = with(binding) {
        recyclerCart.layoutManager =
            LinearLayoutManager(requireContext(), LinearLayoutManager.VERTICAL, false)
        recyclerCart.adapter = adapter
    }

//    fun observe() {
//        viewLifecycleOwner.lifecycleScope.launchWhenCreated {
//            viewmodel.food.collect {
//                adapter.submitList(it)
//            }
//        }
//    }

//    fun initTouchHelper() {
//        val itemTouchHelperCallBack = object :
//            ItemTouchHelper.SimpleCallback(0, ItemTouchHelper.LEFT or ItemTouchHelper.RIGHT) {
//            override fun onMove(
//                recyclerView: RecyclerView,
//                viewHolder: RecyclerView.ViewHolder,
//                target: RecyclerView.ViewHolder
//            ): Boolean {
//                return false
//            }
//
//            override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {
//
//                val position = viewHolder.adapterPosition
//
//                viewmodel.deleteFromCart(cartList[position].cartId, cartList[position].userName)
////                viewmodel.getFromCart(USERNAME)
//                binding.recyclerCart.adapter?.notifyDataSetChanged()
//
//            }
//        }
//
//        itemTouchHelper = ItemTouchHelper(itemTouchHelperCallBack)
//        itemTouchHelper.attachToRecyclerView(binding.recyclerCart)
//
//    }

}