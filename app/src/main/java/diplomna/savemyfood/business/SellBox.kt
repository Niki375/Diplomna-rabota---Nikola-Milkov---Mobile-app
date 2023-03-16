package diplomna.savemyfood.business

import android.content.ContentValues.TAG
import android.util.Log
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase

fun onSellBoxClick(
    foodType: String,
    description: String,
    pickupTime: String,
    pricePerBox: Float
) {
    val db = Firebase.firestore

    val userId = FirebaseAuth.getInstance().currentUser?.uid

    db.collection("users").document(userId!!)
        .get()
        .addOnSuccessListener { documentSnapshot ->
            if (documentSnapshot.exists()) {
                val address = documentSnapshot.getString("address") ?: ""

                val userEmail = Firebase.auth.currentUser?.email

                val box = hashMapOf(
                    "food_type" to foodType,
                    "description" to description,
                    "pickup_time" to pickupTime,
                    "price_per_box" to pricePerBox,
                    "email" to userEmail,
                    "bought" to false,
                    "address" to address
                )

                db.collection("boxes")
                    .add(box)
                    .addOnSuccessListener { documentReference ->
                        val boxId = documentReference.id
                        val boxData = hashMapOf(
                            "food_type" to foodType,
                            "description" to description,
                            "pickup_time" to pickupTime,
                            "price_per_box" to pricePerBox,
                            "email" to userEmail,
                            "bought" to false,
                            "id" to boxId,
                            "address" to address
                        )

                        db.collection("boxes")
                            .document(boxId)
                            .update(boxData as Map<String, Any>)
                            .addOnSuccessListener {
                                Log.d(TAG, "DocumentSnapshot added with ID: $boxId")
                            }
                            .addOnFailureListener { e ->
                                Log.w(TAG, "Error adding document", e)
                            }
                    }
                    .addOnFailureListener { e ->
                        Log.w(TAG, "Error adding document", e)
                    }
            } else {
                Log.d(TAG, "Business account document not found")
            }
        }
        .addOnFailureListener { e ->
            Log.w(TAG, "Error getting business account document", e)
        }
}
