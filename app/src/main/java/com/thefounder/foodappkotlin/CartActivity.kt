package com.thefounder.foodappkotlin

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.thefounder.foodappkotlin.adapter.CartAdapter
import kotlinx.android.synthetic.main.activity_cart.*

class CartActivity : AppCompatActivity() {

    private lateinit var  layoutManager : RecyclerView.LayoutManager
    private lateinit var adapter : CartAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_cart)

        layoutManager = LinearLayoutManager(this)
        recyclerCart.layoutManager = layoutManager

        adapter = CartAdapter()
        recyclerCart.adapter = adapter
    }
}