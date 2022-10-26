package com.example.myku.activity

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.myku.databinding.ActivityRatingBinding

private lateinit var binding: ActivityRatingBinding

class RatingActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityRatingBinding.inflate(layoutInflater)
        setContentView(binding.root)


    }

}