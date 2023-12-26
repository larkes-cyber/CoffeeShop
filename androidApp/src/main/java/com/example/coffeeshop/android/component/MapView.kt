package com.example.coffeeshop.android.component

import android.content.Context
import android.content.DialogInterface.OnClickListener
import android.graphics.Bitmap
import android.graphics.Canvas
import android.util.Log
import android.view.LayoutInflater
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.viewinterop.AndroidView
import androidx.core.content.ContextCompat
import com.example.coffeeshop.R
import com.example.coffeeshop.domain.model.Location
import com.yandex.mapkit.MapKitFactory
import com.yandex.mapkit.geometry.Point
import com.yandex.mapkit.map.CameraPosition
import com.yandex.mapkit.map.IconStyle
import com.yandex.mapkit.map.InputListener
import com.yandex.mapkit.map.Map
import com.yandex.mapkit.map.MapObject
import com.yandex.mapkit.map.MapObjectCollection
import com.yandex.mapkit.map.MapObjectTapListener
import com.yandex.mapkit.map.PlacemarkMapObject
import com.yandex.mapkit.mapview.MapView
import com.yandex.runtime.image.ImageProvider

@Composable
fun MapView(
    modifier: Modifier = Modifier,
    locations:(List<Location>) = listOf(),
    selectingMode:Boolean = false,
    onMapChange:(Pair<Double, Double>) -> Unit
    ) {


    val startLocation = Point(51.5, -0.12)
    var zoomValue = 10.5f

    val ctx = LocalContext.current

    var mapObjectCollection: MapObjectCollection? = null
    var placemarkMapObject: PlacemarkMapObject? = null
    var mapView:MapView? = null
    val marker = createBitmapFromVector(
        art = com.example.coffeeshop.android.R.drawable.ic_pin_red_svg,
        ctx = ctx
    )

    fun addMarkerOnMap(
        point:Point,
        clickable:Boolean = false,
        onClickListener: MapObjectTapListener? = null
    ){
        mapObjectCollection = mapView!!.map.mapObjects
        placemarkMapObject = mapObjectCollection?.addPlacemark(point, ImageProvider.fromBitmap(marker))
        placemarkMapObject?.opacity = 0.5f

        if(clickable){
            placemarkMapObject!!.addTapListener(onClickListener!!)
        }

    }

    val inputListener = remember {
        object : InputListener {
            override fun onMapTap(p0: Map, p1: Point) {
                onMapChange(Pair(p1.longitude, p1.latitude))
                addMarkerOnMap(p1)

            }

            override fun onMapLongTap(p0: Map, p1: Point) {
            }
        }
    }

    val onLocationClickListener = MapObjectTapListener { mapObject, point ->
        onMapChange(Pair(point.latitude, point.longitude))
        true
    }




    AndroidView(
        modifier = modifier,
        factory = {context ->
            val view = LayoutInflater.from(context).inflate(com.example.coffeeshop.android.R.layout.map_view, null)
            mapView = view.findViewById(com.example.coffeeshop.android.R.id.mapview)

            if(selectingMode){
                mapView!!.map.addInputListener(inputListener)
            }

            MapKitFactory.getInstance().onStart()
            mapView!!.onStart()

            mapView!!.map.move(
                CameraPosition(startLocation, zoomValue, 0.0f, 0.0f)
            )

            locations.forEach {
                addMarkerOnMap(
                    point = Point(it.latitude, it.longitude),
                    clickable = true,
                    onClickListener = onLocationClickListener
                )
            }


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