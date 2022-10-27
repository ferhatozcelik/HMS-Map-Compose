package com.ferhatozcelik.firstcompose

import android.content.Context
import android.graphics.Bitmap
import android.graphics.Canvas
import android.graphics.PorterDuff
import android.view.LayoutInflater
import android.view.View
import android.widget.ImageView
import androidx.annotation.DrawableRes
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.viewinterop.AndroidView
import androidx.core.content.ContextCompat
import com.huawei.hms.maps.CameraUpdateFactory
import com.huawei.hms.maps.model.BitmapDescriptorFactory
import com.huawei.hms.maps.model.LatLng
import com.huawei.hms.maps.model.MarkerOptions
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch


@Preview
@Composable
fun Map() {
    val mapView = rememberMapViewWithLifecycle()
    val context = LocalContext.current
    Column(
        modifier = Modifier
            .fillMaxHeight()
            .fillMaxWidth()
            .background(Color.White)
    ) {
        AndroidView({ mapView}) {mapView->
            CoroutineScope(Dispatchers.Main).launch {
                mapView.getMapAsync { map ->
                    map.uiSettings.isZoomControlsEnabled = true

                    val rnd =  LatLng(41.02959203350298, 29.117370221177943)
                    map.moveCamera(CameraUpdateFactory.newLatLngZoom(rnd,6f))
                    val markerOptions = MarkerOptions()
                        .title("Huawei RnD")
                        .snippet("Huawei Turkey RnD Office")
                        .icon(BitmapDescriptorFactory.fromBitmap(getMarkerBitmapFromView(R.drawable.marker_back, 0, context)))
                        .position(rnd)
                    map.addMarker(markerOptions)
                }
            }
        }
    }


}

fun getMarkerBitmapFromView(@DrawableRes resId: Int, markerType: Int, context: Context): Bitmap? {

    val customMarkerView: View = (context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater?)!!.inflate(R.layout.view_custom_marker, null)
    val markerImageView = customMarkerView.findViewById<View>(R.id.profile_imagebg) as ImageView
    markerImageView.setImageResource(resId)
    markerImageView.setColorFilter(ContextCompat.getColor(context, R.color.red), android.graphics.PorterDuff.Mode.SRC_IN);

    customMarkerView.measure(View.MeasureSpec.UNSPECIFIED, View.MeasureSpec.UNSPECIFIED)
    customMarkerView.layout(0, 0, customMarkerView.measuredWidth, customMarkerView.measuredHeight)
    customMarkerView.buildDrawingCache()
    val returnedBitmap = Bitmap.createBitmap(customMarkerView.measuredWidth, customMarkerView.measuredHeight, Bitmap.Config.ARGB_8888)
    val canvas = Canvas(returnedBitmap)
    canvas.drawColor(android.graphics.Color.WHITE, PorterDuff.Mode.SRC_IN)
    val drawable = customMarkerView.background
    drawable?.draw(canvas)
    customMarkerView.draw(canvas)
    return returnedBitmap
}