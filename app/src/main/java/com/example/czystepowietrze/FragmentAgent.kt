package com.example.czystepowietrze

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.czystepowietrze.fragments.AvailableLocationsFragment
import com.example.czystepowietrze.fragments.DetailsFragment

class FragmentAgent : AppCompatActivity(){

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val positionCardView: Int = intent.getIntExtra("position",0)

        if(positionCardView == 0)
            supportFragmentManager.beginTransaction().replace(R.id.mainLayout,
                AvailableLocationsFragment()
            ).commit()

        if(positionCardView == 1)
            supportFragmentManager.beginTransaction().replace(R.id.mainLayout, DetailsFragment()).commit()


    }

}