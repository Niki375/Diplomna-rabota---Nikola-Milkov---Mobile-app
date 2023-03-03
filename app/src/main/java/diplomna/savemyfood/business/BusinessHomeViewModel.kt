package diplomna.savemyfood.business

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import diplomna.savemyfood.service.AuthServiceImpl
import kotlinx.coroutines.launch
import kotlinx.coroutines.tasks.await

class BusinessHomeViewModel : ViewModel() {
    private val authService = AuthServiceImpl()

    private val _boxes = MutableLiveData<List<Box>?>()
    val boxes: MutableLiveData<List<Box>?>
        get() = _boxes

    var boxEmailMap: Map<String, String> = mapOf()

    fun createBoxEmailMap() {
        val firestore = Firebase.firestore
        firestore.collection("orders")
            .get()
            .addOnSuccessListener { ordersQuerySnapshot ->
                val map = mutableMapOf<String, String>()
                for (orderDocument in ordersQuerySnapshot.documents) {
                    val boxId = orderDocument.getString("box_id") ?: ""
                    val email = orderDocument.getString("user_email") ?: ""
                    map[boxId] = email
                }
                boxEmailMap = map
            }
    }

    fun loadBoxes() {
        viewModelScope.launch {
            val firestore = Firebase.firestore
            val user = authService.getUserData()
            val query = firestore.collection("boxes").whereEqualTo("email", user?.email)
            val result = query.get().await()
            val boxes = result.documents.map { it.toObject(Box::class.java) }
            _boxes.value = boxes.takeIf { it.isNotEmpty() } as List<Box>?
        }
    }

}
