package com.example.myapplication

import android.graphics.BlendMode
import android.graphics.BlendModeColorFilter
import android.graphics.Color
import android.graphics.PorterDuff
import android.os.Build
import android.os.Bundle
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView

class ShopActivity : MainActivity() {
    private lateinit var buttonContainer: LinearLayout
    private lateinit var moneyText: TextView
    private lateinit var timeLeft: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        //initialization
        super.onCreate(savedInstanceState)
        setContentView(R.layout.shop)
        buttonContainer = findViewById(R.id.buttonList) as LinearLayout
        moneyText = findViewById(R.id.moneyText)
        timeLeft = findViewById(com.example.myapplication.R.id.timeLeft)
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
        timeLeft.text = displayMinutes + ':' + displaySeconds
        moneyText.text = Money.getMoney().toString() + "$"
        refreshShopButtons()


    }

    fun refreshShopButtons() {
        // get rid of all previous buttons
        buttonContainer.removeAllViews()
        // Add upgrade buttons
        val currentMoney = Money.getMoney()
        for (upgrade in Upgrades.list) {
            // creating the button
            val newLayout = LinearLayout(this)
            val newUpgradeButton = Button(this)
            val upgradeImage = ImageView(this)
            upgradeImage.setBackgroundResource(upgrade.background)
            // setting layout_width and layout_height using layout parameters
            newLayout.layoutParams = LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.WRAP_CONTENT
            )
            // If player has enough money to buy upgrade
            if (upgrade.price < currentMoney) {
                newUpgradeButton.setOnClickListener {
                    Money.modifyMoney(-upgrade.price.toLong())
                    moneyText.text = Money.getMoney().toString() + "$"
                    upgrade.purchase()
                    newUpgradeButton.text = upgrade.toString()
                    refreshShopButtons()
                }
            } else {
                newUpgradeButton.setTextColor(Color.parseColor("#E2E2E2"))
                val bgCol = Color.parseColor("#660000")
                if (Build.VERSION.SDK_INT >= 29)
                    newUpgradeButton.background.colorFilter =
                        BlendModeColorFilter(bgCol, BlendMode.MULTIPLY)
                else
                    newUpgradeButton.background.setColorFilter(bgCol, PorterDuff.Mode.MULTIPLY)
            }


            newUpgradeButton.text = upgrade.toString()
            // add Button to LinearLayout
            newLayout.addView(newUpgradeButton)
            newLayout.addView(upgradeImage)
            buttonContainer.addView(newLayout)
        }
    }

    override fun onResume() {
        super.onResume()
    }

    override fun onPause() {
        super.onPause()
    }
}