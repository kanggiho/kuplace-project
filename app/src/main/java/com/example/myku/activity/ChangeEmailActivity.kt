package com.example.myku.activity

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.myku.databinding.ActivityChangeEmailBinding

private lateinit var binding:ActivityChangeEmailBinding

class ChangeEmailActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityChangeEmailBinding.inflate(layoutInflater)

        setContentView(binding.root)


    }
}