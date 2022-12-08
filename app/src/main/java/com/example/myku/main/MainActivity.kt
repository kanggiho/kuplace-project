package com.example.myku.main

import android.os.Bundle
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import androidx.viewpager2.widget.ViewPager2
import com.example.myku.ViewPagerAdapter
import com.example.myku.databinding.ActivityMainBinding
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase


private lateinit var binding: ActivityMainBinding

private var auth : FirebaseAuth? = null

lateinit var username : String
lateinit var useremail : String


class MainActivity : AppCompatActivity(), BottomNavigationView.OnNavigationItemSelectedListener {


    //private var TAG = "SOL_LOG"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        auth = Firebase.auth
        username = auth?.currentUser?.displayName.toString()
        useremail = auth?.currentUser?.email.toString()



        //해시키
//        try {
//            val information = packageManager.getPackageInfo(packageName, PackageManager.GET_SIGNING_CERTIFICATES)
//            val signatures = information.signingInfo.apkContentsSigners
//            for (signature in signatures) {
//                val md = MessageDigest.getInstance("SHA").apply {
//                    update(signature.toByteArray())
//                }
//                val HASH_CODE = String(Base64.encode(md.digest(), 0))
//
//                Log.d(TAG, "HASH_CODE -> $HASH_CODE")
//            }
//        } catch (e: Exception) {
//            Log.d(TAG, "Exception -> $e")
//        }


        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        nav_setting()
    }

    override fun onNavigationItemSelected(item: MenuItem): Boolean {
        when(item.itemId){
            com.example.myku.R.id.item_one -> {
                // ViewPager의 현재 item에 첫 번째 화면을 대입
                binding.pager.currentItem = 0
                return true
            }
            com.example.myku.R.id.item_two -> {
                // ViewPager의 현재 item에 두 번째 화면을 대입
                binding.pager.currentItem = 1
                return true
            }
            com.example.myku.R.id.item_three -> {
                // ViewPager의 현재 item에 세 번째 화면을 대입
                binding.pager.currentItem = 3
//                val pro = userProfileChangeRequest { displayName = "giho" }
//                auth?.currentUser?.updateProfile(pro)
//                Toast.makeText(this,auth?.currentUser?.displayName.toString(),Toast.LENGTH_SHORT).show()

                return true
            }
//            com.example.myku.R.id.item_four -> {
//                // ViewPager의 현재 item에 세 번째 화면을 대입
//                binding.pager.currentItem = 3
//                return true
//            }
            else -> {
                return false
            }
        }
    }

    fun nav_setting(){




        // 페이저에 어댑터 연결
        binding.pager.adapter = ViewPagerAdapter(this)

        // 슬라이드하여 페이지가 변경되면 바텀네비게이션의 탭도 그 페이지로 활성화
        binding.pager.registerOnPageChangeCallback(
            object : ViewPager2.OnPageChangeCallback() {

                override fun onPageSelected(position: Int) {
                    super.onPageSelected(position)
                    binding.bottomNavigationView.menu.getItem(position).isChecked = true
                }
            }
        )

        // 리스너 연결
        binding.bottomNavigationView.setOnNavigationItemSelectedListener(this)
    }



}