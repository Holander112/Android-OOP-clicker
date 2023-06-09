package com.example.myapplication

class Upgrades {
    companion object {
        private var savedList = mutableListOf<Upgrade>()

        var list = mutableListOf<Upgrade>(
            EnergyDrink(),
            GermanChocolate(),
            Outsource(),
            Bitcoin(),
            Stock(),
            Futures()
        )

        init {
            saveUpgrades()
        }

        fun saveUpgrades() {
            savedList = list.map { it.clone() }.toMutableList()
        }

        fun resetUpgrades() {
            list = savedList.map { it.clone() }.toMutableList()
        }

        fun getTotalScoreModifier(): Int {
            var value = 0
            for (upgrade in list) {
                value += upgrade.getScoreModifier()
            }
            return value
        }

        fun getTotalScoreMultiplier(): Int {
            var value = 1
            for (upgrade in list) {
                value *= upgrade.getScoreMultiplier()
            }
            return value
        }

        fun getTotalScorePerTick(): Int {
            var value = 0
            for (upgrade in list) {
                value += upgrade.getScorePerTick()
            }
            return value
        }

        fun getTotalScoreAfterLevel(): Int {
            var value = 0
            for (upgrade in list) {
                value += upgrade.getScoreAfterLevel()
            }
            return value
        }

        fun getTotalMoneyPerTick(): Int {
            var value = 0
            for (upgrade in list) {
                value += upgrade.getMoneyPerTick()
            }
            return value
        }

        fun getTotalMoneyAfterLevel(): Int {
            var value = 0
            for (upgrade in list) {
                value += upgrade.getMoneyAfterLevel()
            }
            return value
        }
    }

}