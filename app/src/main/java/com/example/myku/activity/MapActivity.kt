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



        val marker = MapPOIItem()
        marker.apply {
            itemName = "서울시청"   // 마커 이름
            mapPoint = MapPoint.mapPointWithGeoCoord(37.5666805, 126.9784147)   // 좌표
            markerType = MapPOIItem.MarkerType.BluePin
            selectedMarkerType = MapPOIItem.MarkerType.RedPin
            //markerType = MapPOIItem.MarkerType.CustomImage          // 마커 모양 (커스텀)
            //customImageResourceId = R.drawable.이미지               // 커스텀 마커 이미지
            //selectedMarkerType = MapPOIItem.MarkerType.CustomImage  // 클릭 시 마커 모양 (커스텀)
            //customSelectedImageResourceId = R.drawable.이미지       // 클릭 시 커스텀 마커 이미지
            //isCustomImageAutoscale = false      // 커스텀 마커 이미지 크기 자동 조정
            //setCustomImageAnchor(0.5f, 1.0f)    // 마커 이미지 기준점
        }
        mapView.addPOIItem(marker)




    }
}