package pp.facerecognizer.ui

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import com.androidnetworking.AndroidNetworking
import com.androidnetworking.common.Priority
import com.androidnetworking.error.ANError
import com.androidnetworking.interfaces.JSONObjectRequestListener
import kotlinx.android.synthetic.main.activity_login.*
import org.json.JSONObject
import pp.facerecognizer.R

class LoginActivity : AppCompatActivity() {

    private lateinit var sharedPreference: SharedPreferences

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        sharedPreference = getSharedPreferences("Session", Context.MODE_PRIVATE)

        login.setOnClickListener {
            if(etEmail.text.toString().isNotEmpty() && etPassword.text.toString().isNotEmpty()) {
                AndroidNetworking.post("https://private-df91b8-attendance15.apiary-mock.com/login")
                        .addBodyParameter("username", etPassword.text.toString())
                        .addBodyParameter("password", etPassword.text.toString())
                        .setTag("Login")
                        .setPriority(Priority.HIGH)
                        .build()
                        .getAsJSONObject(object: JSONObjectRequestListener {
                            override fun onResponse(response: JSONObject?) {
                                val status = response?.getJSONObject("meta")?.getString("message")
                                if(status == "Logged In") {
                                    val editor: SharedPreferences.Editor = sharedPreference.edit()
                                    editor.putString("id", response.getJSONObject("data")?.getString("employee_id"))
                                    editor.putString("name", response.getJSONObject("data")?.getString("employee_name"))
                                    editor.putString("position", response.getJSONObject("data")?.getString("position"))
                                    editor.apply()

                                    startActivity(Intent(this@LoginActivity, HomeActivity::class.java))
                                    finish()
                                }
                            }

                            override fun onError(anError: ANError?) {
                                Log.e("Error", anError?.message)
                            }

                        })
            }
        }
    }
}