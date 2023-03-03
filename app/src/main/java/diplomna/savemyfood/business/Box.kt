package diplomna.savemyfood.business

import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase

val db = Firebase.firestore

data class Box(
    val food_type: String = "",
    val description: String = "",
    val pickup_time: String = "",
    val price_per_box: Float = 0.0f,
    val isBought: Boolean = false,
    val id: String = "",
    val email: String = ""
)
