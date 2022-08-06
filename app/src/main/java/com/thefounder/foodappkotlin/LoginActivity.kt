package com.thefounder.foodappkotlin

import android.content.ContentValues
import android.content.ContentValues.TAG
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.util.Patterns
import android.widget.Toast
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInClient
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.google.android.gms.common.api.ApiException
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.auth.GoogleAuthProvider
import kotlinx.android.synthetic.main.activity_login.*
import kotlinx.android.synthetic.main.activity_register.*

open class LoginActivity : AppCompatActivity() {

    private lateinit var auth: FirebaseAuth

    private lateinit var googleSignInClient : GoogleSignInClient
    private val RC_SIGN_IN = 40

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        auth = FirebaseAuth.getInstance()


        if(auth.currentUser != null){
            startActivity(Intent(this , HomePage::class.java))
            finish()
        }

        logInbtn.setOnClickListener{

            LogIn()
        }

        registerTxt.setOnClickListener {

            val intent = Intent(this , RegisterActivity::class.java)
            startActivity(intent)
            finish()
        }

        //Configure Google Sign In

        val gso =  GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
            .requestIdToken(getString(R.string.default_web_client_Id))
            .requestEmail()
            .build()

        googleSignInClient = GoogleSignIn.getClient(this , gso)

        // FaceBook LogIn EventListener
        facebookSignIn.setOnClickListener {

            val intent = Intent(this , FaceBookAuthentication::class.java)
            intent.setFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION)
            startActivity(intent)
        }
    }

    private fun LogIn() {

        val email = editEmaiLogIn.text.toString()
        val password = editPasswordLogIn.text.toString()



        if (email.isEmpty()){
            editEmaiLogIn.error = "Please Enter Valid Email"
            editEmaiLogIn.requestFocus()
        }

        if(editPasswordLogIn.text.toString().isEmpty()){
            editPasswordLogIn.error = "Please Enter Password"
            editPasswordLogIn.requestFocus()
        }


        auth.signInWithEmailAndPassword(email, password)
            .addOnCompleteListener(this) { task ->

                if (task.isSuccessful){

                    val user = auth.currentUser
                    updateUi(user)
                }
                else{

                    Toast.makeText(this, "Login Failed !!", Toast.LENGTH_SHORT).show()
                }
            }
    }

    private fun updateUi(user: FirebaseUser?) {

        if (user != null){
            startActivity(Intent(this , HomePage::class.java))
            finish()
        }

        else {

            Toast.makeText(this, "LogIn Failed", Toast.LENGTH_SHORT).show()
        }
    }

    fun signIn(view: android.view.View) {

        val signInIntent = googleSignInClient.signInIntent
        startActivityForResult(signInIntent,RC_SIGN_IN)
    }


    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        // Result returned from launching the Intent from GoogleSignInApi.getSignInIntent(...);
        if (requestCode == RC_SIGN_IN) {
            val task = GoogleSignIn.getSignedInAccountFromIntent(data)
            try {
                // Google Sign In was successful, authenticate with Firebase
                val account = task.getResult(ApiException::class.java)!!
                Log.d(TAG, "firebaseAuthWithGoogle:" + account.id)
                firebaseAuthWithGoogle(account.idToken!!)
            } catch (e: ApiException) {
                // Google Sign In failed, update UI appropriately
                Log.w(TAG, "Google sign in failed", e)
            }
        }
    }

    private fun firebaseAuthWithGoogle(idToken: String) {

        val auth = FirebaseAuth.getInstance()
        val credential = GoogleAuthProvider.getCredential(idToken, null)
        auth.signInWithCredential(credential)
            .addOnCompleteListener(this) { task ->
                if (task.isSuccessful) {

                    val intent = Intent(this , HomePage::class.java)
                    startActivity(intent)

                } else {
                    // If sign in fails, display a message to the user.
                    Log.w(ContentValues.TAG, "signInWithCredential:failure", task.exception)

                }
            }
    }
}