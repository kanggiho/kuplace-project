package com.example.myku.activity

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.myku.databinding.ActivityMapBinding
import net.daum.mf.map.api.MapPOIItem
import net.daum.mf.map.api.MapPoint
import net.daum.mf.map.api.MapView

class MapActivity : AppCompatActivity() {
    private lateinit var binding:ActivityMapBinding
    lateinit var mapView: MapView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMapBinding.inflate(layoutInflater)
        setContentView(binding.root)

        mapView = binding.checkMap

        val intent = intent




        mapView.setMapCenterPoint(MapPoint.mapPointWithGeoCoord(intent.getDoubleExtra("lat",0.0), intent.getDoubleExtra("long",0.0)), true);


        val marker = MapPOIItem()
        marker.apply {
            itemName = intent.getStringExtra("name")   // 마커 이름
            mapPoint = MapPoint.mapPointWithGeoCoord(intent.getDoubleExtra("lat",0.0), intent.getDoubleExtra("long",0.0))   // 좌표
            markerType = MapPOIItem.MarkerType.BluePin
            selectedMarkerType = MapPOIItem.MarkerType.RedPin
        }
        mapView.addPOIItem(marker)




    }
}