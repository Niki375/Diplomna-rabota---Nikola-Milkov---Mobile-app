package diplomna.savemyfood.customer

import android.content.ContentValues.TAG
import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.google.firebase.auth.ktx.auth
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import diplomna.savemyfood.authentication.User
import diplomna.savemyfood.service.AuthServiceImpl
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch

class CustomerProfileViewModel : ViewModel() {
    private val authService = AuthServiceImpl()

    val user = MutableStateFlow<User?>(null)

    fun getUser() {
        viewModelScope.launch {
            user.value = authService.getUserData()
        }
    }

    fun addMoney(money: Float) {
        viewModelScope.launch {
            val user = authService.getUserData()
            val newMoney = user?.money!! + money
            val firestore = Firebase.firestore
            firestore.collection("users")
                .whereEqualTo("email", user.email)
                .get()
                .addOnSuccessListener { querySnapshot ->
                    for (document in querySnapshot.documents) {
                        firestore.collection("users").document(document.id)
                            .update("money", newMoney)
                    }
                }
        }
    }

    fun logOut() {
        authService.logout()
    }

    fun deleteAccount() {
        viewModelScope.launch {
            authService.deleteAccount()
            val firestore = Firebase.firestore
            val user = authService.getUserData()
            firestore.collection("users")
                .whereEqualTo("email", user?.email)
                .get()
                .addOnSuccessListener { querySnapshot ->
                    for (document in querySnapshot.documents) {
                        firestore.collection("users").document(document.id).delete()
                    }
                }
            firestore.collection("orders")
                .whereEqualTo("user_email", user?.email)
                .get()
                .addOnSuccessListener { querySnapshot ->
                    for (document in querySnapshot.documents) {
                        firestore.collection("orders").document(document.id).delete()
                    }
                }
            firestore.collection("boxes")
                .whereEqualTo("bought", true)
                .get()
                .addOnSuccessListener { querySnapshot ->
                    for (document in querySnapshot.documents) {
                        firestore.collection("boxes").document(document.id).update("bought", false)
                    }
                }
            val firebaseUser = Firebase.auth.currentUser
            firebaseUser?.delete()
                ?.addOnCompleteListener { task ->
                    if (task.isSuccessful) {
                        Log.d(TAG, "User account deleted from FirebaseAuth")
                    } else {
                        Log.e(TAG, "Error deleting user account from FirebaseAuth", task.exception)
                    }
                }
        }
    }

}
