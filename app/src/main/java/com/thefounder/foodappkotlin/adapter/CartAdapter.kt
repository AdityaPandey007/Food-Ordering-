package com.thefounder.foodappkotlin.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.thefounder.foodappkotlin.R

class CartAdapter : RecyclerView.Adapter<CartAdapter.ViewHolder>() {

    private val cartimage = intArrayOf(
        R.drawable.foodapp1,
        R.drawable.foodapp2,
        R.drawable.foodapp3,
        R.drawable.foodapp4
    )

    private val cartname = arrayOf("Burger" , "Pizza" , "Veg-Thali" , "Western-Thali")

    private val cartRate = arrayOf("Rs.180" , "Rs.250" , "Rs.100" , "Rs.120")


    inner class ViewHolder(itemview : View) : RecyclerView.ViewHolder(itemview) {

        var image : ImageView
        var nam : TextView
        var item_rate : TextView
        var current_rate : TextView

        init {

            image = itemview.findViewById(R.id.cartImage)
            nam = itemview.findViewById(R.id.cartName)
            item_rate = itemview.findViewById(R.id.item_rate)
            current_rate = itemview.findViewById(R.id.current_rate)


        }
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {

        val view = LayoutInflater.from(parent.context).inflate(R.layout.cart_elements, parent , false)

        return ViewHolder(view)
    }


    override fun onBindViewHolder(holder: ViewHolder, position: Int) {

        holder.image.setImageResource(cartimage[position])
        holder.nam.text = cartname[position]
        holder.item_rate.text = cartRate[position]

    }


    override fun getItemCount(): Int {

        return cartname.size
    }
}