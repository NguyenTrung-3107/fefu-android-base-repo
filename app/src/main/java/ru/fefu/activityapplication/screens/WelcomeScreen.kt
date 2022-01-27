package ru.fefu.activityapplication.screens

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import ru.fefu.activityapplication.R

class WelcomeScreen : AppCompatActivity(R.layout.welcome_screen_activity) {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val button1 = findViewById<Button>(R.id.btn_1)
        val button2 = findViewById<Button>(R.id.btn_2)

        button1.setOnClickListener {
            val intent = Intent(this, RegisterScreen::class.java)
            startActivity(intent)
        }

        button2.setOnClickListener {
            val intent = Intent(this, LoginScreen::class.java)
            startActivity(intent)
        }

    }

}