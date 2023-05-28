package com.example.myapplication

class Stock() :
    Upgrade(basePrice, "Local stock", "Generates small amount\nof money per second", R.drawable.locstonk) {
    companion object {
        private const val basePrice = 200
    }

    override fun purchase() {
        super.purchase()
//        this.price = basePrice.toDouble().pow(this.count.toDouble()).toInt()
    }

    override fun getMoneyPerTick(): Int {
        return this.count
    }
}