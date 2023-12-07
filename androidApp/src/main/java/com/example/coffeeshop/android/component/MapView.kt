package com.example.coffeeshop.android.component

import android.content.Context
import android.graphics.Bitmap
import android.graphics.Canvas
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
import androidx.core.content.ContextCompat
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

@Composable
fun MapView(
    modifier: Modifier = Modifier,
    onMapChange:(Pair<Float, Float>) -> Unit
) {

    val startLocation = Point(59.9402, 30.315)
    var zoomValue = 16.5f

    val ctx = LocalContext.current

    var mapObjectCollection: MapObjectCollection? = null
    var placemarkMapObject: PlacemarkMapObject? = null
    var mapView:MapView? = null
    val marker = createBitmapFromVector(
        art = com.example.coffeeshop.android.R.drawable.ic_pin_red_svg,
        ctx = ctx
    )


    val inputListener = remember {
        object : InputListener {
            override fun onMapTap(p0: Map, p1: Point) {
                onMapChange(Pair(p1.longitude.toFloat(), p1.latitude.toFloat()))
                mapObjectCollection = mapView!!.map.mapObjects
                if(placemarkMapObject != null) mapObjectCollection?.remove(placemarkMapObject!!)
                placemarkMapObject = mapObjectCollection?.addPlacemark(p1, ImageProvider.fromBitmap(marker))
                placemarkMapObject?.opacity = 0.5f

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
fun createBitmapFromVector(ctx: Context, art: Int): Bitmap? {
    val drawable = ContextCompat.getDrawable(ctx, art) ?: return null
    val bitmap = Bitmap.createBitmap(
        drawable.intrinsicWidth,
        drawable.intrinsicHeight,
        Bitmap.Config.ARGB_8888
    ) ?: return null
    val canvas = Canvas(bitmap)
    drawable.setBounds(0, 0, canvas.width, canvas.height)
    drawable.draw(canvas)
    return bitmap
}