package diplomna.savemyfood.business

import android.content.ContentValues
import android.content.ContentValues.TAG
import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.google.firebase.auth.ktx.auth
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import diplomna.savemyfood.authentication.User
import diplomna.savemyfood.customer.CustomerMapViewModel
import diplomna.savemyfood.service.AuthServiceImpl
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import org.mapsforge.map.view.MapView

class BusinessProfileViewModel : ViewModel() {
    private val authService = AuthServiceImpl()

    val user = MutableStateFlow<User?>(null)

    fun getUser() {
        viewModelScope.launch {
            user.value = authService.getUserData()
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
            val deletedBusinesses = mutableListOf<String>()
            firestore.collection("users")
                .whereEqualTo("email", user?.email)
                .get()
                .addOnSuccessListener { querySnapshot ->
                    for (document in querySnapshot.documents) {
                        val email = document.getString("email") ?: ""
                        deletedBusinesses.add(email)
                        firestore.collection("users").document(document.id).delete()
                    }
                }
            firestore.collection("boxes")
                .whereEqualTo("email", user?.email)
                .get()
                .addOnSuccessListener { querySnapshot ->
                    for (document in querySnapshot.documents) {
                        val boxId = document.getString("id") ?: ""
                        firestore.collection("boxes").document(document.id).delete()
                        // delete all the orders that are related to the box
                        firestore.collection("orders")
                            .whereEqualTo("box_id", boxId)
                            .get()
                            .addOnSuccessListener { querySnapshot ->
                                for (document in querySnapshot.documents) {
                                    firestore.collection("orders").document(document.id).delete()
                                }
                            }
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
