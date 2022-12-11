package com.example.abbtechgraduationproject.ui.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.abbtechgraduationproject.R
import com.example.abbtechgraduationproject.data.USERNAME
import com.example.abbtechgraduationproject.ui.adapter.CartAdapter
import com.example.abbtechgraduationproject.databinding.FragmentCartBinding
import com.example.abbtechgraduationproject.ui.viewmodels.CartScreenViewModel
import com.example.abbtechgraduationproject.ui.viewmodels.MainScreenViewModel
import com.google.android.material.snackbar.Snackbar
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class CartFragment : Fragment() {

    private val viewmodel: CartScreenViewModel by viewModels()
    private val mainViewModel: MainScreenViewModel by viewModels()
    private lateinit var binding: FragmentCartBinding

    var total = 0
    lateinit var itemTouchHelper: ItemTouchHelper
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

        initRecycler()
        observeFoods()
        initTouchHelper()


        binding.header.title.text = getString(R.string.your_order)

        adapter.btnDeleteClickListener = {

            val deleteItem = it
            Snackbar.make(
                binding.root,
                "${deleteItem.name} removed from cart",
                Snackbar.LENGTH_LONG
            ).apply {
                setAction("Undo") {
                    mainViewModel.addToCart(
                        deleteItem.name,
                        deleteItem.image,
                        deleteItem.price,
                        deleteItem.category,
                        deleteItem.orderAmount,
                        deleteItem.userName
                    )
                    observeFoods()
                }
                show()
            }

            viewmodel.deleteFromCart(deleteItem.cartId, deleteItem.userName)
        }
    }

    override fun onResume() {
        super.onResume()
        observeFoods()
    }


    private fun observeFoods() {
        viewmodel.getFromCart(USERNAME)
        viewmodel.foodsOnCart.observe(viewLifecycleOwner) {
            total = 0
            if (it != null && it.isNotEmpty()) {
                binding.recyclerCart.visibility = View.VISIBLE
                binding.plate.visibility = View.GONE
                binding.tvCartIsEmpty.visibility = View.GONE
                binding.tvCartTotal.visibility = View.VISIBLE

                for (a in it){
                    total+=a.price
                }
                binding.tvCartTotal.text = "Cart Total: ${total.toString()} $"
                adapter.submitList(it)
            } else {
                binding.tvCartTotal.visibility = View.GONE
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


    fun initTouchHelper() {
        val itemTouchHelperCallBack = object :
            ItemTouchHelper.SimpleCallback(0, ItemTouchHelper.LEFT or ItemTouchHelper.RIGHT) {
            override fun onMove(
                recyclerView: RecyclerView,
                viewHolder: RecyclerView.ViewHolder,
                target: RecyclerView.ViewHolder
            ): Boolean {
                return false
            }

            override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {
                adapter.onSwipe(viewHolder.adapterPosition)
            }
        }
        itemTouchHelper = ItemTouchHelper(itemTouchHelperCallBack)
        itemTouchHelper.attachToRecyclerView(binding.recyclerCart)
    }

}