package com.thefounder.foodappkotlin.adapter

import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.thefounder.foodappkotlin.R
import com.thefounder.foodappkotlin.ShowDetail

class CategoryAdapter : RecyclerView.Adapter<CategoryAdapter.ViewHolder>() {

    private val category_name = arrayOf("Burger" , "Pizza" , "Veg-Thali" , "west - Thali")

    private val category_image = intArrayOf(
        R.drawable.foodapp1,
        R.drawable.foodapp2,
        R.drawable.foodapp3,
        R.drawable.foodapp4
    )
  inner class ViewHolder(itemView : View) : RecyclerView.ViewHolder(itemView) {

      var image : ImageView
      var name : TextView

      init {

          image = itemView.findViewById(R.id.catImage)
          name = itemView.findViewById(R.id.catName)
      }
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {

        val view = LayoutInflater.from(parent.context).inflate(R.layout.category_carrd,parent , false)

        return ViewHolder(view)
    }


    override fun onBindViewHolder(holder: ViewHolder, position: Int) {

        holder.image.setImageResource(category_image[position])
        holder.name.text = category_name[position]

        holder.itemView.setOnClickListener {

            val intent = Intent(holder.itemView.context , ShowDetail::class.java)
            intent.putExtra("image" , category_image[position])
            intent.putExtra("name" , category_name[position])

            holder.itemView.context.startActivity(intent)
        }
    }


    override fun getItemCount(): Int {

        return category_image.size
    }
}