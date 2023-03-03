package diplomna.savemyfood.service

import android.content.ContentValues
import android.util.Log
import com.google.firebase.auth.EmailAuthProvider
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.auth.ktx.auth
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import diplomna.savemyfood.authentication.User
import diplomna.savemyfood.business.Box
import kotlinx.coroutines.tasks.await

class AuthServiceImpl: AuthService {

    private val auth = FirebaseAuth.getInstance()

    override fun createAccount(onResult: (Throwable?) -> Unit) {
        // Not needed for sign up and login without anonymous sign-in
    }

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

    override fun linkAccount(email: String, password: String, onResult: (Throwable?) -> Unit) {
        val currentUser = auth.currentUser
        if (currentUser == null) {
            // If no user is signed in, return an error
            onResult(IllegalStateException("No user is signed in"))
            return
        }

        val credential = EmailAuthProvider.getCredential(email, password)

        currentUser.linkWithCredential(credential)
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


    override suspend fun getBoxData(): List<Box>? {
        val firestore = Firebase.firestore
        val boxCollection = firestore.collection("boxes")
        val query = boxCollection.whereEqualTo("email", Firebase.auth.currentUser?.email)

        return try {
            val result = query.get().await()
            if (result.isEmpty) {
                null
            } else {
                result.documents.mapNotNull { doc ->
                    doc.toObject(Box::class.java)
                }
            }
        } catch (e: Exception) {
            null
        }
    }

     suspend fun getAllBoxData(): List<Box>? {
        val firestore = Firebase.firestore
        val boxCollection = firestore.collection("boxes")
        val query = boxCollection

        return try {
            val result = query.get().await()
            if (result.isEmpty) {
                null
            } else {
                result.documents.mapNotNull { doc ->
                    doc.toObject(Box::class.java)
                }
            }
        } catch (e: Exception) {
            null
        }
    }



}
