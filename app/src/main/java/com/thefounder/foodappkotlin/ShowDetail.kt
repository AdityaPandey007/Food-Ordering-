package com.thefounder.foodappkotlin

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_show_detail.*

class ShowDetail : AppCompatActivity() {

    private  var Image : Int? = null
    private var foodname : String? = null
    private var foodprice : String? = null
    private var foodDescription : String? = null
    private var totalQuantity : Int = 1

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_show_detail)

        Image = intent.getIntExtra("image",-1)
        foodImage.setImageResource(Image!!)

        foodname = intent.getStringExtra("name")
        foodTitle.text = foodname

        foodprice = intent.getStringExtra("price")
        foodRate.text = foodprice

        addItem.setOnClickListener {

            if (totalQuantity < 10){

                totalQuantity++
                noItem.text = totalQuantity.toString()
            }
        }

        minusItem.setOnClickListener {

            if (totalQuantity > 1)

                totalQuantity--
                noItem.text = totalQuantity.toString()
        }

        addToCart.setOnClickListener{

            addtoCart()
        }
    }

    private fun addtoCart() {

        val intent = Intent(this , CartActivity::class.java)
        intent.putExtra("foodName",foodname)
        intent.putExtra("image",Image)
        intent.putExtra("foodPrice" , foodprice)

        startActivity(intent)
    }
}