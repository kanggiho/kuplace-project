package com.example.myku.fragment

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import com.example.myku.R
import com.google.firebase.firestore.FirebaseFirestore

class HomeFragment : Fragment() {

    val db = FirebaseFirestore.getInstance()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val v:View = inflater.inflate(R.layout.fragment_home, container, false)
        var cnt = 0
        var range = (5..20)
        db.collection("Place")   // 작업할 컬렉션
            .get()     // 문서 가져오기
            .addOnSuccessListener { result ->
// 성공할 경우
                for (document in result) {  // 가져온 문서들은 result에 들어감

                    if(cnt > range.random()){
                        break
                    }

                    if("카페"== document["field"] as String){
                        cnt+=1
                        v.findViewById<TextView>(R.id.cafeText1).setText(document["name"].toString())
                        v.findViewById<TextView>(R.id.cafeText2).setText(document["address"].toString())
                    }

                    if("음식점"== document["field"] as String){
                        cnt+=1
                        v.findViewById<TextView>(R.id.restaurantText1).setText(document["name"].toString())
                        v.findViewById<TextView>(R.id.restaurantText2).setText(document["address"].toString())
                    }

                    if("스터디룸"== document["field"] as String){
                        cnt+=1
                        v.findViewById<TextView>(R.id.studyText1).setText(document["name"].toString())
                        v.findViewById<TextView>(R.id.studyText2).setText(document["address"].toString())
                    }


                    //val item = ListLayout(document["name"] as String, document["number"] as String)
                    //itemList.add(item)
                }

            }
            .addOnFailureListener { exception ->
                // 실패할 경우
                Log.w("MainActivity", "Error getting documents: $exception")
            }





        return v
    }

}