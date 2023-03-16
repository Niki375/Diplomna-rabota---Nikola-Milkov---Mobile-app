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

            val boxesList = mutableListOf<Box>()

            firestore.collection("orders")
                .whereEqualTo("user_email", user?.email)
                .get()
                .addOnSuccessListener { ordersQuerySnapshot ->

                    for (orderDocument in ordersQuerySnapshot.documents) {
                        val boxId = orderDocument.getString("box_id")

                        firestore.collection("boxes")
                            .document(boxId!!)
                            .get()
                            .addOnSuccessListener { boxDocumentSnapshot ->
                                val box = boxDocumentSnapshot.toObject(Box::class.java)

                                if (box != null) {
                                    boxesList.add(box)
                                }

                                _boxes.value = boxesList.toList()
                            }
                    }
                }
        }
    }
}
