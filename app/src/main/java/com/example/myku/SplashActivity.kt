package com.example.myku

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import androidx.appcompat.app.AppCompatActivity

class SplashActivity : AppCompatActivity() {
    private val SPLASH_TIME_OUT:Long = 2000
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)

        var a = 2

        Handler().postDelayed({

            if(a==1){
                startActivity(Intent(this, MainActivity::class.java))
            }else if(a==2){
                startActivity(Intent(this, LoginActivity::class.java))
            }

            finish()
        }, SPLASH_TIME_OUT)
    }
}