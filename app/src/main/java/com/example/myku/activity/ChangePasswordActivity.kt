package com.example.myku.activity

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.myku.databinding.ActivityChangePasswordBinding
import com.google.firebase.auth.EmailAuthProvider
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase

private lateinit var binding:ActivityChangePasswordBinding


class ChangePasswordActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityChangePasswordBinding.inflate(layoutInflater)
        setContentView(binding.root)


        binding.changePWBtn.setOnClickListener {

            var str = binding.currentpassword.text.toString()

            val user = Firebase.auth.currentUser

            val credential = EmailAuthProvider
                .getCredential(user?.email.toString(), str)

// Prompt the user to re-provide their sign-in credentials
            user?.reauthenticate(credential)
                ?.addOnCompleteListener { }

            var changePassword = binding.changePassword.text.toString()
            if(changePassword.isNotEmpty()&&str.isNotEmpty()){


                user!!.updatePassword(changePassword)
                    .addOnCompleteListener { task ->
                        if (task.isSuccessful) {
                            Toast.makeText(this,"변경이 완료되었습니다.", Toast.LENGTH_SHORT).show()
                            finish()
                        }else{
                            Toast.makeText(this,"정보를 다시 확인해주세요.", Toast.LENGTH_SHORT).show()
                        }
                    }

            }else{
                Toast.makeText(this,"빈칸이 존재합니다.", Toast.LENGTH_SHORT).show()
            }
        }



    }
}