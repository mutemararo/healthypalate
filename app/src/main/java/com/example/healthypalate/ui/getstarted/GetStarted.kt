package com.example.healthypalate.ui.getstarted

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import com.example.healthypalate.R
import com.example.healthypalate.databinding.ActivityGetStartedBinding
import com.example.healthypalate.ui.LogIn
import com.google.firebase.auth.FirebaseAuth

class GetStarted : AppCompatActivity() {

    private val binding: ActivityGetStartedBinding by lazy {
        ActivityGetStartedBinding.inflate(layoutInflater)
    }


    private lateinit var navController: NavController
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        val navHostFragment = supportFragmentManager.findFragmentById(R.id.get_started_host_frag) as NavHostFragment
        navController = navHostFragment.navController
    }

    override fun onResume() {
        super.onResume()
        checkAuthenticationState()
    }

    private fun checkAuthenticationState(){

        val user = FirebaseAuth.getInstance().currentUser

        if (user == null){
            Toast.makeText(this@GetStarted, "Not Authenticated, Redirecting to Login", Toast.LENGTH_SHORT).show()

            val intent = Intent(this@GetStarted, LogIn::class.java)
            intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or(Intent.FLAG_ACTIVITY_CLEAR_TASK)
            startActivity(intent)
            finish()
        }else{
            Toast.makeText(this@GetStarted, "User Authenticated", Toast.LENGTH_SHORT).show()
        }
    }
}