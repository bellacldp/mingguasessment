package org.d3if2025.mingguasessment

import android.content.ContentValues.TAG
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import com.google.android.material.bottomnavigation.BottomNavigationView
import org.d3if2025.mingguasessment.databinding.ActivityMainBinding
import org.d3if2025.mingguasessment.fragments.FriendsFragment
import org.d3if2025.mingguasessment.fragments.HomeFragment
import org.d3if2025.mingguasessment.fragments.MenuFragment
import org.d3if2025.mingguasessment.fragments.MessagesFragment
import org.d3if2025.mingguasessment.retrofit.ApiService
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainActivity : AppCompatActivity() {

    private  val TAG: String = "MainActivity"

    private lateinit var bottomNavView : BottomNavigationView
    lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        supportActionBar?.hide()
        bottomNavView = binding.bottomNavView

        val homeFragment = HomeFragment()
        val friendsFragment = FriendsFragment()
        val messagesFragment = MessagesFragment()
        val menuFragment = MenuFragment()

        setThatFragments(homeFragment)

        bottomNavView.setOnItemSelectedListener {
            when(it.itemId){
                R.id.home ->{
                    setThatFragments(homeFragment)
                }
                R.id.friends ->{
                    setThatFragments(friendsFragment)
                }
                R.id.messages ->{
                    setThatFragments(messagesFragment)
                }
                R.id.menu ->{
                    setThatFragments(menuFragment)
                }
            }
            true
        }

    }
    override fun onStart(){
        super.onStart()
        getDataFromApi()
    }

    private fun getDataFromApi(){

        ApiService.endPoint.getPhotos()
            .enqueue(object : Callback<List<MainModel>> {

                override fun onResponse(
                    call: Call<List<MainModel>>,
                    response: Response<List<MainModel>>
                ) {
                    if (response.isSuccessful){
                        val result = response.body()
                        showPhotos( result!!)
                    }
                }

                override fun onFailure(call: Call<List<MainModel>>, t: Throwable) {
                    printLog( t.toString() )
                }

            })
    }

    private fun printLog(message: String){
        Log.d(TAG, message)
    }

    private fun showPhotos(photos: List<MainModel>){
        for (photo in photos){
            printLog("url: ${photo.url}")
        }
    }
    private fun setThatFragments(fragment : Fragment) =
        supportFragmentManager.beginTransaction().apply {
            replace(R.id.frame, fragment)
            commit()
        }
}