package com.example.myapplication

class Upgrades {
    companion object {
        var list = mutableListOf<Upgrade>(EnergyDrink())
        fun getTotalClickerModifier(): Int {
            var value = 0;
            for (upgrade in Upgrades.list) {
                value += upgrade.getClickModifier()
            }
            return value
        }

        fun getTotalClickerMultiplier(): Int {
            var value = 1;
            for (upgrade in Upgrades.list) {
                value *= upgrade.getClickMultiplier()
            }
            return value
        }

        fun getTotalTickAmount(): Int {
            var value = 0;
            for (upgrade in Upgrades.list) {
                value += upgrade.getTickAmount()
            }
            return value
        }

        fun getTotalAfterLevelAmount(): Int {
            var value = 0;
            for (upgrade in Upgrades.list) {
                value += upgrade.getAfterLevelAmount()
            }
            return value
        }
    }

}