package diplomna.savemyfood.authentication

import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase

val db = Firebase.firestore

data class User(
    val email: String = "",
    val username: String = "",
    val address: String = "",
    val isBusiness: Boolean = false
)

