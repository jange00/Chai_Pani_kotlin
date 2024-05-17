package com.example.chaipani.loginpage

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.chaipani.R
import com.example.chaipani.dashboard.DashBoardActivity
import com.example.chaipani.databinding.ActivityLoginBinding
import com.example.chaipani.forgetpassword.ForgetPasswordActivity
import com.example.chaipani.signup.SignupActivity

class LoginActivity : AppCompatActivity() {

    private lateinit var loginBinding: ActivityLoginBinding


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)


        loginBinding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(loginBinding.root)

        //login to signup
        loginBinding.signnup.setOnClickListener {
            val intent = Intent(this@LoginActivity,SignupActivity::class.java)
            startActivity(intent)
        }

        //login to forget password
        loginBinding.forgetpassword.setOnClickListener {
            val intent = Intent(this@LoginActivity,ForgetPasswordActivity::class.java)
            startActivity(intent)
        }

        //login to dashboard

        loginBinding.loginButton.setOnClickListener {
            val intent = Intent(this@LoginActivity,DashBoardActivity::class.java)
            startActivity(intent)
        }


        loginBinding.loginButton.setOnClickListener {
            val intent = Intent(this@LoginActivity, DashBoardActivity::class.java)
            startActivity(intent)
//            if (loginBinding.email.text.toString() == "user" && loginBinding.password.text.toString() == "1234"){
//                Toast.makeText(this,"Login Successful!",Toast.LENGTH_SHORT).show()
//            }
//
//            else{
//                Toast.makeText(this,"Login Failed!",Toast.LENGTH_SHORT).show()
//            }

        }




        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.signuplogo)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
    }
}