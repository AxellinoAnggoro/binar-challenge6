package com.axellinoanggoro.binar_challenge6

import android.annotation.SuppressLint
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
//import android.view.ViewGroup
//import android.widget.Button

import com.axellinoanggoro.binar_challenge6.databinding.ActivityMainBinding
import com.axellinoanggoro.binar_challenge6.view.RegisterActivity
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding

    @SuppressLint("SetTextI18n")
    override fun onCreate(savedInstanceState: Bundle?) {
        binding = ActivityMainBinding.inflate(layoutInflater)
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        supportActionBar?.hide()
        Handler().postDelayed({
            val intent = Intent(this@MainActivity,RegisterActivity::class.java)
            startActivity(intent)
            finish()
        },3000)

//        val crashButton = Button(this)
//        crashButton.text = "Test Crash"
//        crashButton.setOnClickListener {
//            throw RuntimeException("Test Crash") // Force a crash }
//
//            addContentView(
//                crashButton, ViewGroup.LayoutParams(
//                    ViewGroup.LayoutParams.MATCH_PARENT,
//                    ViewGroup.LayoutParams.WRAP_CONTENT
//                )
//            )
//        }
    }
}