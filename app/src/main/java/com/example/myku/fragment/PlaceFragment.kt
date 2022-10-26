package com.example.myku.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.fragment.app.Fragment
import com.example.myku.R
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase


class PlaceFragment : Fragment() {


    private var firestore:FirebaseFirestore? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        var v:View = inflater.inflate(R.layout.fragment_place, container, false)


        v.findViewById<Button>(R.id.plusBtn).setOnClickListener {
            firestore = Firebase.firestore

//            val content = hashMapOf(
//                "field" to "식당",
//                "name" to "우리식당",
//                "latitude" to 192.5,
//                "longitude" to 32.7,
//                "scope" to 4.3,
//                "key1" to "따뜻함",
//                "key2" to "깨끗함",
//                "review" to "좋아요"
//            )
//            firestore!!.collection("Place").document()
//                .set(content)

            val cont = hashMapOf(
                "func" to "함수",
                "fname" to "이름",

            )


            firestore!!.collection("Cont").document()
                .set(cont)




//            val intent = Intent(activity, RatingActivity::class.java)
//            startActivity(intent)
        }


        return v
    }

}