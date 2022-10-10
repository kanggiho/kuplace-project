package com.example.myku.fragment

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.fragment.app.Fragment
import com.example.myku.R
import com.example.myku.login.LoginActivity
import com.example.myku.main.useremail
import com.example.myku.main.username
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase

class MyinfoFragment : Fragment() {




    private var auth : FirebaseAuth? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment


        val v:View = inflater.inflate(R.layout.fragment_myinfo, container, false)


        auth = Firebase.auth
        v.findViewById<TextView>(R.id.infoEmail).text = useremail
        v.findViewById<TextView>(R.id.infoName).text = username

        v.findViewById<Button>(R.id.logout).setOnClickListener {
            val intent = Intent(activity, LoginActivity::class.java)
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)
            startActivity(intent)
            auth?.signOut()
        }


        return v
    }



}