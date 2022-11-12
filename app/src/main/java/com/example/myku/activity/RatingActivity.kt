package com.example.myku.activity

import android.R
import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import androidx.appcompat.app.AppCompatActivity
import com.example.myku.databinding.ActivityRatingBinding
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase

private lateinit var binding: ActivityRatingBinding
private val REQUEST_CODE = 123



class RatingActivity : AppCompatActivity() {
    private var firestore: FirebaseFirestore? = null
    var auth: FirebaseAuth? = null

    var rt_field = "식당"
    var rt_name = "이름"
    var rt_address = "주소"
    var rt_long:Double = 127.5
    var rt_lat:Double = 32.5
    var rt_scope:Double = 4.5
    var rt_key1 = "key1"
    var rt_key2 = "key2"
    var rt_review = "시설이 좋아요."

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityRatingBinding.inflate(layoutInflater)
        setContentView(binding.root)

        spinner_init()
        init()
        leave()


    }


    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == REQUEST_CODE) {
            if (resultCode == Activity.RESULT_OK) {
                val rname = data!!.getStringExtra("name").toString()
                val raddress = data!!.getStringExtra("address").toString()
                val rlong = data!!.getStringExtra("longitude").toString().toDouble()
                val rlat = data!!.getStringExtra("latitude").toString().toDouble()
                binding.rnameText.setText(rname)
                binding.raddressText.setText(raddress)
                rt_name = rname
                rt_address = raddress
                rt_long = rlong
                rt_lat = rlat





                //Toast.makeText(this,rname.toString()+"그리고 "+raddress.toString(),Toast.LENGTH_LONG).show()

            }
        }
    }

    fun init(){


        binding.setMapImage.setOnClickListener {
            val intent = Intent(this, RatingMapActivity::class.java)
            startActivityForResult(intent, REQUEST_CODE)
        }
    }

    fun leave() {



        auth = Firebase.auth

//        val mapView = MapView(activity)
//        val mapViewContainer = v.findViewById(com.example.myku.R.id.mapview) as ViewGroup
//        mapViewContainer.addView(mapView)

        binding.ratingBtn.setOnClickListener {



            if(binding.check1.isChecked){
                if(rt_key1=="key1"){
                    rt_key1 = "따뜻함"
                }else if(rt_key2=="key2"){
                    rt_key2 = "따뜻함"
                }
            }
            if(binding.check2.isChecked){
                if(rt_key1=="key1"){
                    rt_key1 = "시원함"
                }else if(rt_key2=="key2"){
                    rt_key2 = "시원함"
                }
            }
            if(binding.check3.isChecked){
                if(rt_key1=="key1"){
                    rt_key1 = "아늑함"
                }else if(rt_key2=="key2"){
                    rt_key2 = "아늑함"
                }
            }
            if(binding.check4.isChecked){
                if(rt_key1=="key1"){
                    rt_key1 = "깨끗함"
                }else if(rt_key2=="key2"){
                    rt_key2 = "깨끗함"
                }
            }
            if(binding.check5.isChecked){
                if(rt_key1=="key1"){
                    rt_key1 = "조용함"
                }else if(rt_key2=="key2"){
                    rt_key2 = "조용함"
                }
            }
            if(binding.check6.isChecked){
                if(rt_key1=="key1"){
                    rt_key1 = "감성적임"
                }else if(rt_key2=="key2"){
                    rt_key2 = "감성적임"
                }
            }

            rt_review = binding.reviewText.text.toString()
            rt_scope = binding.ratingBar.rating.toString().toDouble()

            firestore = Firebase.firestore

            val content = hashMapOf(
                "uid" to auth!!.currentUser!!.uid.toString(),
                "field" to rt_field,
                "name" to rt_name,
                "address" to rt_address,
                "latitude" to rt_lat,
                "longitude" to rt_long,
                "scope" to rt_scope,
                "key1" to rt_key1,
                "key2" to rt_key2,
                "review" to rt_review
            )
            firestore!!.collection("Place").document()
                .set(content)

        }
    }







    fun spinner_init(){

        val items = arrayOf("카페","음식점","술집","스터디룸","파티룸")
        val myAdapter = ArrayAdapter(this, R.layout.simple_spinner_dropdown_item, items)

        binding.spinner.adapter = myAdapter

        binding.spinner.setSelection(1)

        binding.spinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(parent: AdapterView<*>, view: View, position: Int, id: Long) {

                //아이템이 클릭 되면 맨 위부터 position 0번부터 순서대로 동작하게 됩니다.
                when(position) {
                    0   ->  {
                        rt_field = "카페"
                    }
                    1   ->  {
                        rt_field = "음식점"
                    }
                    2   ->  {
                        rt_field = "술집"
                    }
                    3   ->  {
                        rt_field = "스터디룸"
                    }
                    4   ->  {
                        rt_field = "파티룸"
                    }
                    else -> {
                        rt_field = "미설정"
                    }
                }
            }

            override fun onNothingSelected(parent: AdapterView<*>) {

            }
        }

    }

}