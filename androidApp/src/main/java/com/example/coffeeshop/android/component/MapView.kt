package com.example.coffeeshop.android.component

import android.util.Log
import android.view.LayoutInflater
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.viewinterop.AndroidView
import com.example.coffeeshop.R
import com.yandex.mapkit.MapKitFactory
import com.yandex.mapkit.geometry.Point
import com.yandex.mapkit.map.CameraPosition
import com.yandex.mapkit.map.IconStyle
import com.yandex.mapkit.map.InputListener
import com.yandex.mapkit.map.Map
import com.yandex.mapkit.map.MapObjectCollection
import com.yandex.mapkit.map.PlacemarkMapObject
import com.yandex.mapkit.mapview.MapView
import com.yandex.runtime.image.ImageProvider
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

@Composable
fun MapView(
    modifier: Modifier = Modifier,
    onMapChange:(Pair<Float, Float>) -> Unit
) {

    val startLocation = Point(59.9402, 30.315)
    var zoomValue = 16.5f

    val cnt = LocalContext.current

    var mapObjectCollection: MapObjectCollection? = null
    var placemarkMapObject: PlacemarkMapObject? = null
    var mapView:MapView? = null
    val marker = com.example.coffeeshop.android.R.drawable.location_pin


    val inputListener = remember {
        object : InputListener {
            override fun onMapTap(p0: Map, p1: Point) {
                onMapChange(Pair(p1.longitude.toFloat(), p1.latitude.toFloat()))
                mapObjectCollection = mapView!!.map.mapObjects
                if(placemarkMapObject != null) mapObjectCollection?.remove(placemarkMapObject!!)
                placemarkMapObject = mapObjectCollection?.addPlacemark(p1, ImageProvider.fromResource(cnt, marker))
                placemarkMapObject?.opacity = 0.5f
                placemarkMapObject?.direction = 12f
            }

            override fun onMapLongTap(p0: Map, p1: Point) {
            }
        }
    }


    AndroidView(
        modifier = modifier,
        factory = {context ->
            val view = LayoutInflater.from(context).inflate(com.example.coffeeshop.android.R.layout.map_view, null)
            mapView = view.findViewById<MapView>(com.example.coffeeshop.android.R.id.mapview)

            mapView!!.map.addInputListener(inputListener)


            MapKitFactory.getInstance().onStart()
            mapView!!.onStart()

            mapView!!.map.move(
                CameraPosition(startLocation, zoomValue, 0.0f, 0.0f)
            )



            mapView!!
        }
    )

}