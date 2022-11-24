  package com.example.healthypalate.ui

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import com.example.healthypalate.R
import com.example.healthypalate.databinding.ActivityRegisterBinding
import com.example.healthypalate.ui.models.User
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.FirebaseDatabase

  class Register : AppCompatActivity() {

    val binding : ActivityRegisterBinding by lazy{
        ActivityRegisterBinding.inflate(layoutInflater)
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        var regEmail = binding.regEmail.text.toString()
        var regPassword = binding.regPassword.text.toString()
        var confirmPassword = binding.confirmPassword.text.toString()

        binding.register.setOnClickListener {
            if (binding.regEmail.text.toString().isNotBlank() &&
                binding.regPassword.text.toString().isNotBlank() &&
                binding.confirmPassword.text.toString().isNotBlank()){
                if (binding.regPassword.text.toString() == binding.confirmPassword.text.toString()){
                    registerNewUserEmail(binding.regEmail.text.toString(), binding.regPassword.text.toString())
                }else{
                    Toast.makeText(this, "Passwords do not match!!", Toast.LENGTH_LONG).show()
                }
            }else{
                Toast.makeText(this, "Enter all fields", Toast.LENGTH_LONG).show()
            }
        }
    }

      private fun registerNewUserEmail(email: String, password: String){

          FirebaseAuth.getInstance().createUserWithEmailAndPassword(email, password)
              .addOnCompleteListener {
                  if (it.isSuccessful){
                      Toast.makeText(this@Register,
                          "Registration successful: ${FirebaseAuth.getInstance().currentUser?.uid}",
                          Toast.LENGTH_SHORT).show()
                      sendVerificationEmail()
                      FirebaseAuth.getInstance().signOut()

                      var user = User()
                      FirebaseAuth.getInstance().currentUser?.let { it1 ->
                          FirebaseDatabase.getInstance().getReference("users")
                              .child(it1.uid)
                      }?.setValue(user)
                      redirectToLogIn()
                  }else{
                      Toast.makeText(this@Register,
                          "Sorry!! Registration unsuccessful",
                          Toast.LENGTH_SHORT).show()
                  }
              }
      }

      private fun redirectToLogIn() {
          val intent = Intent(this@Register, LogIn::class.java)
          startActivity(intent)
      }

      private fun sendVerificationEmail(){
          val user = FirebaseAuth.getInstance().currentUser

          user?.sendEmailVerification()?.addOnCompleteListener {

              if (it.isSuccessful){
                  Toast.makeText(this@Register, "Sent verification email", Toast.LENGTH_SHORT).show()
              }else{
                  Toast.makeText(this@Register, "Could not send verification email", Toast.LENGTH_SHORT).show()
              }
          }
      }
}