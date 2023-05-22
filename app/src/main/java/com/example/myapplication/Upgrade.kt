package com.example.myapplication

open class Upgrade {
    var name = "Generic Upgrade"
    var price: Int
    var count: Int

    constructor(cost: Int) {
        this.price = cost
        count = 0
    }

    /** Purchase one more of this upgrade, recalculate price of next one */
    open fun purchase() {
        count++
    }

    /** How much to add to every click */
    open fun getClickModifier(): Int {
        return 0
    }

    /** How much to multiply every click */
    open fun getClickMultiplier(): Int {
        return 1
    }

    /** How much money to add every tick */
    open fun getTickAmount(): Int {
        return 0
    }

    /** How much money to add after finishing level */
    open fun getAfterLevelAmount(): Int {
        return 0
    }

    override fun toString(): String {
        return "Name: ${this.name}\nCount: ${this.count}\nPrice: ${this.price}"
    }
}