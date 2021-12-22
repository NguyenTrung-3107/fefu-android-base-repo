package ru.fefu.activityapplication.screens

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import ru.fefu.activityapplication.R

class LoginScreen : AppCompatActivity(R.layout.login_screen_activity) {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val toolbar = findViewById<androidx.appcompat.widget.Toolbar>(R.id.toolbar_login)

        toolbar.setNavigationOnClickListener {
            val intent = Intent(this, WelcomeScreen::class.java)
            finish()
        }

    }
}