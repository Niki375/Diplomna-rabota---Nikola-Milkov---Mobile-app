package diplomna.savemyfood.service

import android.content.ContentValues
import android.util.Log
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.auth.ktx.auth
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import diplomna.savemyfood.authentication.User
import kotlinx.coroutines.tasks.await

class AuthServiceImpl: AuthService {

    private val auth = FirebaseAuth.getInstance()

    override fun authenticate(email: String, password: String, onResult: (Throwable?) -> Unit) {
        auth.signInWithEmailAndPassword(email, password)
            .addOnCompleteListener { task ->
                if (task.isSuccessful) {
                    onResult(null)
                } else {
                    onResult(task.exception)
                }
            }
    }

    override fun signup(email: String, password: String, isBusiness: Boolean, onResult: (FirebaseUser?, Throwable?) -> Unit) {
        auth.createUserWithEmailAndPassword(email, password)
            .addOnCompleteListener { task ->
                if (task.isSuccessful) {
                    val user = task.result?.user
                    onResult(user, null)
                } else {
                    onResult(null, task.exception)
                }
            }
    }

    override suspend fun getUserData(): User? {
        val currentUser = Firebase.auth.currentUser
        return currentUser?.let {
            val firestore = Firebase.firestore
            val usersCollection = firestore.collection("users")
            val query = usersCollection.whereEqualTo("email", currentUser.email)
            Log.d(ContentValues.TAG, "email: ${currentUser.email}")
            return@let try {
                val userData = query.get().await().documents[0].toObject(User::class.java)
                userData
            } catch (e: Exception) {
                null
            }
        }
    }

    fun logout() {
        auth.signOut()
    }

    fun deleteAccount() {
        auth.currentUser?.delete()
    }

}
