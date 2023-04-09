package com.example.myfirstapplication

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import com.example.myfirstapplication.util.SharedPrefereces

class SplashActivity : AppCompatActivity() {
    lateinit var pre: SharedPrefereces
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)

        pre = SharedPrefereces(this)

        Handler().postDelayed({
            var i = Intent()

            if (pre.firstInstall == false) {
                i = Intent(this, WalkthroughActivity::class.java)
            } else {
                i = Intent(this, MainActivity::class.java)
            }

            startActivity(i)
            finish()

        },3000)
    }
}