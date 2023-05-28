package com.example.myapplication

open class Upgrade(cost: Int, var name: String, var description: String, var background: Int) :
    Cloneable {
    var price: Int = cost
    var count: Int = 0

    override fun toString(): String {
        return "${this.name}\nCount: ${this.count}\nPrice: ${this.price}\n${this.description}"
    }

    public override fun clone(): Upgrade {
        return super.clone() as Upgrade
    }


    /** Purchase one more of this upgrade, recalculate price of next one */
    open fun purchase() {
        count++
    }

    /** How much to add to every click */
    open fun getScoreModifier(): Int {
        return 0
    }

    /** How much to multiply every click */
    open fun getScoreMultiplier(): Int {
        return 1
    }

    /** How much score to add every tick */
    open fun getScorePerTick(): Int {
        return 0
    }

    /** How much score to add after finishing level */
    open fun getScoreAfterLevel(): Int {
        return 0
    }

    /** How much money to add every tick */
    open fun getMoneyPerTick(): Int {
        return 0
    }

    /** How much money to add after finishing level */
    open fun getMoneyAfterLevel(): Int {
        return 0
    }
}