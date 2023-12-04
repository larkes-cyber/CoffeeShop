package com.example.coffeeshop.android.component

import android.view.LayoutInflater
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.viewinterop.AndroidView
import com.example.coffeeshop.R
import com.yandex.mapkit.MapKitFactory
import com.yandex.mapkit.geometry.Point
import com.yandex.mapkit.map.CameraPosition
import com.yandex.mapkit.map.MapObjectCollection
import com.yandex.mapkit.map.PlacemarkMapObject
import com.yandex.mapkit.mapview.MapView
import com.yandex.runtime.image.ImageProvider

@Composable
fun MapView(
    modifier: Modifier = Modifier,
    onMapChange:Pair<Float, Float>
) {

    val startLocation = Point(59.9402, 30.315)
    var zoomValue = 16.5f

    var mapObjectCollection: MapObjectCollection
    var placemarkMapObject: PlacemarkMapObject


    val cnt = LocalContext.current

    AndroidView(
        modifier = modifier,
        factory = {context ->
            val view = LayoutInflater.from(context).inflate(com.example.coffeeshop.android.R.layout.map_view, null)
            val mapView = view.findViewById<MapView>(com.example.coffeeshop.android.R.id.mapview)

            val marker = com.example.coffeeshop.android.R.drawable.ic_pin_black_png

            MapKitFactory.getInstance().onStart()
            mapView.onStart()

            mapView.map.move(
                CameraPosition(startLocation, zoomValue, 0.0f, 0.0f)
            )

            mapObjectCollection = mapView.map.mapObjects
            placemarkMapObject = mapObjectCollection.addPlacemark(startLocation, ImageProvider.fromResource(cnt, marker))
            placemarkMapObject.opacity = 0.5f

            mapView
        }
    )

}