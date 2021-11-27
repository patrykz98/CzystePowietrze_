package com.example.czystepowietrze

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.example.czystepowietrze.fragments.MenuFragment

class MainActivity : AppCompatActivity() {

    private var backPressedTime: Long = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        supportFragmentManager.beginTransaction().add(R.id.fragmentContainer, MenuFragment()).commit()

    }

    override fun onBackPressed() {
        if(backPressedTime + 2000 > System.currentTimeMillis()){
            super.onBackPressed()
            return
        }else{
            Toast.makeText(baseContext, "Naciśnij jeszcze raz aby zamknąć", Toast.LENGTH_SHORT).show()
        }

        backPressedTime = System.currentTimeMillis()
    }



}