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

private lateinit var binding: ActivityRatingBinding
private val REQUEST_CODE = 123

class RatingActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityRatingBinding.inflate(layoutInflater)
        setContentView(binding.root)
        spinner_init()
        init()


    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == REQUEST_CODE) {
            if (resultCode == Activity.RESULT_OK) {
                val rname = data!!.getStringExtra("name").toString()
                val raddress = data!!.getStringExtra("address").toString()
                binding.rnameText.setText(rname)
                binding.raddressText.setText(raddress)




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

                    }
                    1   ->  {

                    }
                    //...
                    else -> {

                    }
                }
            }

            override fun onNothingSelected(parent: AdapterView<*>) {

            }
        }

    }

}