package com.thefounder.foodappkotlin

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.ContactsContract
import android.view.MenuItem
import android.widget.Toast
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.firebase.auth.FirebaseAuth
import com.thefounder.foodappkotlin.adapter.CategoryAdapter
import com.thefounder.foodappkotlin.adapter.PopularAdapter
import com.thefounder.foodappkotlin.fragments.*
import kotlinx.android.synthetic.main.activity_home_page.*

class HomePage : AppCompatActivity() {

    private lateinit var toggle : ActionBarDrawerToggle
    private lateinit var auth : FirebaseAuth

    private val homeFragment = HomeFragment()
    private val profileFragments = ProfileFragment()
    private val chatFragments = ChatSupportFragment()
    private val settingFragments = SettingFragment()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home_page)
        replaceFragment(homeFragment)

        auth = FirebaseAuth.getInstance()

        // for navigation drawer
        toggle = ActionBarDrawerToggle(this , drawerlayout , R.string.open , R.string.close)
        drawerlayout.addDrawerListener(toggle)
        toggle.syncState()
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        bottomNav.background = null
        bottomNav.menu.getItem(2).isEnabled = false

        bottomNav.setOnNavigationItemSelectedListener {
            when(it.itemId){

                R.id.home -> replaceFragment(homeFragment)
                R.id.profile -> replaceFragment(profileFragments)
                R.id.chat -> replaceFragment(chatFragments)
                R.id.setting -> replaceFragment(settingFragments)

            }
            true
        }

        navView.setNavigationItemSelectedListener {
            when (it.itemId) {
                R.id.logOut -> auth.signOut()
            }
            true
        }
    }


    private fun replaceFragment(fragment: Fragment) {

        if (fragment!= null){

            var transaction = supportFragmentManager.beginTransaction()
            transaction.replace(R.id.frameLayout,fragment)
            transaction.commit()
        }
        floating.setOnClickListener {

            val intent = Intent(this , CartActivity::class.java)
            startActivity(intent)
        }
    }


    override fun onOptionsItemSelected(item: MenuItem): Boolean {

        if (toggle.onOptionsItemSelected(item))
            return true
        return super.onOptionsItemSelected(item)
    }
}