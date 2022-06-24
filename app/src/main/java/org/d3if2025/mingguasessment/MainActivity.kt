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

        ApiService.endPoint.getData()
            .enqueue(object : Callback<MainModel> {

                override fun onResponse(
                    call: Call<MainModel>,
                    response: Response<MainModel>
                ) {
                    if (response.isSuccessful){
                        showData( response.body()!!)
                    }
                }

                override fun onFailure(call: Call<MainModel>, t: Throwable) {
                    printLog( "onFailure: $t" )
                }

            })
    }

    private fun printLog(message: String){
        Log.d(TAG, message)
    }

    private fun showData(data: MainModel){
        val results = data.result
        for (result in results){
            printLog("title: ${result.title}")
        }
    }
    private fun setThatFragments(fragment : Fragment) =
        supportFragmentManager.beginTransaction().apply {
            replace(R.id.frame, fragment)
            commit()
        }
}