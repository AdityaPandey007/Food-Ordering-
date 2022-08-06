package com.thefounder.foodappkotlin

import android.content.ContentValues.TAG
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.util.Patterns
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.ktx.Firebase
import com.thefounder.foodappkotlin.dataClass.User
import kotlinx.android.synthetic.main.activity_register.*

class RegisterActivity : AppCompatActivity() {

    private lateinit var database : DatabaseReference
    private lateinit var auth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register)

        // Initialize Firebase Auth
        auth = Firebase.auth

        registerbtn.setOnClickListener {
            signUp()
        }

        logInTxt.setOnClickListener {

            val intent = Intent(this , LoginActivity::class.java)
            startActivity(intent)
            finish()
        }
    }

    public override fun onStart() {
        super.onStart()
        // Check if user is signed in (non-null) and update UI accordingly.
        val currentUser = auth.currentUser


    }

    fun signUp(){

        val email = editEmail.text.toString()
        val password = editPassword.text.toString()

        if (editName.text.toString().isEmpty()){
            editName.error = "Please Enter Name"
            editName.requestFocus()
        }

        if (!!Patterns.EMAIL_ADDRESS.matcher(editName.text.toString()).matches()){
            editEmail.error = "Please Enter Valid Email"
            editEmail.requestFocus()
        }

        if(editPassword.text.toString().isEmpty()){
            editPassword.error = "Please Enter Password"
            editPassword.requestFocus()
        }


        auth.createUserWithEmailAndPassword(email,password)
            .addOnCompleteListener(this) { task ->
                if (task.isSuccessful) {

                    val email = editEmail.text.toString()
                    val password = editPassword.text.toString()
                    val name = editName.text.toString()


                    database =  FirebaseDatabase.getInstance().getReference("Users")
                    val User = User(name,email,password)
                    database.child(name).setValue(User)

                    startActivity(Intent(this , LoginActivity::class.java))
                    finish()

                }
                else {
                    // If sign in fails, display a message to the user.
                    Toast.makeText(baseContext, "Authentication failed.", Toast.LENGTH_SHORT).show()

                }
            }
    }
}