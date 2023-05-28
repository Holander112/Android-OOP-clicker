package com.example.myapplication

class Outsource :
    Upgrade(basePrice, "Outsource to India", "Generates small amount\nclicks per second", R.drawable.intern) {
    companion object {
        private const val basePrice = 20
    }

    override fun purchase() {
        super.purchase()
//        this.price = basePrice.toDouble().pow(this.count.toDouble()).toInt()
    }

    override fun getScorePerTick(): Int {
        return this.count
    }
}