package diplomna.savemyfood.service

import com.google.firebase.auth.FirebaseUser

interface AuthService {
    fun createAccount(onResult: (Throwable?) -> Unit)
    fun authenticate(email: String, password: String, onResult: (Throwable?) -> Unit)
    fun linkAccount(email: String, password: String, onResult: (Throwable?) -> Unit)
    fun signup(email: String, password: String, isBusiness: Boolean, onResult: (FirebaseUser?, Throwable?) -> Unit)
}