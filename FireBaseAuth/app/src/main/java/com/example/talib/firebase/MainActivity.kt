package com.example.talib.firebase

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    lateinit var fireBaseAuth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        fireBaseAuth = FirebaseAuth.getInstance()

        fireBaseAuth.signOut()

        if(fireBaseAuth.currentUser != null){
            startActivity(Intent(this,HomeActivity::class.java))
            finish()
        }

        login.setOnClickListener{
            var email=text1.text.toString()
            var password=text2.text.toString()
            signUp(email,password)
        }

    }
    fun signUp(email:String,password:String)
    {
        fireBaseAuth.createUserWithEmailAndPassword(email,password).addOnCompleteListener {
            if(it.isSuccessful){
                Toast.makeText(this,"Signed UP!",Toast.LENGTH_SHORT).show()
            }else{
                Toast.makeText(this,"Error! : ${it.exception?.message}",Toast.LENGTH_SHORT).show()
            }
        }
    }
}