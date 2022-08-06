package com.thefounder.foodappkotlin.adapter

import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.thefounder.foodappkotlin.R
import com.thefounder.foodappkotlin.ShowDetail

class PopularAdapter : RecyclerView.Adapter<PopularAdapter.Viewholder>() {

    private val popular_image = intArrayOf(
        R.drawable.foodapp1,
        R.drawable.foodapp2,
        R.drawable.foodapp3,
        R.drawable.foodapp4
    )

    private val popular_name = arrayOf("Cheese Burger" , "Popular Pizza" , "Veg Thali" , "Western Thali")

    private val popular_price = arrayOf("Rs.180" , "Rs.250" , "Rs.100" , "Rs.120")

    inner class Viewholder(itemView :View) :RecyclerView.ViewHolder(itemView){

        var image : ImageView
        var name : TextView
        var price : TextView
        var button : Button


        init {
            image = itemView.findViewById(R.id.imageFood)
            name = itemView.findViewById(R.id.popularName)
            price = itemView.findViewById(R.id.foodPrice)
            button = itemView.findViewById(R.id.btn_buy)
        }
    }



    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Viewholder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.popular_card, parent , false)

        return Viewholder(view)
    }


    override fun onBindViewHolder(holder: Viewholder, position: Int) {

        holder.image.setImageResource(popular_image[position])
        holder.name.text = popular_name[position]
        holder.price.text = popular_price[position]

        holder.button.setOnClickListener {

            val intent = Intent(holder.itemView.context , ShowDetail::class.java)
            intent.putExtra("image" , popular_image[position])
            intent.putExtra("name" , popular_name[position])
            intent.putExtra("price" ,popular_price[position])

            holder.itemView.context.startActivity(intent)
        }
    }


    override fun getItemCount(): Int {

        return popular_name.size
    }
}