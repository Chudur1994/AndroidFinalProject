package com.example.student.finalproject.models

data class Card(var attack: Int = 0,
                var health: Int = 0,
                var name: String = "",
                var description: String = "",
                var cost: Int = 0,
                var className: Class = Class.DEFAULT,
                var rarity: Rarity = Rarity.DEFAULT,
                var imagePath: String = "") {


    override fun toString(): String {
        return "{name = $name, cost = $cost, description = $description}"
    }
}
