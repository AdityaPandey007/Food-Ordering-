package com.thefounder.foodappkotlin.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.HorizontalScrollView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.thefounder.foodappkotlin.R
import com.thefounder.foodappkotlin.adapter.CategoryAdapter
import com.thefounder.foodappkotlin.adapter.PopularAdapter
import kotlinx.android.synthetic.main.fragment_home.*

class HomeFragment : Fragment() {

    private  var adapter : CategoryAdapter? = null
    private  var adapter2: PopularAdapter? = null

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_home, container, false)

        initRecyclerView(view)
        return view


    }

    private fun initRecyclerView(view: View){

        val recyclerViewCat = view.findViewById<RecyclerView>(R.id.recyclerViewCat)
        recyclerViewCat.layoutManager = LinearLayoutManager(activity, LinearLayoutManager.HORIZONTAL , false)

        adapter = CategoryAdapter()
        recyclerViewCat.adapter = adapter

        val recyclerViewPop = view.findViewById<RecyclerView>(R.id.recyclerViewPop)
        recyclerViewPop.layoutManager = LinearLayoutManager(activity , LinearLayoutManager.HORIZONTAL , false)

        adapter2 = PopularAdapter()
        recyclerViewPop.adapter = adapter2

    }


}