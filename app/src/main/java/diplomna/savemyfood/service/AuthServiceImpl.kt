package diplomna.savemyfood.service

import com.google.firebase.auth.EmailAuthProvider
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser


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

    override fun signup(email: String, password: String, onResult: (FirebaseUser?, Throwable?) -> Unit) {
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
}
