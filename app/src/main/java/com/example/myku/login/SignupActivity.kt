package com.example.myku.login

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.myku.databinding.ActivitySignupBinding
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.auth.ktx.userProfileChangeRequest
import com.google.firebase.ktx.Firebase

private lateinit var binding: ActivitySignupBinding


class SignupActivity : AppCompatActivity() {
    private var auth : FirebaseAuth? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        auth = Firebase.auth
        binding = ActivitySignupBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.createbutton.setOnClickListener {
            createAccount(binding.createid.text.toString(),binding.createpassword.text.toString(),binding.createname.text.toString())
        }

        binding.backbutton.setOnClickListener {
            finish()
        }

    }

    private fun createAccount(email: String, password: String, name: String) {

        if (email.isNotEmpty() && password.isNotEmpty() && name.isNotEmpty()) {
            auth?.createUserWithEmailAndPassword(email, password)
                ?.addOnCompleteListener(this) { task ->
                    if (task.isSuccessful) {
                        //성공시 이름도 추가하기
                        val temp_name = userProfileChangeRequest { displayName = name }
                        auth?.currentUser?.updateProfile(temp_name)

                        Toast.makeText(
                            this, "계정 생성 완료.",
                            Toast.LENGTH_SHORT
                        ).show()
                        finish() // 가입창 종료
                    } else {
                        Toast.makeText(
                            this, "계정 생성 실패",
                            Toast.LENGTH_SHORT
                        ).show()
                    }
                }
        }
    }

}