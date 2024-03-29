package diplomna.savemyfood.customer

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import diplomna.savemyfood.business.Box
import diplomna.savemyfood.service.AuthServiceImpl
import kotlinx.coroutines.launch
import kotlinx.coroutines.tasks.await

class CustomerHomeViewModel : ViewModel() {
    private val authService = AuthServiceImpl()

    private val _boxes = MutableLiveData<List<Box>?>()
    val boxes: MutableLiveData<List<Box>?>
        get() = _boxes

    fun loadBoxes() {
        viewModelScope.launch {
            val firestore = Firebase.firestore
            val boxCollection = firestore.collection("boxes")
            val query = boxCollection.whereEqualTo("bought", false)
            val boxes = query.get().await().toObjects(Box::class.java)
            _boxes.value = boxes
        }
    }

    fun buyBox(box: Box) {
        viewModelScope.launch {
            val firestore = Firebase.firestore
            val user = authService.getUserData()
            if(user?.money!! < box.price_per_box) {
                return@launch
            }
            val newMoney = user.money - box.price_per_box
            firestore.collection("users")
                .whereEqualTo("email", user.email)
                .get()
                .addOnSuccessListener { querySnapshot ->
                    for (document in querySnapshot.documents) {
                        firestore.collection("users").document(document.id).update("money", newMoney)
                    }
                }
            val order = hashMapOf(
                "food_type" to box.food_type,
                "description" to box.description,
                "price" to box.price_per_box,
                "user_email" to user.email,
                "business_email" to box.email,
                "box_id" to box.id,
                "pickup_time" to box.pickup_time,
                "address" to box.address
            )
            firestore.collection("orders").add(order)
            firestore.collection("boxes").document(box.id).update("bought", true)
            loadBoxes()
        }
    }
}
