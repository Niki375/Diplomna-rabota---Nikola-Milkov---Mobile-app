/*package diplomna.savemyfood.service

import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase

class FirestoreServiceImpl : FirestoreService {
    private val db = Firebase.firestore

    override fun addUser(user: User, onComplete: (Exception?) -> Unit) {
        db.collection("users")
            .add(user)
            .addOnSuccessListener { onComplete(null) }
            .addOnFailureListener { e -> onComplete(e) }
    }

    override fun getUser(userId: String, onComplete: (User?, Exception?) -> Unit) {
        db.collection("users")
            .document(userId)
            .get()
            .addOnSuccessListener { document ->
                val user = document.toObject(User::class.java)
                onComplete(user, null)
            }
            .addOnFailureListener { e -> onComplete(null, e) }
    }

    override fun updateUser(userId: String, user: User, onComplete: (Exception?) -> Unit) {
        db.collection("users")
            .document(userId)
            .set(user)
            .addOnSuccessListener { onComplete(null) }
            .addOnFailureListener { e -> onComplete(e) }
    }

    override fun deleteUser(userId: String, onComplete: (Exception?) -> Unit) {
        db.collection("users")
            .document(userId)
            .delete()
            .addOnSuccessListener { onComplete(null) }
            .addOnFailureListener { e -> onComplete(e) }
    }
}
*/