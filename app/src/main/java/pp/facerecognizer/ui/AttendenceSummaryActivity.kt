package pp.facerecognizer.ui

import android.content.Context
import android.content.SharedPreferences
import android.os.Bundle
import android.util.Log
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import com.androidnetworking.AndroidNetworking
import com.androidnetworking.common.Priority
import com.androidnetworking.error.ANError
import com.androidnetworking.interfaces.JSONObjectRequestListener
import kotlinx.android.synthetic.main.activity_attendence_summary.*
import org.json.JSONObject
import pp.facerecognizer.R

class AttendenceSummaryActivity : AppCompatActivity() {

    private lateinit var sharedPreference: SharedPreferences

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_attendence_summary)

        sharedPreference = getSharedPreferences("Session", Context.MODE_PRIVATE)

        setSupportActionBar(toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        AndroidNetworking.post("https://private-df91b8-attendance15.apiary-mock.com/attendance/clock-in")
                .setTag("Attendance")
                .setPriority(Priority.HIGH)
                .build()
                .getAsJSONObject(object: JSONObjectRequestListener {
                    override fun onResponse(response: JSONObject?) {
                        Log.i("Response : ", response.toString())
                        txtName.text = response?.getJSONObject("data")?.getString("employee_name")
                        txtPosition.text = response?.getJSONObject("data")?.getString("position")
                        txtAttendanceTime.text = response?.getJSONObject("data")?.getString("clock_in_time")
                        txtAttendanceDate.text = response?.getJSONObject("data")?.getString("clock_in_date")
                        txtStatus.text = response?.getJSONObject("data")?.getString("status")
                        val editor: SharedPreferences.Editor = sharedPreference.edit()
                        editor.putBoolean("clocked", true)
                        editor.apply()
                    }

                    override fun onError(anError: ANError?) {
                        Log.e("Error", anError?.message)
                    }
                })
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        return when(item?.itemId) {
            android.R.id.home -> {
                finish()
                true
            }
            else -> super.onOptionsItemSelected(item)
        }

    }
}