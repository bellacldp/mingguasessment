package org.d3if2025.mingguasessment

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.Fragment
import com.google.android.material.bottomnavigation.BottomNavigationView
import org.d3if2025.mingguasessment.databinding.ActivityMainBinding
import org.d3if2025.mingguasessment.fragments.FriendsFragment
import org.d3if2025.mingguasessment.fragments.HomeFragment
import org.d3if2025.mingguasessment.fragments.MenuFragment
import org.d3if2025.mingguasessment.fragments.MessagesFragment

class MainActivity : AppCompatActivity() {
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

    private fun setThatFragments(fragment : Fragment) =
        supportFragmentManager.beginTransaction().apply {
            replace(R.id.frame, fragment)
            commit()
        }
}