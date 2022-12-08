package com.example.abbtechgraduationproject.ui.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.abbtechgraduationproject.data.USERNAME
import com.example.abbtechgraduationproject.data.adapter.CartAdapter
import com.example.abbtechgraduationproject.data.entities.FoodsOnCart
import com.example.abbtechgraduationproject.databinding.FragmentCartBinding
import com.example.abbtechgraduationproject.ui.viewmodels.CartScreenViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class CartFragment : Fragment() {

    private val viewmodel: CartScreenViewModel by viewModels()
    private lateinit var binding: FragmentCartBinding
//    lateinit var itemTouchHelper: ItemTouchHelper
    lateinit var cartList: ArrayList<FoodsOnCart>



    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {


        binding = FragmentCartBinding.inflate(inflater, container, false)

        return binding.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        observeFoods()

//        initTouchHelper()

    }

    private fun observeFoods() = with(binding) {

        viewmodel.foodsOnCart.observe(viewLifecycleOwner) { list ->

            val adapter = CartAdapter(list, viewmodel)
            recyclerCart.layoutManager =
                LinearLayoutManager(requireContext(), LinearLayoutManager.VERTICAL, false)
            recyclerCart.adapter = adapter

            println(list)
        }
    }
    override fun onResume() {
        super.onResume()
        viewmodel.getFromCart(USERNAME)
    }


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