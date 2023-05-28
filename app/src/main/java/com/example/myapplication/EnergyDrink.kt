package com.example.myapplication


class EnergyDrink : Upgrade {
    companion object {
        private const val basePrice = 3
    }

    constructor() : super(basePrice, "Energy Drink", "+1 to every click", R.drawable.can)

    override fun purchase() {
        super.purchase()
        this.price = (this.count + 1) * basePrice
    }

    override fun getScoreModifier(): Int {
        return this.count
    }

}