package com.chobo.projectlogin

import android.annotation.SuppressLint
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast

class RegisterActivity : AppCompatActivity() {
    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register)

        val btn_register_next = findViewById<Button>(R.id.btn_register_next)
        val et_register_email = findViewById<EditText>(R.id.et_register_email)
        val et_register_password = findViewById<EditText>(R.id.et_register_password)
        val et_register_password_check = findViewById<EditText>(R.id.et_register_password_check)

        var email : String
        var password : String

        btn_register_next.setOnClickListener {

            if(!et_register_email.text.toString().trim().isEmpty() &&
                !et_register_password.text.toString().trim().isEmpty() &&
                et_register_password_check.text.toString() == et_register_password.text.toString())
            {
                email = et_register_email.text.toString()
                password = et_register_password.text.toString()

                val goRegisterToRegisterInfoActivity =
                    Intent(this, RegisterInfoActivity::class.java)
                goRegisterToRegisterInfoActivity.putExtra("register_email", email)
                goRegisterToRegisterInfoActivity.putExtra("register_password", password)

                startActivity(goRegisterToRegisterInfoActivity)
            }
            else {
                Toast.makeText(getApplicationContext(), "빈칸이 있습니다.", Toast.LENGTH_SHORT).show()
            }
        }
    }
}