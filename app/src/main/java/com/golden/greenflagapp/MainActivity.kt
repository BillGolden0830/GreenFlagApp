package com.golden.greenflagapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button

/*
    Green Flag App by Bill Golden
    10/13-10/17/2022
 */
class MainActivity : AppCompatActivity() {

    private lateinit var btnAccount: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        btnAccount = findViewById(R.id.btnCreate)

        //Move from opening page to account creation page

        btnAccount.setOnClickListener{

            intent = Intent(this, CreateNewAccount::class.java)
            startActivity(intent)

        }
    }
}