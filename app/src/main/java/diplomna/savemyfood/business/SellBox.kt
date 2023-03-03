package diplomna.savemyfood.business

import android.content.ContentValues.TAG
import android.util.Log
import com.google.firebase.auth.ktx.auth
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase

fun onSellBoxClick(
    foodType: String,
    description: String,
    pickupTime: String,
    pricePerBox: String,
    quantityOfBoxes: String
) {
    // Create a Firestore instance
    val db = Firebase.firestore
    // Get the current user
    val user_email = Firebase.auth.currentUser?.email

    // Create a HashMap object with the box data
    val box = hashMapOf(
        "food_type" to foodType,
        "description" to description,
        "pickup_time" to pickupTime,
        "price_per_box" to pricePerBox,
        "quantity_of_boxes" to quantityOfBoxes,
        "email" to user_email,
        "isBought" to false
    )

    // Add the box data to the "boxes" collection
    db.collection("boxes")
        .add(box)
        .addOnSuccessListener { documentReference ->
            Log.d(TAG, "DocumentSnapshot added with ID: ${documentReference.id}")
        }
        .addOnFailureListener { e ->
            Log.w(TAG, "Error adding document", e)
        }
}
