package ru.fefu.activityapplication

import android.content.Intent
import android.os.Bundle
import android.widget.*
import androidx.appcompat.app.AppCompatActivity

class RegisterScreen : AppCompatActivity(R.layout.register_screen_activity){

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val toolBar = findViewById<androidx.appcompat.widget.Toolbar>(R.id.toolbar_register)
        val button = findViewById<Button>(R.id.regis_btn)

        toolBar.setNavigationOnClickListener {
            val intent = Intent(this, WelcomeScreen::class.java)
            finish()
        }

        button.setOnClickListener {
            val intent = Intent(this, WelcomeScreen::class.java)
            startActivity(intent)
        }
    }

}