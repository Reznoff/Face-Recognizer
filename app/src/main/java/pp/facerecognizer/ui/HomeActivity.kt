package pp.facerecognizer.ui

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_home.*
import pp.facerecognizer.MainActivity
import pp.facerecognizer.R

class HomeActivity : AppCompatActivity() {

    private lateinit var sharedPreference: SharedPreferences

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)

        sharedPreference = getSharedPreferences("Session", Context.MODE_PRIVATE)

        if (!sharedPreference.getBoolean("clocked", false)) {
            clockin.visibility = View.VISIBLE
            clockout.visibility = View.GONE
        } else {
            clockin.visibility = View.GONE
            clockout.visibility = View.VISIBLE
        }

        clockout.setOnClickListener {
            startActivity(Intent(this@HomeActivity, MainActivity::class.java))
        }

        clockin.setOnClickListener {
            startActivity(Intent(this@HomeActivity, MainActivity::class.java))
        }

        logout.setOnClickListener {
            val dialog = AlertDialog.Builder(this)
                    .setTitle("Konfirmasi")
                    .setMessage("Apakah anda yakin akan logout ?")
                    .setPositiveButton("Yes") { _, _ ->
                        sharedPreference.edit().clear().apply()
                        startActivity(Intent(this@HomeActivity, LoginActivity::class.java))
                        finish()
                    }
                    .setNegativeButton("No", null)
            dialog.show()

        }
    }

    override fun onResume() {
        super.onResume()
        if (!sharedPreference.getBoolean("clocked", false)) {
            clockin.visibility = View.VISIBLE
            clockout.visibility = View.GONE
        } else {
            clockin.visibility = View.GONE
            clockout.visibility = View.VISIBLE
        }
    }
}