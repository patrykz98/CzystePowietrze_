package com.example.czystepowietrze

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.czystepowietrze.fragments.MenuFragment

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        supportFragmentManager.beginTransaction().add(R.id.fragmentContainer, MenuFragment()).commit()

    }



}