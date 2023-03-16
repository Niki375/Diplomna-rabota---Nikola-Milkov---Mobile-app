package diplomna.savemyfood.business

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import diplomna.savemyfood.authentication.User
import diplomna.savemyfood.service.AuthServiceImpl
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch

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
        }
    }
}
