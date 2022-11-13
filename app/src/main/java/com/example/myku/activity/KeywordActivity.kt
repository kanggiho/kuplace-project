package com.example.myku.activity

import android.R
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.myku.adapter.RecyclerAdapter
import com.example.myku.data.ListItem
import com.example.myku.databinding.ActivityKeywordBinding
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.ktx.Firebase


class KeywordActivity : AppCompatActivity() {

    private var firestore: FirebaseFirestore? = null
    var auth: FirebaseAuth? = null
    val db = FirebaseFirestore.getInstance()





    private lateinit var binding: ActivityKeywordBinding
    val itemList = arrayListOf<ListItem>()      // 아이템 배열
    val RecyclerAdapter = RecyclerAdapter(itemList)

    var kw_field:String = ""
    var kw_key1:String = ""
    var kw_key2:String = ""




    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityKeywordBinding.inflate(layoutInflater)
        setContentView(binding.root)

        init()
        spinner_init()




    }

    fun init(){
        binding.goBtn.setOnClickListener {

            recommend()

        }
    }







    fun recommend(){
        auth = Firebase.auth



        var ml_name = mutableListOf<String>()
        var ml_address = mutableListOf<String>()
        var ml_rate = mutableListOf<Double>()

        binding.rvList.layoutManager = LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)
        binding.rvList.adapter = RecyclerAdapter


        RecyclerAdapter.setItemClickListener(object: RecyclerAdapter.OnItemClickListener{
            override fun onClick(v: View, position: Int) {
                // 클릭 시 이벤트 작성
                //Toast.makeText(v.context,"hihi",Toast.LENGTH_SHORT).show()

                val intent = Intent(v.context, com.example.myku.activity.MapActivity::class.java)
                startActivity(intent)
            }
        })




        itemList.clear()
        RecyclerAdapter.notifyDataSetChanged()






        db.collection("Place")   // 작업할 컬렉션
            .orderBy("name")
            .get()     // 문서 가져오기
            .addOnSuccessListener { result ->

                //Toast.makeText(this,"잘 가져옴", Toast.LENGTH_LONG).show()
                var temp_name = ""
                var reco_num:Double = 0.0
                var cnt:Int = 0
 //성공할 경우
                for (document in result) {  // 가져온 문서들은 result에 들어감

                    if(kw_field == document["field"] as String){
                        //Toast.makeText(this,reco_num.toString(), Toast.LENGTH_SHORT).show()


                        if(temp_name == document["name"] as String){
                            if(kw_key1 == document["key1"] as String||kw_key1 == document["key2"]as String){
                                reco_num+=(document["scope"] as Double)*0.8
                                if(kw_key2 == document["key1"] as String||kw_key2 == document["key2"]as String){
                                    reco_num+=(document["scope"] as Double)*1.3
                                }
                            }
                        }else{

                            if(cnt!=0 && reco_num!=0.0){
                                ml_name.add(document["name"].toString())
                                ml_address.add(document["address"].toString())
                                ml_rate.add(reco_num)
                            }
                            //Toast.makeText(this,"추가됨", Toast.LENGTH_SHORT).show()
                            //Toast.makeText(this,ml_name.size.toString(),Toast.LENGTH_SHORT).show()
                            reco_num = 0.0

                            temp_name = document["name"] as String

                            if(kw_key1 == document["key1"] as String||kw_key1 == document["key2"]as String){
                                reco_num+=(document["scope"] as Double).toDouble()*0.8
                                if(kw_key2 == document["key1"] as String||kw_key2 == document["key2"]as String){
                                    reco_num+=(document["scope"] as Double).toDouble()*1.3
                                }
                            }

                        }
                        //v.findViewById<TextView>(com.example.myku.R.id.cafeText1).setText(document["name"].toString())
                        //v.findViewById<TextView>(com.example.myku.R.id.cafeText2).setText(document["address"].toString())
                    }
                    cnt++


                    //val item = ListLayout(document["name"] as String, document["number"] as String)
                    //itemList.add(item)
                }

                // 아이템 추가
                for(i in 0..ml_name.size-1){
                    //Toast.makeText(this,"정상",Toast.LENGTH_SHORT).show()
                    itemList.add(ListItem(ml_name[i], ml_address[i] ,roundDigit(ml_rate[i], 1).toString()))
                }




                // 리스트가 변경됨을 어댑터에 알림
                RecyclerAdapter.notifyDataSetChanged()

            }
            .addOnFailureListener { exception ->
                // 실패할 경우
                Log.w("MainActivity", "Error getting documents: $exception")
            }


        //Toast.makeText(this,ml_name.size.toString(),Toast.LENGTH_SHORT).show()

