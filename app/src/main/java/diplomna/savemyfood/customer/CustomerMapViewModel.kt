package diplomna.savemyfood.customer

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import kotlinx.coroutines.launch
import org.osmdroid.util.GeoPoint
import org.osmdroid.views.MapView
import org.osmdroid.views.overlay.Marker

data class LocationData(
    val geoPoint: GeoPoint,
    val name: String,
    val address: String
)

class CustomerMapViewModel : ViewModel() {
    private val _geoPoints = MutableLiveData<List<LocationData>?>()

    val geoPoints: MutableLiveData<List<LocationData>?>
        get() = _geoPoints

    fun getGeoPoints() {
        viewModelScope.launch {
            val firestore = Firebase.firestore
            var locationDataList = mutableListOf<LocationData>()
            firestore.collection("users")
                .whereEqualTo("business", true)
                .get()
                .addOnSuccessListener { result ->
                    for (document in result) {
                        val latitude = document.getDouble("latitude") ?: 0.0
                        val longitude = document.getDouble("longitude") ?: 0.0
                        val username = document.getString("username") ?: ""
                        val address = document.getString("address") ?: ""
                        val geoPoint = GeoPoint(latitude, longitude)
                        val locationData = LocationData(geoPoint, username, address)
                        locationDataList.add(locationData)
                    }
                    _geoPoints.value = locationDataList.takeIf { it.isNotEmpty() }
                }
        }
    }

    fun updateMapMarkers(mapView: MapView, geoPoints: List<LocationData>?, deletedBusinesses: List<String>?) {
        mapView.overlays.clear()

        geoPoints?.forEach {
            if (deletedBusinesses == null || it.name !in deletedBusinesses) {
                val marker = Marker(mapView)
                marker.position = it.geoPoint
                marker.title = it.name
                marker.snippet = it.address
                marker.setAnchor(Marker.ANCHOR_CENTER, Marker.ANCHOR_BOTTOM)
                mapView.overlays.add(marker)
            }
        }

        mapView.invalidate()
    }

}
