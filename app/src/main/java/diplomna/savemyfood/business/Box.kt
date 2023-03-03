package diplomna.savemyfood.business

data class Box(
    val food_type: String = "",
    val description: String = "",
    val pickup_time: String = "",
    val price_per_box: Float = 0.0f,
    var bought: Boolean = false,
    val id: String = "",
    val email: String = ""
)
