package com.example.myapplication

class Score {
    companion object{
        var score = 0
        var max = 10
        var level = 1

        fun getScoreText():String{
          return score.toString() + "/" + max.toString()
        }

    }

}