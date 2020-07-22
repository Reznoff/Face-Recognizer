package pp.facerecognizer.model

import com.google.gson.annotations.SerializedName

data class AttendanceModel(@SerializedName("data")
                           val data: Data,
                           @SerializedName("meta")
                           val meta: Meta)