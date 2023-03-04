package diplomna.savemyfood.authentication

import androidx.lifecycle.ViewModel
import com.google.firebase.auth.FirebaseAuth

class ForgotPasswordViewModel : ViewModel() {
    val firebaseAuth = FirebaseAuth.getInstance()

    fun resetPassword(email: String) {
        firebaseAuth.sendPasswordResetEmail(email)
    }
}