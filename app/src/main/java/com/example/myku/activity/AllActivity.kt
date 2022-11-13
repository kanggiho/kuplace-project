package com.example.myku.activity

import android.R
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.ListView
import androidx.appcompat.app.AppCompatActivity
import com.example.myku.databinding.ActivityAllBinding
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import com.mancj.materialsearchbar.MaterialSearchBar
import net.daum.mf.map.api.MapPOIItem
import net.daum.mf.map.api.MapPoint
import net.daum.mf.map.api.MapView

class AllActivity : AppCompatActivity() {


    private var firestore: FirebaseFirestore? = null
    var auth: FirebaseAuth? = null
    val db = FirebaseFirestore.getInstance()


    var allplace = ArrayList<String>()

    lateinit var binding: ActivityAllBinding

    lateinit var allmap: MapView


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityAllBinding.inflate(layoutInflater)
        setContentView(binding.root)



        var temp_name:String = ""
        var cnt=0

        db.collection("Place")   // 작업할 컬렉션
            .orderBy("name")
            .get()     // 문서 가져오기
            .addOnSuccessListener { result ->
                for(document in result){


                    if(temp_name!=document["name"].toString()||cnt==0){
                        allplace.add(document["name"].toString())
                        temp_name = document["name"].toString()
                        cnt++
                    }
                }

            }
            .addOnFailureListener { exception ->
                // 실패할 경우
                Log.w("MainActivity", "Error getting documents: $exception")
            }



        val lv = binding.mListView as ListView
        val ll = binding.LL
        val searchBar = binding.searchBar
        searchBar.setHint("Search")
        searchBar.setSpeechMode(false)

        //var prokw = arrayOf("Sombrero", "Cartwheel", "Pinwheel", "StarBust", "Whirlpool", "Ring Nebular", "Own Nebular", "Centaurus A", "Virgo Stellar Stream", "Canis Majos Overdensity", "Mayall's Object", "Leo", "Milky Way", "IC 1011", "Messier 81", "Andromeda", "Messier 87")

        //Adapter


        val adapter = ArrayAdapter<String>(this, R.layout.simple_list_item_1, allplace)
        lv.visibility = View.INVISIBLE
        ll.visibility = View.VISIBLE
        lv.setAdapter(adapter)
        searchBar.setOnSearchActionListener(object : MaterialSearchBar.OnSearchActionListener{
            override fun onButtonClicked(buttonCode: Int) {
                TODO("Not yet implemented")
            }

            override fun onSearchStateChanged(enabled: Boolean) {
                if(enabled){

                    lv.visibility = View.VISIBLE
                    ll.visibility = View.INVISIBLE
                }else{
                    lv.visibility = View.INVISIBLE
                    ll.visibility = View.VISIBLE
                }
            }

            override fun onSearchConfirmed(text: CharSequence?) {
                TODO("Not yet implemented")
            }

        })

        searchBar.addTextChangeListener(object : TextWatcher {
            override fun afterTextChanged(s: Editable?) {

            }

            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {

            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                adapter.getFilter().filter(s)
            }

        })


        lv.setOnItemClickListener(object : AdapterView.OnItemClickListener{
            override fun onItemClick(
                parent: AdapterView<*>?,
                view: View?,
                position: Int,
                id: Long
            ) {



                searchBar.disableSearch()


                var us_sum:Double = 0.0
                var us_num:Double = 0.0




                db.collection("Place")   // 작업할 컬렉션
                    .orderBy("name")
                    .get()     // 문서 가져오기
                    .addOnSuccessListener { result ->
                        for(document in result){
                            if(adapter.getItem(position)!!.toString()==document["name"].toString()){
                                binding.allplaceText.setText(document["name"].toString())
                                binding.alladdressText.setText("주소 : " + document["address"].toString())
                                allmap = binding.allmapView
                                allmap.setMapCenterPoint(MapPoint.mapPointWithGeoCoord(document["latitude"].toString().toDouble(), document["longitude"].toString().toDouble()), true);


                                val marker = MapPOIItem()
                                marker.apply {
                                    itemName = document["name"].toString()   // 마커 이름
                                    mapPoint = MapPoint.mapPointWithGeoCoord(document["latitude"].toString().toDouble(), document["longitude"].toString().toDouble())   // 좌표
                                    markerType = MapPOIItem.MarkerType.BluePin
                                    selectedMarkerType = MapPOIItem.MarkerType.RedPin
                                }
                                allmap.addPOIItem(marker)

                                break
                            }
                        }

                        for(document in result){
                            if(adapter.getItem(position)!!.toString()==document["name"].toString()){
                                us_sum+=document["scope"].toString().toDouble()
                                us_num+=1.0
                            }
                        }
                        binding.allrateText.setText("평점 : " + roundDigit(us_sum/us_num,2).toString()+"("+us_num.toInt().toString()+")")


                    }
                    .addOnFailureListener { exception ->
                        // 실패할 경우
                        Log.w("MainActivity", "Error getting documents: $exception")
                    }

                //Toast.makeText(this@AllActivity, adapter.getItem(position)!!.toString(), Toast.LENGTH_SHORT).show()


            }

        })

    }

    fun roundDigit(number : Double, digits : Int): Double {
        return Math.round(number * Math.pow(10.0, digits.toDouble())) / Math.pow(10.0, digits.toDouble())
    }
}