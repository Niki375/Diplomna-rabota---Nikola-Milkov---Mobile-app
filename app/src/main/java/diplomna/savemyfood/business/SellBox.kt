package diplomna.savemyfood.business

import android.content.ContentValues.TAG
import android.util.Log
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

    // Create a HashMap object with the box data
    val box = hashMapOf(
        "food_type" to foodType,
        "description" to description,
        "pickup_time" to pickupTime,
        "price_per_box" to pricePerBox,
        "quantity_of_boxes" to quantityOfBoxes
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
