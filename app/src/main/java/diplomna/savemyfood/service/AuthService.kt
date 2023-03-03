package diplomna.savemyfood.service

import com.google.firebase.auth.FirebaseUser
import diplomna.savemyfood.authentication.User
import diplomna.savemyfood.business.Box

interface AuthService {
    fun createAccount(onResult: (Throwable?) -> Unit)
    fun authenticate(email: String, password: String, onResult: (Throwable?) -> Unit)
    fun linkAccount(email: String, password: String, onResult: (Throwable?) -> Unit)
    fun signup(email: String, password: String, isBusiness: Boolean, onResult: (FirebaseUser?, Throwable?) -> Unit)
    suspend fun getUserData(): User?
    //suspend fun getBoxData(): List<Box>?
}