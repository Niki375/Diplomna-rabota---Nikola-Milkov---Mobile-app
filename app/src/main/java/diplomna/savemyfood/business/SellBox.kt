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
    // Create a Firestore instance
    val db = Firebase.firestore

    // Get the current user's UID
    val userId = FirebaseAuth.getInstance().currentUser?.uid

    // Retrieve the business account document from Firestore
    db.collection("users").document(userId!!)
        .get()
        .addOnSuccessListener { documentSnapshot ->
            if (documentSnapshot.exists()) {
                val address = documentSnapshot.getString("address") ?: ""

                // Get the current user's email
                val userEmail = Firebase.auth.currentUser?.email

                // Create a HashMap object with the box data
                val box = hashMapOf(
                    "food_type" to foodType,
                    "description" to description,
                    "pickup_time" to pickupTime,
                    "price_per_box" to pricePerBox,
                    "email" to userEmail,
                    "bought" to false,
                    "address" to address
                )

                // Add the box data to the "boxes" collection
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

                        // Update the document with the box data, including the generated ID
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
