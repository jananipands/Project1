package com.example.wordle


import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import android.widget.EditText
import androidx.core.view.isVisible



class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        runGame()
    }

    private fun runGame(){

        var continueGame = true
        val guess1 = findViewById<TextView>(R.id.guess1)
        val guess2 = findViewById<TextView>(R.id.guess2)
        val guess3 = findViewById<TextView>(R.id.guess3)
        val check1 = findViewById<TextView>(R.id.check1)
        val check2 = findViewById<TextView>(R.id.check2)
        val check3 = findViewById<TextView>(R.id.check3)
        val ans = findViewById<TextView>(R.id.answer)

        val button = findViewById<Button>(R.id.button)


        button.text = "Guess!"
        val answer : String = FourLetterWordList.getRandomFourLetterWord()

        for(nums in 1..3){

            button.setOnClickListener {
                val guessStr = findViewById<EditText>(R.id.guess).toString()
                val check = checkGuess(guessStr.uppercase(), answer.uppercase())

                if(check == "OOOO"){
                    return@setOnClickListener // equivalent to break
                }

                when (nums) {
                    1 -> {
                        guess1.text = guessStr
                        check1.text = check

                        guess1.isVisible = true
                        check1.isVisible = true

                    }
                    2 -> {
                        guess2.text = guessStr
                        check2.text = check

                        guess2.isVisible = true
                        check2.isVisible = true

                    }
                    3 -> {
                        guess3.text = guessStr
                        check3.text = check

                        guess3.isVisible = true
                        check3.isVisible = true
                    }
                }


            }
        }

        ans.text = answer
        ans.isVisible = true

        val restart = "restart"
        button.text = restart

        button.setOnClickListener {
            continueGame = true
        }


    }

    /**
     * Parameters / Fields:
     *   wordToGuess : String - the target word the user is trying to guess
     *   guess : String - what the user entered as their guess
     *
     * Returns a String of 'O', '+', and 'X', where:
     *   'O' represents the right letter in the right place
     *   '+' represents the right letter in the wrong place
     *   'X' represents a letter not in the target word
     */
    private fun checkGuess(guess: String, wordToGuess: String) : String {
        var result = ""
        for (i in 0..3) {
            if (guess[i] == wordToGuess[i]) {
                result += "O"
            }
            else if (guess[i] in wordToGuess) {
                result += "+"
            }
            else {
                result += "X"
            }
        }
        return result
    }
}