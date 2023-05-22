package com.example.myapplication


class EnergyDrink : Upgrade {
    constructor() : super(3)

    override fun purchase() {
        super.purchase()
        this.price = (this.count + 1) * 3
    }

    override fun getClickModifier(): Int {
        return this.count
    }

}