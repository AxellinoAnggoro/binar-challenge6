package com.axellinoanggoro.binar_challenge6.view

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Patterns
import android.widget.Toast
import com.axellinoanggoro.binar_challenge6.databinding.ActivityRegisterBinding
import com.google.firebase.auth.FirebaseAuth
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class RegisterActivity : AppCompatActivity() {
    private lateinit var binding: ActivityRegisterBinding
    private lateinit var auth: FirebaseAuth
    private lateinit var pref: SharedPreferences

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityRegisterBinding.inflate(layoutInflater)
        setContentView(binding.root)

        auth = FirebaseAuth.getInstance()
        pref = this.getSharedPreferences("data_reg", Context.MODE_PRIVATE)

        binding.registerLogin.setOnClickListener {
            startActivity(Intent(this, LoginActivity::class.java))
            finish()
        }


        binding.registerBtn.setOnClickListener {
            val email = binding.registerEmail.text.toString()
            val password = binding.registerPassword.text.toString()
            val confPassword = binding.registerConfirm.text.toString()
            val getUsername = binding.registerUsername.text.toString()

            if (!Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
                binding.registerEmail.error = "Invalid Email"
                binding.registerEmail.requestFocus()
                return@setOnClickListener
            }

            if (password.isEmpty()) {
                binding.registerPassword.error = "Password still empty"
                binding.registerPassword.requestFocus()
                return@setOnClickListener
            }

            if (password == confPassword){
                firebaseRegisterAuth(email, password)
                val save = pref.edit()
                save.putString("username", getUsername)
                save.apply()

            }else{
                binding.registerConfirm.error = "Password doesn't match"
                binding.registerConfirm.requestFocus()
            }


        }
    }

    private fun firebaseRegisterAuth(email: String, password: String) {
        auth.createUserWithEmailAndPassword(email, password).addOnCompleteListener(this) {
            if (it.isSuccessful) {
                Toast.makeText(this, "Register Success", Toast.LENGTH_SHORT).show()
                startActivity(Intent(this, LoginActivity::class.java))
                finish()
            } else {
                Toast.makeText(this, "${it.exception?.message}", Toast.LENGTH_SHORT).show()
            }
        }
    }
}