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
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.firestore.FirebaseFirestore

class LoginActivity : AppCompatActivity() {

    private lateinit var loginBinding: ActivityLoginBinding
    private val auth = FirebaseAuth.getInstance()
    private lateinit var firestore: FirebaseFirestore
    lateinit var email: String
    lateinit var phoneNumber: String


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        loginBinding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(loginBinding.root)
        firestore = FirebaseFirestore.getInstance()

        //login to signup
        loginBinding.signnup.setOnClickListener {
            val intent = Intent(this@LoginActivity, SignupActivity::class.java)
            startActivity(intent)
        }

        //login to forget password
        loginBinding.forgetpassword.setOnClickListener {
            val intent = Intent(this@LoginActivity, ForgetPasswordActivity::class.java)
            startActivity(intent)
        }
        loginBinding.loginButton.setOnClickListener {
            loginuser()
        }
    }

    private fun loginuser() {
        val email = loginBinding.email.text.toString().trim()
        val password = loginBinding.password.text.toString().trim()

        if (email.isEmpty() || password.isEmpty()) {
            Toast.makeText(this, "Enter the details", Toast.LENGTH_SHORT).show()
            return
        }
        auth.signInWithEmailAndPassword(email, password)
            .addOnCompleteListener(this) { task ->
                if (task.isSuccessful) {
                    Toast.makeText(this, "Login Successful", Toast.LENGTH_SHORT).show()
                    val intent = Intent(this@LoginActivity, DashBoardActivity::class.java)
                    startActivity(intent)
                    finish();

                } else {

                    Toast.makeText(this, "Authentication failed.", Toast.LENGTH_SHORT).show()
                }

            }
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.signuplogo))
        { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
    }
}