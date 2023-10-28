package com.chobo.projectlogin

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button

class LoginActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        val btn_login_register = findViewById<Button>(R.id.btn_login_register)

        btn_login_register.setOnClickListener{
            val goLoginToRegisterIntent = Intent(this, RegisterActivity::class.java)
            startActivity(goLoginToRegisterIntent)
        }
    }
}