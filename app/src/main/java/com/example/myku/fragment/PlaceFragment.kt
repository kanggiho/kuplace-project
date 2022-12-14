package com.example.myku.fragment
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.fragment.app.Fragment
import com.example.myku.R
import com.example.myku.activity.AllActivity
import com.example.myku.activity.KeywordActivity
import com.example.myku.activity.RatingActivity
import com.example.myku.activity.SearchActivity
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore


class PlaceFragment : Fragment() {


    private var firestore: FirebaseFirestore? = null
    var auth: FirebaseAuth? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        //Inflate the layout for this fragment
        var v: View = inflater.inflate(R.layout.fragment_place, container, false)




        v.findViewById<ImageView>(R.id.keywordImage).setOnClickListener {
            val intent = Intent(activity, KeywordActivity::class.java)
            startActivity(intent)
        }

        v.findViewById<ImageView>(R.id.distanceImage).setOnClickListener {
            val intent = Intent(activity, AllActivity::class.java)
            startActivity(intent)
        }

        v.findViewById<ImageView>(R.id.findImage).setOnClickListener {
            val intent = Intent(activity, SearchActivity::class.java)
            startActivity(intent)
        }

        v.findViewById<ImageView>(R.id.ratingImage).setOnClickListener {
            val intent = Intent(activity, RatingActivity::class.java)
            startActivity(intent)
        }





//
//
//        auth = Firebase.auth
//
//        val mapView = MapView(activity)
//        val mapViewContainer = v.findViewById(com.example.myku.R.id.mapview) as ViewGroup
//        mapViewContainer.addView(mapView)
//
//        v.findViewById<Button>(com.example.myku.R.id.plusBtn).setOnClickListener {
//            firestore = Firebase.firestore
//
//            val content = hashMapOf(
//                "uid" to auth!!.currentUser!!.uid.toString(),
//                "field" to "??????",
//                "name" to "???????????????",
//                "latitude" to 192.5,
//                "longitude" to 32.7,
//                "scope" to 4.3,
//                "key1" to "?????????",
//                "key2" to "?????????",
//                "review" to "?????????"
//            )
//            firestore!!.collection("Place").document()
//                .set(content)


//            val intent = Intent(activity, RatingActivity::class.java)
//            startActivity(intent)
//      }
        return v

    }


}