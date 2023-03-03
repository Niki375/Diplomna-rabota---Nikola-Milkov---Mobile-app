package diplomna.savemyfood.authentication

import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase

val db = Firebase.firestore

data class User(
    val email: String = "",
    val username: String = "",
    val address: String = "",
    val business: Boolean = false,
    val money: Float = 0.0f
)

