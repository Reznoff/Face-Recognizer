package pp.facerecognizer.ui

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import android.os.Handler
import androidx.appcompat.app.AppCompatActivity
import pp.facerecognizer.R

class SplashActivity : AppCompatActivity() {

    private lateinit var sharedPreference: SharedPreferences

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)

        sharedPreference = getSharedPreferences("Session", Context.MODE_PRIVATE)

        Handler().postDelayed({
            val id = sharedPreference.getString("id", null)
            if(id != null) {
                startActivity(Intent(this@SplashActivity, HomeActivity::class.java))
                finish()
            } else {
                startActivity(Intent(this@SplashActivity, LoginActivity::class.java))
                finish()
            }
        }, 1000)
    }
}