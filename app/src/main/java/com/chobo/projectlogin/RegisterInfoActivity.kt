package com.chobo.projectlogin

import android.content.Context
import android.content.Intent
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Environment
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.annotation.RequiresApi
import org.json.JSONObject
import java.io.BufferedWriter
import java.io.File
import java.io.FileWriter
import java.io.IOException
import java.io.OutputStreamWriter
import java.util.Base64
import javax.crypto.Mac
import javax.crypto.spec.SecretKeySpec


class RegisterInfoActivity : AppCompatActivity() {
    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register_info)

        val btn_registerInfo_join = findViewById<Button>(R.id.btn_registerInfo_join)
        val et_registerInfo_name = findViewById<EditText>(R.id.et_registerInfo_name)

        val email = intent.getStringExtra("register_email")
        val password = intent.getStringExtra("register_password")

        var name : String
        var age : String
        try{
            btn_registerInfo_join.setOnClickListener {
                if(!et_registerInfo_name.text.toString().trim().isEmpty())
                {
                    name = et_registerInfo_name.text.toString().trim()

                    val jsonData =JSONObject()

                    val header = JSONObject()
                    header.put("alg", "HS256")
                    header.put("typ", "JWT")
                    jsonData.put("header", header)

                    val payload = JSONObject()
                    payload.put("email", email)
                    payload.put("password", password)
                    payload.put("name", name)
                    jsonData.put("payload", payload)

                    val secretKey = "testSecretkey1234"
                    val hmac = Mac.getInstance("HmacSHA256")
                    val key = SecretKeySpec(secretKey.toByteArray(), "HmacSHA256")
                    hmac.init(key)
                    val signatureByte = hmac.doFinal(jsonData.toString().toByteArray())
                    val signature = Base64.getEncoder().encodeToString(signatureByte)
                    val verifySignature = JSONObject()
                    verifySignature.put("signature", signature)
                    jsonData.put("verifySignature", verifySignature)


                    var jsonString = jsonData.toString()

                    val fileName = "registerInfo.json"
                    val fileOutputStream = openFileOutput(fileName, Context.MODE_PRIVATE)
                    fileOutputStream.write(jsonString.toByteArray())
                    fileOutputStream.close()



                    val intent: Intent = Intent(this, LoginActivity::class.java)
                    startActivity(intent)
                }
                else
                {
                    Toast.makeText(getApplicationContext(), "빈칸이 있습니다.", Toast.LENGTH_SHORT).show()
                }
            }
        }
        catch(e : IOException){

        }
    }
}