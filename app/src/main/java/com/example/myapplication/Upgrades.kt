package com.example.myapplication

class Upgrades {
    companion object {
        val list =
            mutableListOf<Upgrade>(EnergyDrink(), GermanChocolate(), Bitcoin(), Stock(), Futures())

        fun getTotalClickerModifier(): Int {
            var value = 0
            for (upgrade in list) {
                value += upgrade.getClickModifier()
            }
            return value
        }

        fun getTotalClickerMultiplier(): Int {
            var value = 1
            for (upgrade in list) {
                value *= upgrade.getClickMultiplier()
            }
            return value
        }

        fun getTotalTickAmount(): Int {
            var value = 0
            for (upgrade in list) {
                value += upgrade.getTickAmount()
            }
            return value
        }

        fun getTotalAfterLevelAmount(): Int {
            var value = 0
            for (upgrade in list) {
                value += upgrade.getAfterLevelAmount()
            }
            return value
        }
    }

}