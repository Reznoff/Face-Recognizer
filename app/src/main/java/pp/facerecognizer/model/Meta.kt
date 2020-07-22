package pp.facerecognizer.model

import com.google.gson.annotations.SerializedName

data class Meta(@SerializedName("message")
                val message: String = "",
                @SerializedName("status")
                val status: Int = 0)