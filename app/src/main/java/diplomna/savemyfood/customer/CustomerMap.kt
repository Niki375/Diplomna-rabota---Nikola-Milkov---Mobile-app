package diplomna.savemyfood.customer

import androidx.compose.foundation.layout.*
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.viewinterop.AndroidView
import org.osmdroid.config.Configuration
import org.osmdroid.tileprovider.tilesource.TileSourceFactory
import org.osmdroid.util.GeoPoint
import org.osmdroid.views.MapView

@Composable
fun CustomerMapPage(viewModel: CustomerMapViewModel) {
    viewModel.getGeoPoints()

    Column(
        modifier = Modifier.padding(20.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        Text(
            text = "Map of businesses",
            style = TextStyle(
                textAlign = TextAlign.Center,
                fontSize = 40.sp,
                textDecoration = TextDecoration.Underline
            )
        )

        Spacer(modifier = Modifier.height(150.dp))

        AndroidView(
            modifier = Modifier.fillMaxWidth().height(200.dp),
            factory = { context ->
                Configuration.getInstance().userAgentValue = context.packageName
                val mapView = MapView(context).apply {
                    setTileSource(TileSourceFactory.MAPNIK)
                    setMultiTouchControls(true)
                    controller.setZoom(7.5)
                    controller.setCenter(GeoPoint(42.7339, 25.4858))
                }
                viewModel.updateMapMarkers(mapView, viewModel.geoPoints.value, null)
                mapView
            },
            update = { view ->
                viewModel.updateMapMarkers(view, viewModel.geoPoints.value, null)
            }
        )
    }
}