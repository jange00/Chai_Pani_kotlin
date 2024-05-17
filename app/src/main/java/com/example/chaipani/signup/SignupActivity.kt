package com.example.chaipani.signup

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.chaipani.R
import com.example.chaipani.databinding.ActivitySignupBinding
import com.example.chaipani.loginpage.LoginActivity

class SignupActivity : AppCompatActivity() {
    private lateinit var signupBinding: ActivitySignupBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_signup)

        signupBinding = ActivitySignupBinding.inflate(layoutInflater)
        setContentView(signupBinding.root)



        //signup to login
        signupBinding.loginn.setOnClickListener {
            val intent = Intent(this@SignupActivity,LoginActivity::class.java)
            startActivity(intent)
        }

        signupBinding.signupButton.setOnClickListener {
            val intent = Intent(this@SignupActivity, LoginActivity::class.java)
            startActivity(intent)

            
            var firstname = signupBinding.firstname.text.toString()
            var lastname = signupBinding.lastname.text.toString()
            val email = signupBinding.email.text.toString()
            val phonenumber = signupBinding.phonenumber.text.toString()
            val password = signupBinding.password.text.toString()
            val confirmpassword = signupBinding.confirmpassword.text.toString()

            if (firstname.isNotEmpty() && lastname.isNotEmpty() && email.isNotEmpty() && phonenumber.isNotEmpty() && password.isNotEmpty() && confirmpassword.isNotEmpty()) {
                if (password == confirmpassword) {

                }else{
                    Toast.makeText(this, "Password is incorrect", Toast.LENGTH_SHORT).show()
                }
            }else{
                Toast.makeText(this, "Empty fileds are not allowed!!", Toast.LENGTH_SHORT).show()
            }
        }
    }
}