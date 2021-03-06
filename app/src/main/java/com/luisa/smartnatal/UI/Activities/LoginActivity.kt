package com.luisa.smartnatal.UI.Activities

import android.app.ProgressDialog
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.TextUtils
import android.util.Log
import android.widget.*
import com.google.android.gms.tasks.OnCompleteListener
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.luisa.smartnatal.R

class LoginActivity : AppCompatActivity() {
    private lateinit var auth: FirebaseAuth

    private lateinit var emailEt: EditText
    private lateinit var passwordEt: EditText

    private lateinit var signupBtn: TextView
    private lateinit var loginBtn: Button

    private lateinit var resetPasswordTv: TextView
    private lateinit var progressDialog: ProgressDialog

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        emailEt = findViewById(R.id.email_edt_text)
        passwordEt = findViewById(R.id.pass_edt_text)


        signupBtn = findViewById(R.id.signup_btn)
        loginBtn = findViewById(R.id.login_btn)

        resetPasswordTv = findViewById(R.id.reset_pass_tv)

        auth = FirebaseAuth.getInstance()
        progressDialog = ProgressDialog(this)
        progressDialog.setProgressStyle(ProgressDialog.STYLE_SPINNER)



    loginBtn.setOnClickListener {
        var email: String = emailEt.text.toString()
        var password: String = passwordEt.text.toString()

        progressDialog.setMessage("Logging Please Wait")
        progressDialog.show();

        if(TextUtils.isEmpty(email) || TextUtils.isEmpty(password)) {
            Toast.makeText(this@LoginActivity, "Please fill all the fields", Toast.LENGTH_LONG).show()
        } else{
                    loginuser(email,password)
        }
    }

    signupBtn.setOnClickListener{
        val intent = Intent(this, SignUpActivity::class.java)
        startActivity(intent)
        finish()
    }

    resetPasswordTv.setOnClickListener{
       /* val intent = Intent(this, ForgotPasswordActivity::class.java)
        startActivity(intent)*/
        Toast.makeText(applicationContext,"Working in Progress",Toast.LENGTH_LONG).show()

    }
}

    private fun loginuser(email:String, password:String) {
        progressDialog.show()
        auth.signInWithEmailAndPassword(email, password).addOnCompleteListener(this, OnCompleteListener { task ->
            if(task.isSuccessful) {
                Toast.makeText(this, "Successfully Logged In", Toast.LENGTH_LONG).show()
                val user = FirebaseAuth.getInstance().currentUser
                user?.let {
                    val email = user.email
                    val uid = user.uid

                    Log.e("users info", user.email+ user.uid)

                }

                val intent = Intent(this, MainActivity::class.java)
                startActivity(intent)
                finish()
            }else {
                Toast.makeText(this, "Login Failed", Toast.LENGTH_LONG).show()
                progressDialog.dismiss()

            }
        })    }


}
