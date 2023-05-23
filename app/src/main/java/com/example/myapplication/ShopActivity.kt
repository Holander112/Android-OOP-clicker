package com.example.myapplication

import android.content.Intent
import android.os.Bundle
import android.view.ViewGroup
import android.widget.Button
import android.widget.LinearLayout
import android.widget.TextView

class ShopActivity : MainActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        //initialization
        super.onCreate(savedInstanceState)
        setContentView(R.layout.shop)
    }

    override fun onStart() {
        super.onStart()
        val backButton: Button = findViewById(R.id.backButton)
        backButton.setOnClickListener() {
            finish()
        }
        // Get passed time data using intent object
        val intent = intent
        val time = intent.getLongExtra("timeLeft", 100000)

        val seconds = time / 1000
        val minutes = seconds / 60
        val remainingSeconds = seconds % 60
        // format the values in mm:ss
        val displaySeconds = String.format("%02d", remainingSeconds)
        val displayMinutes = String.format("%02d", minutes)
        // print the result in the UI
        val timeLeft: TextView = findViewById(R.id.timeLeft)
        timeLeft.text = displayMinutes + ':' + displaySeconds
        val moneyText: TextView = findViewById(R.id.moneyText)
        moneyText.text = Money.GetMoney().toString() + "$"

        // Add upgrade buttons
        val ll_main = findViewById(R.id.buttonList) as LinearLayout
        for (upgrade in Upgrades.list) {
            // creating the button
            val button_dynamic = Button(this)
            // setting layout_width and layout_height using layout parameters
            button_dynamic.layoutParams = LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.WRAP_CONTENT,
                ViewGroup.LayoutParams.WRAP_CONTENT
            )
            button_dynamic.setOnClickListener {
                upgrade.purchase()
                button_dynamic.text = upgrade.toString()

            }
            button_dynamic.text = upgrade.toString()
            // add Button to LinearLayout
            ll_main.addView(button_dynamic)
        }

    }

    override fun onResume() {
        super.onResume()
    }

    override fun onPause() {
        super.onPause()
    }
}