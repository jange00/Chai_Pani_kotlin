package com.example.chaipani.signup

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.chaipani.R
import com.example.chaipani.dashboard.DashBoardActivity
import com.example.chaipani.databinding.ActivitySignupBinding
import com.example.chaipani.loginpage.LoginActivity
import com.google.firebase.Firebase
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore

class SignupActivity : AppCompatActivity() {
    private lateinit var signupBinding: ActivitySignupBinding
    private lateinit var firestore: FirebaseFirestore
    private var auth = FirebaseAuth.getInstance()
    lateinit var firstname: String
    lateinit var lastname: String
    lateinit var email: String
    lateinit var phoneNumber: String
    lateinit var password: String
    lateinit var confirmPassword :String


    //    public override fun onStart() {
//        super.onStart()
//        val currentUser = auth.currentUser
//        if (currentUser != null) {
//            val intent = Intent(this@SignupActivity, LoginActivity::class.java)
//            startActivity(intent)
//            finish()
//        }
//    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        signupBinding = ActivitySignupBinding.inflate(layoutInflater)
        setContentView(signupBinding.root)
        firestore = FirebaseFirestore.getInstance()

        signupBinding.loginn.setOnClickListener {
            val intent = Intent(this@SignupActivity, LoginActivity::class.java)
            startActivity(intent)
        }
        signupBinding.signupButton.setOnClickListener {
            signupUser()
        }
    }

    private fun signupUser() {
        val firstname = signupBinding.firstname.text.toString().trim()
        val lastname = signupBinding.lastname.text.toString().trim()
        val email = signupBinding.email.text.toString().trim()
        val password = signupBinding.password.text.toString().trim()
        val confirmPassword = signupBinding.confirmPassword.text.toString().trim()
        val phoneNumber = signupBinding.phoneNumber.text.toString().trim()

        if (firstname.isEmpty() || lastname.isEmpty() || email.isEmpty() || password.isEmpty() || confirmPassword.isEmpty()|| phoneNumber.isEmpty()) {
            Toast.makeText(this, "Please fill in all fields", Toast.LENGTH_SHORT).show()
            return
        }

        auth.createUserWithEmailAndPassword(email, password).addOnCompleteListener { task ->
            if (task.isSuccessful) {
                Toast.makeText(this, "Account created", Toast.LENGTH_SHORT).show()
                createSchema(firstname, lastname, email, phoneNumber, password)
            } else {
                Toast.makeText(
                    this,
                    "Account not created: ${task.exception?.message}",
                    Toast.LENGTH_SHORT
                ).show()
            }
        }
    }

    //mvvm

    private fun createSchema(
        firstname: String,
        lastname: String,
        email: String,
        phoneNumber: String,
        password: String
    ) {
        val userDocument = firestore.collection("users_collection").document(email)
        val batch = firestore.batch()

        val userData = mapOf(
            "first_name" to firstname,
            "last_name" to lastname,
            "email" to email,
            "phone_number" to phoneNumber,
            "password" to password
        )

        batch.set(userDocument, userData)

        batch.commit().addOnCompleteListener { task ->
            try {
                if (task.isSuccessful) {

                    Log.d("message", "user created")
//                    Toast.makeText(this, "User schema created", Toast.LENGTH_SHORT).show()
//                val intent = Intent(this@SignupActivity, LoginActivity::class.java)
//                startActivity(intent)
                    finish()
                } else {

                    Log.d("message", "erorr user created")
                    Toast.makeText(
                        this,
                        "Error creating user schema: ${task.exception?.message}",
                        Toast.LENGTH_SHORT
                    ).show()
                }
            } catch (e: Exception) {
                throw e
            }
        }
    }
}