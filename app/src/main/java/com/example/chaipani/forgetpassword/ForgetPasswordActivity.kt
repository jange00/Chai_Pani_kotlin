package com.example.chaipani.forgetpassword

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.chaipani.R
import com.example.chaipani.databinding.ActivityForgetPasswordBinding
import com.example.chaipani.loginpage.LoginActivity

class ForgetPasswordActivity : AppCompatActivity() {
    private lateinit var forgetPasswordBinding: ActivityForgetPasswordBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_forget_password)

        forgetPasswordBinding = ActivityForgetPasswordBinding.inflate(layoutInflater)
        setContentView(forgetPasswordBinding.root)

        forgetPasswordBinding.update.setOnClickListener {
            val intent = Intent(this@ForgetPasswordActivity, LoginActivity::class.java)
            startActivity(intent)

            val email = forgetPasswordBinding.email.text.toString()
            val Newpassword = forgetPasswordBinding.Newpassword.text.toString()
            val confirmm_password = forgetPasswordBinding.confirmmPassword.text.toString()

            if (email.isNotEmpty() && Newpassword.isNotEmpty() && confirmm_password.isNotEmpty())
                if (Newpassword == confirmm_password) {
                } else {
                    Toast.makeText(this, "Incoorect Password", Toast.LENGTH_SHORT).show()
                }
            else {
                Toast.makeText(this, "Empty fileds are not allowed!", Toast.LENGTH_SHORT).show()
            }
        }

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
    }
}