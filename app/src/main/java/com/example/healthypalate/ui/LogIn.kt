package com.example.healthypalate.ui

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.example.healthypalate.R
import com.example.healthypalate.databinding.ActivityLogInBinding
import com.example.healthypalate.ui.getstarted.GetStarted
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser

class LogIn : AppCompatActivity() {

    val binding: ActivityLogInBinding by lazy {
        ActivityLogInBinding.inflate(layoutInflater)
    }

    private lateinit var auth : FirebaseAuth
    private var user: FirebaseUser? = null
    private lateinit var authState: FirebaseAuth.AuthStateListener
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        var email = binding.email.text.toString()
        var password = binding.password.text.toString()

        setUpFirebaseAuth()
        binding.signUp.setOnClickListener {
            var intent = Intent(this@LogIn, Register::class.java)
            startActivity(intent)
        }

        auth = FirebaseAuth.getInstance()
        user = auth.currentUser

        binding.logIn.setOnClickListener {
            if (binding.email.text.toString().isNotBlank() && binding.password.text.toString().isNotBlank()){
                loginUser(binding.email.text.toString(), binding.password.text.toString())
            }else{
                Toast.makeText(this, "Enter all fields", Toast.LENGTH_LONG).show()
            }
        }
    }

    private fun loginUser(email: String, password: String) {
        auth.signInWithEmailAndPassword(email, password)
            .addOnCompleteListener {
                Toast.makeText(this@LogIn, "Authenticated: ${auth.currentUser?.email}", Toast.LENGTH_SHORT).show()
            }
            .addOnFailureListener {
                Toast.makeText(this@LogIn, "Authentication failed", Toast.LENGTH_SHORT).show()
            }
    }

    private fun setUpFirebaseAuth(){

        authState = FirebaseAuth.AuthStateListener {
            val user = it.currentUser

            if (user != null){
                if (user.isEmailVerified){
                    Toast.makeText(this@LogIn,
                        "Signed in: ${user.email}",
                        Toast.LENGTH_SHORT).show()

                    val intent = Intent(this@LogIn, GetStarted::class.java)
                    startActivity(intent)
                    finish()
                }else{
                    Toast.makeText(this@LogIn,
                    "Check email inbox to verify", Toast.LENGTH_SHORT).show()
                    auth.signOut()
                }

            }else{
                Toast.makeText(this@LogIn,
                    "Signed out",
                    Toast.LENGTH_SHORT).show()
            }
        }
    }

    override fun onStart() {
        super.onStart()
        auth.addAuthStateListener(authState)
    }

    override fun onStop() {
        super.onStop()
        if (authState != null){
            auth.removeAuthStateListener(authState)
        }
    }
}