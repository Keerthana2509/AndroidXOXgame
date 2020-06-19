package com.example.xoxgame

import android.opengl.Visibility
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

    var gameState = arrayOf(2,2,2,2,2,2,2,2,2)
    var winningPositions = arrayOf(
        arrayOf(0,1,2),
        arrayOf(3,4,5),
        arrayOf(6,7,8),
        arrayOf(0,3,6),
        arrayOf(1,4,7),
        arrayOf(2,5,8),
        arrayOf(0,4,8),
        arrayOf(2,4,6)
    )
    var activePlayer = 0
    var gameActive = true
    fun dropin(view: View){
        val counter :ImageView = view as ImageView
        val tappedCounter = counter.getTag().toString().toInt()
        if(gameState[tappedCounter] == 2 && gameActive) {
            gameState[tappedCounter] = activePlayer
            counter.translationY = -1500F

            if (activePlayer == 0) {
                counter.setImageResource(R.drawable.xox_x)
                activePlayer = 1
            } else {
                counter.setImageResource(R.drawable.xox_o)
                activePlayer = 0
            }
////        counter.setTransalationY(-1500)
//        counter.translationY = -1500F
//        counter.setImageResource(R.drawable.xox_o)
            counter.animate().translationYBy(1500F).rotation(3600F).setDuration(300)
            for (winningPosition in winningPositions) {
                if (gameState[winningPosition[0]] == gameState[winningPosition[1]] && gameState[winningPosition[1]] == gameState[winningPosition[2]] && gameState[winningPosition[0]] != 2) {
                    var winner: String = ""
                    gameActive = false
                    if (activePlayer == 1) {
                        winner = "Player One"
                    } else {
                        winner = "Player Two"
                    }
//                    Toast.makeText(this, winner + " has Won!!", Toast.LENGTH_LONG).show()
                    val button = findViewById<Button>(R.id.button)
                    val text = findViewById<TextView>(R.id.textview)
                    text.text = winner+ " has Won the match!!"
                    text.visibility = View.VISIBLE
                    button.visibility = View.VISIBLE
                }
            }
        }
    }
    fun replay(view: View){
        val button = findViewById<Button>(R.id.button)
        val text = findViewById<TextView>(R.id.textview)
        text.visibility = View.INVISIBLE
        button.visibility = View.INVISIBLE
        var i =0
        val gridLayout : GridLayout = findViewById(R.id.grid_layout)
        while (i < gridLayout.childCount){
            val counter:ImageView = gridLayout.getChildAt(i) as ImageView
            counter.setImageDrawable(null)
            i++
        }
        gameState = arrayOf(2,2,2,2,2,2,2,2,2)
        activePlayer = 0
        gameActive = true
    }
}
