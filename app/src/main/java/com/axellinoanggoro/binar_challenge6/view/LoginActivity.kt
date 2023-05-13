package com.axellinoanggoro.binar_challenge6.view

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Patterns
import android.widget.Toast
import com.axellinoanggoro.binar_challenge6.databinding.ActivityLoginBinding
import com.google.firebase.auth.FirebaseAuth
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class LoginActivity : AppCompatActivity() {
    private lateinit var binding : ActivityLoginBinding
    private lateinit var auth : FirebaseAuth
    private lateinit var pref: SharedPreferences


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)

        pref = this.getSharedPreferences("data_reg", Context.MODE_PRIVATE)


        binding.loginRegister.setOnClickListener {
            startActivity(Intent(this, RegisterActivity::class.java))
            finish()
        }

        auth = FirebaseAuth.getInstance()
        binding.loginBtn.setOnClickListener {
            val email = binding.loginEmail.text.toString()
            val password = binding.loginPassword.text.toString()

            if (!Patterns.EMAIL_ADDRESS.matcher(email).matches()){
                binding.loginEmail.error = "Invalid Email"
                binding.loginEmail.requestFocus()
                return@setOnClickListener
            }

            if (password.isEmpty()){
                binding.loginPassword.error = "Password still empty"
                binding.loginPassword.requestFocus()
                return@setOnClickListener
            }
            firebaseAuthLogin(email, password)
            startActivity(Intent(this,HomeActivity::class.java))
            finish()
        }
    }

    private fun firebaseAuthLogin(email: String, password: String) {
        auth.signInWithEmailAndPassword(email,password).addOnCompleteListener {
            if (it.isSuccessful){
                Toast.makeText(this, "Login Success", Toast.LENGTH_SHORT).show()
            }else{
                Toast.makeText(this, "Login Fail", Toast.LENGTH_SHORT).show()
            }
        }
    }
}