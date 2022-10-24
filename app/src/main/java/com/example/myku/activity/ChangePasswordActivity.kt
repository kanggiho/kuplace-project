package com.example.myku.activity

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.myku.databinding.ActivityChangePasswordBinding

private lateinit var binding:ActivityChangePasswordBinding


class ChangePasswordActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityChangePasswordBinding.inflate(layoutInflater)
        setContentView(binding.root)




    }
}