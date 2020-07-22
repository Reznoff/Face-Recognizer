package pp.facerecognizer.model

import com.google.gson.annotations.SerializedName

data class Data(@SerializedName("clock_in_date")
                val clockInDate: String = "",
                @SerializedName("employee_id")
                val employeeId: Int = 0,
                @SerializedName("employee_name")
                val employeeName: String = "",
                @SerializedName("clock_in_time")
                val clockInTime: String = "",
                @SerializedName("id")
                val id: Int = 0,
                @SerializedName("position")
                val position: String = "",
                @SerializedName("status")
                val status: String = "")