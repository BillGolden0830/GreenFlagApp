package com.golden.greenflagapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import android.widget.Toast.LENGTH_LONG
import android.widget.Toast.LENGTH_SHORT
import androidx.core.view.isVisible
import androidx.core.widget.doAfterTextChanged
/*
    Green Flag App by Bill Golden
    10/13 - 10/17/2022
 */


class CreateNewAccount : AppCompatActivity() {

    //lateinit components that are used in app

    //One ed displays plain text; the password boxes obscure the password entry
    private lateinit var edEmail: EditText
    private lateinit var edPassword: EditText
    private lateinit var edConfirm: EditText

    //these labels appear and disappear based on ed entries
    private lateinit var txtEmail: TextView
    private lateinit var txtPassword: TextView
    private lateinit var txtConfirm: TextView

    //Button to create account
    private lateinit var btnConfirm: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_create_new_account)

        edEmail = findViewById(R.id.edAddress)
        edPassword = findViewById(R.id.etPassword)
        edConfirm = findViewById(R.id.etConfirm)

        btnConfirm = findViewById(R.id.btnConf)

        txtEmail = findViewById(R.id.tvEmailInvalid)
        txtPassword = findViewById(R.id.tvPass)
        txtConfirm = findViewById(R.id.tvNoMatch)

        btnConfirm.setOnClickListener {
            Toast.makeText(this, "Account creation successful.", LENGTH_LONG).show()
        }

        edEmail.addTextChangedListener(object : TextWatcher {

            override fun afterTextChanged(s: Editable) {}

            override fun beforeTextChanged(
                s: CharSequence, start: Int,
                count: Int, after: Int
            ) {
            }

            override fun onTextChanged(
                s: CharSequence, start: Int,
                before: Int, count: Int
            ) {
                if (verifyEmailAddress(edEmail.text.toString())) {
                    txtEmail.visibility = View.GONE
                } else {
                    txtEmail.visibility = View.VISIBLE
                }

                btnConfirm.isEnabled =
                    enableConfirm(verifyEmailAddress(edEmail.text.toString()), validatePassword(edPassword.text.toString()),
                        verifyMatch(edPassword.text.toString(), edConfirm.text.toString()))

            }
        })

        edPassword.addTextChangedListener(object : TextWatcher {

            override fun afterTextChanged(s: Editable) {}

            override fun beforeTextChanged(
                s: CharSequence, start: Int,
                count: Int, after: Int
            ) {
            }

            override fun onTextChanged(
                s: CharSequence, start: Int,
                before: Int, count: Int
            ) {
                if (validatePassword(edPassword.text.toString())) {
                    txtPassword.visibility = View.GONE
                } else {
                    txtPassword.visibility = View.VISIBLE
                }
                btnConfirm.isEnabled =
                    enableConfirm(
                        verifyEmailAddress(edEmail.text.toString()),
                        validatePassword(edPassword.text.toString()),
                        verifyMatch(edPassword.text.toString(), edConfirm.text.toString()),
                    )
            }
        })

        edConfirm.addTextChangedListener(object : TextWatcher {

            override fun afterTextChanged(s: Editable) {}

            override fun beforeTextChanged(
                s: CharSequence, start: Int,
                count: Int, after: Int
            ) {
            }

            override fun onTextChanged(
                s: CharSequence, start: Int,
                before: Int, count: Int
            ) {
                if (verifyMatch(
                        edConfirm.text.toString(),
                        edPassword.text.toString()
                    ) && validatePassword(edConfirm.text.toString())
                ) {
                    txtConfirm.visibility = View.GONE
                } else {
                    txtConfirm.visibility = View.VISIBLE
                }
                btnConfirm.isEnabled =
                    enableConfirm(
                        verifyEmailAddress(edEmail.text.toString()),
                        validatePassword(edPassword.text.toString()),
                        verifyMatch(edPassword.text.toString(), edConfirm.text.toString()),
                    )
            }

        })
    }

    private fun enableConfirm(verifyEmailAddress: Boolean, validatePassword: Boolean, verifyMatch: Boolean): Boolean {
        return(verifyEmailAddress && validatePassword && verifyMatch)

    }

    private fun verifyMatch(pwOne: String?, pwTwo: String?): Boolean {
        if(pwOne.isNullOrEmpty()||pwTwo.isNullOrEmpty())return false

        return (pwOne == pwTwo)
    }


    private fun validatePassword(pwEntry: String?): Boolean {

        if (pwEntry.isNullOrEmpty())return false

        val pwPattern = "^(?=.*[A-Za-z])(?=.*\\d)(?=.*[@\$!%*#?&])[A-Za-z\\d@\$!%*#?&]{8,}\$"

        return pwEntry.matches((pwPattern.toRegex()))

    }

    private fun verifyEmailAddress(email: String?): Boolean {

        if(email.isNullOrEmpty())return false

        val emailPattern = "[a-zA-Z0-9._-]+@[a-z]+.+[a-z]+"

        return email.matches(emailPattern.toRegex())

    }
}