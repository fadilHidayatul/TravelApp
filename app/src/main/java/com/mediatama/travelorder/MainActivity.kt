package com.mediatama.travelorder

import android.content.Context
import android.os.Bundle
import android.os.Handler
import android.view.MenuItem
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentTransaction
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.mediatama.travelorder.Home.HomeFragment
import com.mediatama.travelorder.Kendaraan.KendaraanFragment
import com.mediatama.travelorder.Pemesanan.PemesananFragment
import com.mediatama.travelorder.Profile.ProfileFragment
import com.mediatama.travelorder.SharedPreferences.PrefManager
import com.mediatama.travelorder.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding : ActivityMainBinding
    private lateinit var context: Context
    private lateinit var manager : PrefManager

    var doubleback : Boolean = false
    lateinit var back : Toast

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        context = this

        initBottomView()
//
//        binding.bottomNavigation.selectedItemId = R.id.nav_home
    }

    private fun initBottomView() {

        binding.bottomNavigation.setOnNavigationItemSelectedListener(object :
            BottomNavigationView.OnNavigationItemSelectedListener {
            override fun onNavigationItemSelected(item: MenuItem): Boolean {
                val selectedFragment: Fragment? = null

                when (item.itemId) {
                    R.id.nav_home -> changeFragment(
                        HomeFragment(),
                        HomeFragment::class.java.simpleName
                    )
                    R.id.nav_kendaraan -> changeFragment(
                        KendaraanFragment(),
                        KendaraanFragment::class.java.simpleName
                    )
                    R.id.nav_pemesanan -> changeFragment(
                        PemesananFragment(),
                        PemesananFragment::class.java.simpleName
                    )
                    R.id.nav_profil -> changeFragment(
                        ProfileFragment(),
                        ProfileFragment::class.java.simpleName
                    )
                }

                return true
            }

        })

        changeFragment(HomeFragment(), HomeFragment::class.java.simpleName)
    }

    fun changeFragment(fragment: Fragment, tag: String) {

        val mfragmentManager : FragmentManager = supportFragmentManager
        val fragmentTransaction: FragmentTransaction = mfragmentManager.beginTransaction()

        val currentFragment: Fragment? = mfragmentManager.primaryNavigationFragment
        if (currentFragment != null){
            fragmentTransaction.hide(currentFragment)
        }

        var tempFragment : Fragment? = mfragmentManager.findFragmentByTag(tag)
        if (tempFragment == null){
            tempFragment = fragment
            fragmentTransaction.add(R.id.fragment_container, tempFragment, tag)
        }else{
            fragmentTransaction.show(tempFragment)
        }

        fragmentTransaction.setPrimaryNavigationFragment(tempFragment)
        fragmentTransaction.setReorderingAllowed(true)
        fragmentTransaction.commitNowAllowingStateLoss()

    }

    override fun onBackPressed() {
        manager = PrefManager(context)

        if (doubleback){
            manager.removeMobilBoolean()
            manager.removeRuteBoolean()

            back.cancel()
            super.onBackPressed()
            moveTaskToBack(true)
        }else{
            back = Toast.makeText(context, "Tekan Lagi Untuk Keluar", Toast.LENGTH_SHORT)
            back.show()
            doubleback = true
            val handler = Handler()
            handler.postDelayed(object : Runnable {
                override fun run() {
                    doubleback = false
                }

            }, 2000 )




        }

    }
}

