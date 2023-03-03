package diplomna.savemyfood.customer

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import diplomna.savemyfood.business.Box
import diplomna.savemyfood.service.AuthServiceImpl
import kotlinx.coroutines.launch

class CustomerCartViewModel : ViewModel() {
    private val authService = AuthServiceImpl()

    private val _boxes = MutableLiveData<List<Box>?>()
    val boxes: MutableLiveData<List<Box>?>
        get() = _boxes

    fun loadBoxes() {
        viewModelScope.launch {
            val firestore = Firebase.firestore
            val user = authService.getUserData()

            val boxesList = mutableListOf<Box>() // create a mutable list to store the boxes

            firestore.collection("orders")
                .whereEqualTo("user_email", user?.email)
                .get()
                .addOnSuccessListener { ordersQuerySnapshot ->

                    // For each order document, get the box_id
                    for (orderDocument in ordersQuerySnapshot.documents) {
                        val boxId = orderDocument.getString("box_id")

                        // Get the box document from the boxes collection with the same box_id
                        firestore.collection("boxes")
                            .document(boxId!!)
                            .get()
                            .addOnSuccessListener { boxDocumentSnapshot ->
                                val box = boxDocumentSnapshot.toObject(Box::class.java)

                                // Add the box to the list
                                if (box != null) {
                                    boxesList.add(box)
                                }

                                // Update the live data with the new list of boxes
                                _boxes.value = boxesList.toList()
                            }
                    }
                }
        }
    }
}