//        itemList.add(ListItem("슈브제", "서울 광진구 화양동 8-42","32.5점"))
//        itemList.add(ListItem("돕", "서울 광진구 화양동 9-22","18.2점"))
//        itemList.add(ListItem("모어스터디", "서울 광진구 자양동 7-13","11.2점"))






    }







    fun spinner_init(){

        val items = arrayOf("카페","음식점","술집","스터디룸","파티룸")
        val myAdapter = ArrayAdapter(this, R.layout.simple_spinner_dropdown_item, items)

        binding.spinner1.adapter = myAdapter

        binding.spinner1.setSelection(1)

        binding.spinner1.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(parent: AdapterView<*>, view: View, position: Int, id: Long) {

                //아이템이 클릭 되면 맨 위부터 position 0번부터 순서대로 동작하게 됩니다.
                when(position) {
                    0   ->  {
                        kw_field = "카페"
                    }
                    1   ->  {
                        kw_field = "음식점"
                    }
                    2   ->  {
                        kw_field = "술집"
                    }
                    3   ->  {
                        kw_field = "스터디룸"
                    }
                    4   ->  {
                        kw_field = "파티룸"
                    }
                    else -> {
                        kw_field = "미설정"
                    }
                }
            }

            override fun onNothingSelected(parent: AdapterView<*>) {

            }
        }

        val items2 = arrayOf("따뜻함","시원함","아늑함","깨끗함","조용함","감성적임")
        val myAdapter2 = ArrayAdapter(this, R.layout.simple_spinner_dropdown_item, items2)

        binding.spinner2.adapter = myAdapter2

        binding.spinner2.setSelection(1)

        binding.spinner2.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(parent: AdapterView<*>, view: View, position: Int, id: Long) {

                //아이템이 클릭 되면 맨 위부터 position 0번부터 순서대로 동작하게 됩니다.
                when(position) {
                    0   ->  {
                        kw_key1 = "따뜻함"
                    }
                    1   ->  {
                        kw_key1 = "시원함"
                    }
                    2   ->  {
                        kw_key1 = "아늑함"
                    }
                    3   ->  {
                        kw_key1 = "깨끗함"
                    }
                    4   ->  {
                        kw_key1 = "조용함"
                    }
                    5   ->  {
                        kw_key1 = "감성적임"
                    }
                    else -> {
                        kw_key1 = "미설정"
                    }
                }
            }

            override fun onNothingSelected(parent: AdapterView<*>) {

            }
        }

        val items3 = arrayOf("따뜻함","시원함","아늑함","깨끗함","조용함","감성적임")
        val myAdapter3 = ArrayAdapter(this, R.layout.simple_spinner_dropdown_item, items3)

        binding.spinner3.adapter = myAdapter3

        binding.spinner3.setSelection(2)

        binding.spinner3.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(parent: AdapterView<*>, view: View, position: Int, id: Long) {

                //아이템이 클릭 되면 맨 위부터 position 0번부터 순서대로 동작하게 됩니다.
                when(position) {
                    0   ->  {
                        kw_key2 = "따뜻함"
                    }
                    1   ->  {
                        kw_key2 = "시원함"
                    }
                    2   ->  {
                        kw_key2 = "아늑함"
                    }
                    3   ->  {
                        kw_key2 = "깨끗함"
                    }
                    4   ->  {
                        kw_key2 = "조용함"
                    }
                    5   ->  {
                        kw_key2 = "감성적임"
                    }
                    else -> {
                        kw_key2 = "미설정"
                    }
                }
            }

            override fun onNothingSelected(parent: AdapterView<*>) {

            }
        }



    }

    fun roundDigit(number : Double, digits : Int): Double {
        return Math.round(number * Math.pow(10.0, digits.toDouble())) / Math.pow(10.0, digits.toDouble())
    }
}